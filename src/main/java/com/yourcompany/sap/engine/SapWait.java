package com.yourcompany.sap.engine;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SapWait {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SapWait(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Create a wait helper for the supplied driver.
     *
     * @param driver the webdriver instance
     */
    public SapWait(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Wait until the document.readyState is 'complete'.
     */
    public void pageReady() {
        wait.until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
    }

    /**
     * Wait until the page's busy indicators are not present.
     * The selectors used here may be adjusted to match your system.
     */
    public void notBusy() {
        wait.until(d -> d.findElements(By.cssSelector(".urBusy, .sapUiLocalBusyIndicator")).isEmpty());
    }
}
