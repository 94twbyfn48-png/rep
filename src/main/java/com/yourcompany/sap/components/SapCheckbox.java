package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import org.openqa.selenium.*;

public class SapCheckbox {
    private final WebDriver driver;

    public SapCheckbox(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Create a checkbox helper bound to a WebDriver.
     *
     * @param driver webdriver instance
     */
    public SapCheckbox(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Locate the checkbox element by using label heuristics.
     *
     * @param label label text identifying the checkbox
     * @return located WebElement
     */
    public WebElement byLabel(String label) {
        return SapLocator.byLabelHeuristic(driver, label);
    }

    /**
     * Return true if the checkbox identified by label is checked.
     *
     * @param label label text identifying the checkbox
     */
    public boolean isChecked(String label) {
        WebElement el = byLabel(label);
        return el.isSelected() || "true".equalsIgnoreCase(el.getAttribute("aria-checked"));
    }
}
