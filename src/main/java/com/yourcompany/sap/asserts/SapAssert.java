package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.*;
import org.openqa.selenium.WebDriver;

public class SapAssert {
    private final WebDriver driver;

    public SapAssert(WebDriver driver) {
        this.driver = driver;
    }

    public SapGrid grid() { return new SapGrid(driver); }
    public SapInput inputComponent() { return new SapInput(driver); }
    public SapCheckbox checkboxComponent() { return new SapCheckbox(driver); }
    public SapDropdown dropdownComponent() { return new SapDropdown(driver); }
    public SapStatusBar statusBarComponent() { return new SapStatusBar(driver); }

    public SapRowAssert row(int rowIndex) { return new SapRowAssert(grid(), rowIndex); }
    public SapTableAssert table(int maxRowsCap) { return new SapTableAssert(grid(), maxRowsCap); }

    public SapInputAssert input(String label) { return new SapInputAssert(inputComponent(), label); }
    public SapCheckboxAssert checkbox(String label) { return new SapCheckboxAssert(checkboxComponent(), label); }
    public SapDropdownAssert dropdown(String label) { return new SapDropdownAssert(dropdownComponent(), label); }
    public SapStatusBarAssert statusBar() { return new SapStatusBarAssert(statusBarComponent()); }
}
