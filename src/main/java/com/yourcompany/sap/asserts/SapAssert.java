package com.yourcompany.sap.asserts;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import com.yourcompany.sap.components.SapCheckbox;
import com.yourcompany.sap.components.SapDropdown;
import com.yourcompany.sap.components.SapGrid;
import com.yourcompany.sap.components.SapInput;
import com.yourcompany.sap.components.SapStatusBar;

/**
 * Root assertion facade that creates specific assertion helpers.
 */
public class SapAssert {

    private final Browser browser;

    /**
     * @param browser Your framework Browser wrapper.
     */
    /**
     * Creates a new SapAssert instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     */
    public SapAssert(Browser browser) {
        this.browser = browser;
    }

    /** @return Grid helper bound to current Browser instance. */
    public SapGrid grid() { return new SapGrid(browser); }

    /** @return Input helper bound to current Browser instance. */
    public SapInput inputComponent() { return new SapInput(browser); }

    /** @return Checkbox helper bound to current Browser instance. */
    public SapCheckbox checkboxComponent() { return new SapCheckbox(browser); }

    /** @return Dropdown helper bound to current Browser instance. */
    public SapDropdown dropdownComponent() { return new SapDropdown(browser); }

    /** @return StatusBar helper bound to current Browser instance. */
    public SapStatusBar statusBarComponent() { return new SapStatusBar(browser); }

    /** @return Row assertion helper. */
    public SapRowAssert row(int rowIndex) { return new SapRowAssert(grid(), rowIndex); }

    /** @return Table assertion helper. */
    public SapTableAssert table(int maxRowsCap) { return new SapTableAssert(grid(), maxRowsCap); }

    /** @return Input assertion helper for the given label. */
    public SapInputAssert input(String label) { return new SapInputAssert(inputComponent(), label); }

    /** @return Checkbox assertion helper for the given label. */
    public SapCheckboxAssert checkbox(String label) { return new SapCheckboxAssert(checkboxComponent(), label); }

    /** @return Dropdown assertion helper for the given label. */
    public SapDropdownAssert dropdown(String label) { return new SapDropdownAssert(dropdownComponent(), label); }

    /** @return Status bar assertion helper. */
    public SapStatusBarAssert statusBar() { return new SapStatusBarAssert(statusBarComponent()); }
}
