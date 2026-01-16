package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapRetry;
import org.openqa.selenium.*;

import java.util.*;

public class SapGrid {
    private final WebDriver driver;

    public SapGrid(WebDriver driver) {
        this.driver = driver;
    }

    private void scrollIntoView(WebElement e) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", e
        );
    }

    // row: 0-based, col: 1-based
    public WebElement findCellByRowCol(int rowIndex, int colIndex) {
        String xp = String.format("//td[@lsmatrixrowindex='%d' and @lsmatrixcolindex='%d']", rowIndex, colIndex);

        return SapRetry.get(() -> {
            WebElement cell = driver.findElement(By.xpath(xp));
            scrollIntoView(cell);
            return cell;
        }, 3, 300);
    }

    public WebElement getCellInput(WebElement cell) {
        try {
            return cell.findElement(By.xpath(".//span[@role='textbox']"));
        } catch (NoSuchElementException e) {
            return cell;
        }
    }

    public String getCellText(int row, int col) {
        WebElement cell = findCellByRowCol(row, col);
        return getCellInput(cell).getText().trim();
    }

    public void setCellValue(int row, int col, String value) {
        WebElement cell = findCellByRowCol(row, col);
        WebElement input = getCellInput(cell);
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, value);
    }

    // ===== Identifiers with automatic fallback =====

    public Map<Integer, String> identifiers() {
        Map<Integer, String> ids = identifiersFromFirstRow();
        if (!ids.isEmpty()) return ids;
        return identifiersFromThColumnHeaders();
    }

    private Map<Integer, String> identifiersFromFirstRow() {
        Map<Integer, String> ids = new LinkedHashMap<>();
        List<WebElement> cells = driver.findElements(By.xpath("//td[@lsmatrixrowindex='0' and @lsmatrixcolindex]"));
        for (WebElement c : cells) {
            String col = c.getAttribute("lsmatrixcolindex");
            if (col == null) continue;
            String key = c.getText().trim();
            if (!key.isEmpty()) ids.put(Integer.parseInt(col), key);
        }
        return ids;
    }

    private Map<Integer, String> identifiersFromThColumnHeaders() {
        Map<Integer, String> ids = new LinkedHashMap<>();
        List<WebElement> ths = driver.findElements(By.xpath("//th[@role='columnheader']"));

        int seq = 1;
        for (WebElement th : ths) {
            String text = th.getText().trim();
            if (text.isEmpty()) {
                seq++;
                continue;
            }

            String col = firstNonBlank(
                    th.getAttribute("lsmatrixcolindex"),
                    th.getAttribute("aria-colindex"),
                    th.getAttribute("data-colindex"),
                    th.getAttribute("data-col")
            );

            int colIndex = (col != null) ? safeParseInt(col, seq) : seq;
            ids.put(colIndex, text);
            seq++;
        }
        return ids;
    }

    private static String firstNonBlank(String... vals) {
        for (String v : vals) if (v != null && !v.isBlank()) return v;
        return null;
    }

    private static int safeParseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return fallback;
        }
    }

    public Map<String, String> rowAsMap(int rowIndex) {
        Map<Integer, String> ids = identifiers();
        if (ids.isEmpty()) throw new RuntimeException("No identifiers found (row0 td or th columnheader).");

        Map<String, String> row = new LinkedHashMap<>();
        for (var e : ids.entrySet()) {
            row.put(e.getValue(), getCellText(rowIndex, e.getKey()));
        }
        return row;
    }

    // ===== Virtualization helpers =====

    private WebElement findFirstDataCellSafe() {
        List<WebElement> candidates = driver.findElements(By.xpath("//td[@lsmatrixrowindex and @lsmatrixcolindex]"));
        for (WebElement td : candidates) {
            String r = td.getAttribute("lsmatrixrowindex");
            if (r == null) continue;
            try {
                int ri = Integer.parseInt(r);
                if (ri >= 1) return td;
            } catch (Exception ignored) {
            }
        }
        List<WebElement> header = driver.findElements(By.xpath("//td[@lsmatrixrowindex='0' and @lsmatrixcolindex]"));
        if (!header.isEmpty()) return header.get(0);
        return driver.findElement(By.xpath("//td"));
    }

    private Set<Integer> getVisibleRowIndices() {
        Set<Integer> rows = new HashSet<>();
        List<WebElement> tds = driver.findElements(By.xpath("//td[@lsmatrixrowindex and @lsmatrixcolindex]"));
        for (WebElement td : tds) {
            String r = td.getAttribute("lsmatrixrowindex");
            if (r == null) continue;
            try {
                rows.add(Integer.parseInt(r));
            } catch (Exception ignored) {
            }
        }
        return rows;
    }

    private WebElement findScrollableAncestor(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object res = js.executeScript(
                "let el = arguments[0];" +
                        "while (el) {" +
                        "  const st = window.getComputedStyle(el);" +
                        "  const oy = st.overflowY;" +
                        "  const can = (oy === 'auto' || oy === 'scroll') && el.scrollHeight > el.clientHeight;" +
                        "  if (can) return el;" +
                        "  el = el.parentElement;" +
                        "}" +
                        "return document.scrollingElement || document.documentElement;",
                element
        );
        return (WebElement) res;
    }

    private void scrollDownOnePage(WebElement scroller) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].clientHeight;", scroller
        );
    }

    private void scrollUpOnePage(WebElement scroller) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = Math.max(0, arguments[0].scrollTop - arguments[0].clientHeight);", scroller
        );
    }

    private long getScrollTop(WebElement scroller) {
        Object val = ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scroller);
        return (val instanceof Number) ? ((Number) val).longValue() : -1;
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    // ===== Requested: scroll to row =====

    public boolean scrollToRow(int targetRow, int maxScrolls) {
        WebElement anchor = findFirstDataCellSafe();
        WebElement scroller = findScrollableAncestor(anchor);

        long lastTop = -1;
        int stalls = 0;

        for (int i = 0; i < maxScrolls; i++) {
            Set<Integer> vis = getVisibleRowIndices();
            if (vis.contains(targetRow)) return true;

            int min = vis.stream().min(Integer::compareTo).orElse(0);
            int max = vis.stream().max(Integer::compareTo).orElse(0);

            if (targetRow < min) scrollUpOnePage(scroller);
            else if (targetRow > max) scrollDownOnePage(scroller);
            else scrollDownOnePage(scroller);

            sleep(200);

            long top = getScrollTop(scroller);
            if (top == lastTop) stalls++; else stalls = 0;
            lastTop = top;

            if (stalls >= 4) break;
        }
        return false;
    }

    // ===== Virtualized full read =====

    public List<Map<String, String>> readTableVirtualized(int maxRowsCap, int stallRounds) {
        Map<Integer, String> ids = identifiers();
        if (ids.isEmpty()) throw new RuntimeException("No identifiers found for grid.");

        WebElement anchor = findFirstDataCellSafe();
        WebElement scroller = findScrollableAncestor(anchor);

        Map<Integer, Map<String, String>> collected = new TreeMap<>();
        int stall = 0;
        long lastScrollTop = -1;

        while (collected.size() < maxRowsCap && stall < stallRounds) {
            Set<Integer> visibleRows = getVisibleRowIndices();
            int before = collected.size();

            for (int r : visibleRows) {
                if (r <= 0) continue;
                if (r > maxRowsCap) continue;

                if (!collected.containsKey(r)) {
                    Map<String, String> row = new LinkedHashMap<>();
                    for (var col : ids.entrySet()) {
                        row.put(col.getValue(), getCellText(r, col.getKey()));
                    }
                    if (row.values().stream().allMatch(String::isBlank)) continue;
                    collected.put(r, row);
                }
            }

            int after = collected.size();
            if (after == before) stall++; else stall = 0;

            long currentTop = getScrollTop(scroller);
            if (currentTop == lastScrollTop) stall++;
            lastScrollTop = currentTop;

            scrollDownOnePage(scroller);
            sleep(250);
        }

        return new ArrayList<>(collected.values());
    }
}
