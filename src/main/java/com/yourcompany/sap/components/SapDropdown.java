package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SapDropdown {
    private final WebDriver driver;

    public SapDropdown(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Create a dropdown helper for the provided driver.
     *
     * @param driver webdriver instance
     */
    public SapDropdown(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Find a select element by label heuristics and return a Selenium {@link Select} wrapper.
     *
     * @param label label text identifying the select
     * @return Select wrapper
     */
    public Select byLabel(String label) {
        WebElement el = SapLocator.byLabelHeuristic(driver, label);
        return new Select(el);
    }
}
