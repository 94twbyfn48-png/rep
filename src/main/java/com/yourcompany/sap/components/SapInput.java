package com.yourcompany.sap.components;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import com.yourcompany.sap.engine.SapLocator;
import org.openqa.selenium.*;

public class SapInput implements SapInputActions {
    private final WebDriver driver;

    /**
     * Creates a new SapInput instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     */
    public SapInput(Browser browser) {
        this.driver = browser.getDriver();
    }

    /**
     * Executes byLabel operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param label input parameter
     *
     * @return operation result
     */
    public WebElement byLabel(String label) {
        return SapLocator.byLabelHeuristic(driver, label);
    }

    /**
     * Sets setByLabel operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param label input parameter
     * @param value input parameter
     *
     * @return operation result
     */
    public void setByLabel(String label, String value) {
        WebElement el = byLabel(label);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, value);
    }

    /**
     * Gets getValueByLabel operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param label input parameter
     *
     * @return operation result
     */
    public String getValueByLabel(String label) {
        WebElement el = byLabel(label);
        String v = el.getAttribute("value");
        return v != null ? v : el.getText();
    }
}
