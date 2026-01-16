package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapCheckbox;

public class SapCheckboxAssert {
    private final SapCheckbox checkbox;
    private final String label;

    public SapCheckboxAssert(SapCheckbox checkbox, String label) {
        this.checkbox = checkbox;
        this.label = label;
    }

    public SapCheckboxAssert assertChecked() {
        if (!checkbox.isChecked(label)) {
            throw new SapAssertException(label + " is NOT checked");
        }
        return this;
    }

    public SapCheckboxAssert assertUnchecked() {
        if (checkbox.isChecked(label)) {
            throw new SapAssertException(label + " is checked");
        }
        return this;
    }
}
