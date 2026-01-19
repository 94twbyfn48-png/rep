package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapCheckbox;

public class SapCheckboxAssert {
    private final SapCheckbox checkbox;
    private final String label;

    public SapCheckboxAssert(SapCheckbox checkbox, String label) {
        this.checkbox = checkbox;
        this.label = label;
    }

    /**
     * Create checkbox assertions for the control found by label.
     *
     * @param checkbox checkbox helper
     * @param label    label text identifying the checkbox
     */
    public SapCheckboxAssert(SapCheckbox checkbox, String label) {
        this.checkbox = checkbox;
        this.label = label;
    }

    /**
     * Assert that the checkbox is checked.
     */
    public SapCheckboxAssert assertChecked() {
        if (!checkbox.isChecked(label)) {
            throw new SapAssertException(label + " is NOT checked");
        }
        return this;
    }

    /**
     * Assert that the checkbox is unchecked.
     */
    public SapCheckboxAssert assertUnchecked() {
        if (checkbox.isChecked(label)) {
            throw new SapAssertException(label + " is checked");
        }
        return this;
    }
}
