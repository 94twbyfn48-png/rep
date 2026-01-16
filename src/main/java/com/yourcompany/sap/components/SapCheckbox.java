package com.yourcompany.sap.components;

import com.yourcompany.sap.engine.SapLocator;
import org.openqa.selenium.*;

public class SapCheckbox {
    private final WebDriver driver;

    public SapCheckbox(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement byLabel(String label) {
        return SapLocator.byLabelHeuristic(driver, label);
    }

    public boolean isChecked(String label) {
        WebElement el = byLabel(label);
        return el.isSelected() || "true".equalsIgnoreCase(el.getAttribute("aria-checked"));
    }
}
