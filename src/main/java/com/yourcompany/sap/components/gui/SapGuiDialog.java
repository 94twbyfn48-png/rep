package com.yourcompany.sap.components.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SapGuiDialog extends SapGuiElement {
    private final WebElement root;

    public SapGuiDialog(WebDriver driver, WebElement root) {
        super(driver, root);
        this.root = root;
    }

    public boolean isOpen() {
        return isDisplayed();
    }

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
