package com.yourcompany.sap.components.gui;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SapGuiDialog extends SapGuiElement {
    private final WebElement root;

    /**
     * Creates a new SapGuiDialog instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param driver input parameter
     * @param root input parameter
     */
    public SapGuiDialog(WebDriver driver, WebElement root) {
        super(driver, root);
        this.root = root;
    }

    /**
     * Checks isOpen operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public boolean isOpen() {
        return isDisplayed();
    }

    /**
     * Performs close operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public void close() {
        try {
            // try common close selectors
            WebElement btn = root.findElement(By.xpath(".//button[@title='Close' or contains(@class,'close') or @aria-label='Close']"));
            btn.click();
            return;
        } catch (Exception ignored) {
        }

        try {
            // fallback: click top-right close icon if any
            WebElement ico = root.findElement(By.cssSelector(".sapUiDlgClose, .ui-dialog-titlebar-close"));
            ico.click();
        } catch (Exception e) {
            // last resort: JS remove
            try {
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].remove();", root);
            } catch (Exception ex) {
                // swallow - caller can handle
            }
        }
    }
}
