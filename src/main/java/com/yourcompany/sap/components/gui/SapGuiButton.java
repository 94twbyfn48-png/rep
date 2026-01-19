package com.yourcompany.sap.components.gui;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SapGuiButton extends SapGuiElement {
    /**
     * Creates a new SapGuiButton instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param driver input parameter
     * @param el input parameter
     */
    public SapGuiButton(WebDriver driver, WebElement el) {
        super(driver, el);
    }

    /**
     * Performs clickAndWait operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public void clickAndWait() {
        click();
        // small implicit wait: caller may replace with explicit wait
        try { Thread.sleep(200); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }
}
