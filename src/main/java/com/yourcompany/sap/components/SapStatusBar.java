package com.yourcompany.sap.components;

import org.openqa.selenium.*;

public class SapStatusBar {
    private final WebDriver driver;

    public SapStatusBar(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Create a status bar helper.
     *
     * @param driver webdriver instance
     */
    public SapStatusBar(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Locate the status bar element.
     *
     * @return the status bar WebElement
     */
    public WebElement element() {
        return driver.findElement(By.xpath("//*[contains(@class,'urStatusBar') or contains(@class,'sapUiStatusBar')]") );
    }

    /**
     * Get trimmed text of the status bar.
     */
    public String text() {
        return element().getText().trim();
    }
}
