package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SapDropdown {
    private final WebDriver driver;

    /**
     * Creates a new SapDropdown instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     */
    public SapDropdown(Browser browser) {
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
    public Select byLabel(String label) {
        WebElement el = SapLocator.byLabelHeuristic(driver, label);
        return new Select(el);
    }
}
