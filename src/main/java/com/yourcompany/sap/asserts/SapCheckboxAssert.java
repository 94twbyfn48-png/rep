package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapCheckbox;

public class SapCheckboxAssert {
    private final SapCheckbox checkbox;
    private final String label;

    /**
     * Creates a new SapCheckboxAssert instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param checkbox input parameter
     * @param label input parameter
     */
    public SapCheckboxAssert(SapCheckbox checkbox, String label) {
        this.checkbox = checkbox;
        this.label = label;
    }

    /**
     * Verifies assertChecked operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public SapCheckboxAssert assertChecked() {
        if (!checkbox.isChecked(label)) {
            throw new SapAssertException(label + " is NOT checked");
        }
        return this;
    }

    /**
     * Verifies assertUnchecked operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public SapCheckboxAssert assertUnchecked() {
        if (checkbox.isChecked(label)) {
            throw new SapAssertException(label + " is checked");
        }
        return this;
    }
}
