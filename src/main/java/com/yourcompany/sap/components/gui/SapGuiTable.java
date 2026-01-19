package com.yourcompany.sap.components.gui;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SapGuiTable extends SapGuiElement {
    private final WebElement tableRoot;

    /**
     * Creates a new SapGuiTable instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param driver input parameter
     * @param tableRoot input parameter
     */
    public SapGuiTable(WebDriver driver, WebElement tableRoot) {
        super(driver, tableRoot);
        this.tableRoot = tableRoot;
    }

    /**
     * Executes rows operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public List<WebElement> rows() {
        try {
            return tableRoot.findElements(By.xpath(".//tr"));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Gets getCell operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param rowIndex input parameter
     * @param colIndex input parameter
     *
     * @return operation result
     */
    public WebElement getCell(int rowIndex, int colIndex) {
        List<WebElement> r = rows();
        if (rowIndex < 0 || rowIndex >= r.size()) throw new IndexOutOfBoundsException("rowIndex");
        List<WebElement> cells = r.get(rowIndex).findElements(By.xpath(".//th|.//td"));
        if (colIndex < 0 || colIndex >= cells.size()) throw new IndexOutOfBoundsException("colIndex");
        return cells.get(colIndex);
    }

    /**
     * Gets findRowIndexByCellText operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param text input parameter
     *
     * @return operation result
     */
    public int findRowIndexByCellText(String text) {
        List<WebElement> r = rows();
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i).getText().contains(text)) return i;
        }
        return -1;
    }
}
