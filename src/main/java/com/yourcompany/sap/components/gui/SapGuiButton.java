package com.yourcompany.sap.components.gui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SapGuiButton extends SapGuiElement {
    public SapGuiButton(WebDriver driver, WebElement el) {
        super(driver, el);
    }
    /**
     * Button wrapper around `SapGuiElement`.
     */
    public SapGuiButton(WebDriver driver, WebElement el) {
        super(driver, el);
    }

    /**
     * Click the button and perform a short sleep to allow UI to react. Prefer
     * callers to use explicit waits where appropriate.
     */
    public void clickAndWait() {
        click();
        // small implicit wait: caller may replace with explicit wait
        try { Thread.sleep(200); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }
}
