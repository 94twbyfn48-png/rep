package com.yourcompany.sap.components;

import org.openqa.selenium.*;

public class SapToolbar {
    private final WebDriver driver;

    public SapToolbar(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonByText(String text) {
        driver.findElement(By.xpath("//button[.//*[normalize-space(text())='" + text + "']]")).click();
    }
}
