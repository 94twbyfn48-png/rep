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

    public void pageReady() {
        wait.until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
    }

    // Busy indicator selector’ları sistemine göre güncellenebilir
    public void notBusy() {
        wait.until(d -> d.findElements(By.cssSelector(".urBusy, .sapUiLocalBusyIndicator")).isEmpty());
    }
}
