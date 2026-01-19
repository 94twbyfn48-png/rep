package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Checkbox helper for SAP GUI for HTML.
 */
public class SapCheckbox implements SapCheckboxActions {

    private final WebDriver driver;

    /**
     * Creates a new SapCheckbox instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     */
    public SapCheckbox(Browser browser) {
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
     * Checks isChecked operation.
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
    public boolean isChecked(String label) {
        WebElement el = byLabel(label);
        return el.isSelected() || "true".equalsIgnoreCase(el.getAttribute("aria-checked"));
    }
}
