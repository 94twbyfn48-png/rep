package com.yourcompany.sap.components;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.*;

public class SapPopup {
    private final WebDriver driver;

    /**
     * Creates a new SapPopup instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     */
    public SapPopup(Browser browser) {
        this.driver = browser.getDriver();
    }

    /**
     * Executes confirm operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public void confirm() {
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    /**
     * Checks cancel operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public void cancel() {
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
    }
}
