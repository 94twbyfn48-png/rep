package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.*;
import org.openqa.selenium.WebDriver;

public class SapAssert {
    private final WebDriver driver;

    public SapAssert(WebDriver driver) {
        this.driver = driver;
    }
    /**
     * Create an assertion helper bound to the given driver.
     *
     * @param driver webdriver used for assertions
     */
    public SapAssert(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Return a `SapGrid` assertion helper.
     */
    public SapGrid grid() { return new SapGrid(driver); }

    /**
     * Return a `SapInput` helper instance.
     */
    public SapInput inputComponent() { return new SapInput(driver); }

    /**
     * Return a `SapCheckbox` helper instance.
     */
    public SapCheckbox checkboxComponent() { return new SapCheckbox(driver); }

    /**
     * Return a `SapDropdown` helper instance.
     */
    public SapDropdown dropdownComponent() { return new SapDropdown(driver); }

    /**
     * Return a `SapStatusBar` helper instance.
     */
    public SapStatusBar statusBarComponent() { return new SapStatusBar(driver); }

    /**
     * Start assertions for a specific row.
     *
     * @param rowIndex zero-based row index
     */
    public SapRowAssert row(int rowIndex) { return new SapRowAssert(grid(), rowIndex); }

    /**
     * Start assertions for a table read with a maximum row cap.
     *
     * @param maxRowsCap maximum rows to read for assertions
     */
    public SapTableAssert table(int maxRowsCap) { return new SapTableAssert(grid(), maxRowsCap); }

    /**
     * Assertions scoped to an input identified by label.
     */
    public SapInputAssert input(String label) { return new SapInputAssert(inputComponent(), label); }

    /**
     * Assertions scoped to a checkbox identified by label.
     */
    public SapCheckboxAssert checkbox(String label) { return new SapCheckboxAssert(checkboxComponent(), label); }

    /**
     * Assertions scoped to a dropdown identified by label.
     */
    public SapDropdownAssert dropdown(String label) { return new SapDropdownAssert(dropdownComponent(), label); }

    /**
     * Assertions for the status bar.
     */
    public SapStatusBarAssert statusBar() { return new SapStatusBarAssert(statusBarComponent()); }
}
