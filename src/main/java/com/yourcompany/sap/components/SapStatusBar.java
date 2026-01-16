package com.yourcompany.sap.components;

import org.openqa.selenium.*;

public class SapStatusBar {
    private final WebDriver driver;

    public SapStatusBar(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement element() {
        return driver.findElement(By.xpath("//*[contains(@class,'urStatusBar') or contains(@class,'sapUiStatusBar')]"));
    }

    public String text() {
        return element().getText().trim();
    }
}
