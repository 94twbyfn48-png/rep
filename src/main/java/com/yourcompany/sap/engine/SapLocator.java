package com.yourcompany.sap.engine;

import org.openqa.selenium.*;

public class SapLocator {

    /**
     * Executes byLabelForId operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param driver input parameter
     * @param labelText input parameter
     *
     * @return operation result
     */
    public static WebElement byLabelForId(WebDriver driver, String labelText) {
        WebElement label = driver.findElement(
                By.xpath("//label[contains(normalize-space(.),'" + escape(labelText) + "')]")
        );
        String forId = label.getAttribute("for");
        if (forId == null || forId.isBlank()) {
            throw new NoSuchElementException("Label found but has no @for: " + labelText);
        }
        return driver.findElement(By.id(forId));
    }

    /**
     * Executes byLabelHeuristic operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param driver input parameter
     * @param labelText input parameter
     *
     * @return operation result
     */
    public static WebElement byLabelHeuristic(WebDriver driver, String labelText) {
        try {
            return byLabelForId(driver, labelText);
        } catch (Exception ignored) {
        }

        try {
            return driver.findElement(By.xpath("//*[contains(@aria-label,'" + escape(labelText) + "')]"));
        } catch (Exception ignored) {
        }

        return driver.findElement(By.xpath("//*[contains(@placeholder,'" + escape(labelText) + "')]"));
    }

    /**
     * Executes escape operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param s input parameter
     *
     * @return operation result
     */
    private static String escape(String s) {
        return s.replace("'", "’");
    }
}
