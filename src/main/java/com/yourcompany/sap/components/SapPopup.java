package com.yourcompany.sap.components;

import org.openqa.selenium.*;

public class SapPopup {
    private final WebDriver driver;

    public SapPopup(WebDriver driver) {
        this.driver = driver;
    }

    public void confirm() {
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public void cancel() {
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
    }
}
