package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import org.openqa.selenium.*;

public class SapInput {
    private final WebDriver driver;

    public SapInput(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement byLabel(String label) {
        return SapLocator.byLabelHeuristic(driver, label);
    }

    public void setByLabel(String label, String value) {
        WebElement el = byLabel(label);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, value);
    }

    public String getValueByLabel(String label) {
        WebElement el = byLabel(label);
        String v = el.getAttribute("value");
        return v != null ? v : el.getText();
    }
}
