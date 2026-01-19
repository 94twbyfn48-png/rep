package com.yourcompany.sap.components;

import org.openqa.selenium.*;

public class SapToolbar {
    private final WebDriver driver;

    public SapToolbar(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Create a toolbar helper.
     *
     * @param driver webdriver instance
     */
    public SapToolbar(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Click a toolbar button whose inner element's text matches the provided text.
     *
     * @param text visible text of the button
     */
    public void clickButtonByText(String text) {
        driver.findElement(By.xpath("//button[.//*[normalize-space(text())='" + text + "']]")).click();
    }
}
