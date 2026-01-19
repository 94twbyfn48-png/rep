package com.yourcompany.sap.engine;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SapWait {
    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Creates a new SapWait instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     */
    public SapWait(Browser browser) {
        this.driver = browser.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Executes pageReady operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public void pageReady() {
        wait.until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
    }

    // Busy indicator selector’ları sistemine göre güncellenebilir
    public void notBusy() {
        wait.until(d -> d.findElements(By.cssSelector(".urBusy, .sapUiLocalBusyIndicator")).isEmpty());
    }
}
