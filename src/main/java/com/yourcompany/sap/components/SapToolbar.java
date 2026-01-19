package com.yourcompany.sap.components;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.*;

public class SapToolbar {
    private final WebDriver driver;

    /**
     * Creates a new SapToolbar instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     */
    public SapToolbar(Browser browser) {
        this.driver = browser.getDriver();
    }

    /**
     * Performs clickButtonByText operation.
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
    public void clickButtonByText(String text) {
        driver.findElement(By.xpath("//button[.//*[normalize-space(text())='" + text + "']]")).click();
    }
}
