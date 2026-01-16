package com.yourcompany.sap.components.gui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SapGuiButton extends SapGuiElement {
    public SapGuiButton(WebDriver driver, WebElement el) {
        super(driver, el);
    }

    public void clickAndWait() {
        click();
        // small implicit wait: caller may replace with explicit wait
        try { Thread.sleep(200); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }
}
