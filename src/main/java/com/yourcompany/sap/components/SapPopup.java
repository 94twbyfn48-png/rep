package com.yourcompany.sap.components;

import org.openqa.selenium.*;

public class SapPopup {
    private final WebDriver driver;

    public SapPopup(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Create a popup helper bound to the provided driver.
     *
     * @param driver webdriver instance
     */
    public SapPopup(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Confirm the active popup by sending Enter to the active element.
     */
    public void confirm() {
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    /**
     * Cancel the active popup by sending Escape to the active element.
     */
    public void cancel() {
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
    }
}
