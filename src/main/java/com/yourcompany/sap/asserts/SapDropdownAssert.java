package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapDropdown;
import org.openqa.selenium.support.ui.Select;

public class SapDropdownAssert {
    private final SapDropdown dropdown;
    private final String label;

    /**
     * Creates a new SapDropdownAssert instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param dropdown input parameter
     * @param label input parameter
     */
    public SapDropdownAssert(SapDropdown dropdown, String label) {
        this.dropdown = dropdown;
        this.label = label;
    }

    /**
     * Verifies assertSelected operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param expected input parameter
     *
     * @return operation result
     */
    public SapDropdownAssert assertSelected(String expected) {
        Select sel = dropdown.byLabel(label);
        String actual = sel.getFirstSelectedOption().getText().trim();
        if (!expected.equals(actual)) {
            throw new SapAssertException(label + " expected [" + expected + "] but was [" + actual + "]");
        }
        return this;
    }

    /**
     * Verifies assertOptionsContain operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param optionText input parameter
     *
     * @return operation result
     */
    public SapDropdownAssert assertOptionsContain(String optionText) {
        Select sel = dropdown.byLabel(label);
        boolean found = sel.getOptions().stream().anyMatch(o -> optionText.equals(o.getText().trim()));
        if (!found) {
            throw new SapAssertException(label + " does not contain option [" + optionText + "]");
        }
        return this;
    }
}
