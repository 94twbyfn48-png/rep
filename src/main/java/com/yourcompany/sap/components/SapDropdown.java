package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SapDropdown {
    private final WebDriver driver;

    public SapDropdown(WebDriver driver) {
        this.driver = driver;
    }

    public Select byLabel(String label) {
        WebElement el = SapLocator.byLabelHeuristic(driver, label);
        return new Select(el);
    }
}
