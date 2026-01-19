package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import org.openqa.selenium.*;

public class SapInput {
    private final WebDriver driver;

    public SapInput(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Create an input helper bound to a WebDriver.
     *
     * @param driver webdriver instance
     */
    public SapInput(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Find an input element by label heuristics.
     *
     * @param label label text
     * @return the located element
     */
    public WebElement byLabel(String label) {
        return SapLocator.byLabelHeuristic(driver, label);
    }

    /**
     * Set the input value for the element identified by label.
     * This uses a select-all + delete then types the provided value.
     *
     * @param label label text
     * @param value value to set
     */
    public void setByLabel(String label, String value) {
        WebElement el = byLabel(label);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, value);
    }

    /**
     * Get the value of an input by label. Falls back to element text when attribute missing.
     *
     * @param label label text
     * @return value or text
     */
    public String getValueByLabel(String label) {
        WebElement el = byLabel(label);
        String v = el.getAttribute("value");
        return v != null ? v : el.getText();
    }
}
