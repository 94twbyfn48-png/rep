package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapDropdown;
import org.openqa.selenium.support.ui.Select;

public class SapDropdownAssert {
    private final SapDropdown dropdown;
    private final String label;

    public SapDropdownAssert(SapDropdown dropdown, String label) {
        this.dropdown = dropdown;
        this.label = label;
    }

    public SapDropdownAssert assertSelected(String expected) {
        Select sel = dropdown.byLabel(label);
        String actual = sel.getFirstSelectedOption().getText().trim();
        if (!expected.equals(actual)) {
            throw new SapAssertException(label + " expected [" + expected + "] but was [" + actual + "]");
        }
        return this;
    }

    public SapDropdownAssert assertOptionsContain(String optionText) {
        Select sel = dropdown.byLabel(label);
        boolean found = sel.getOptions().stream().anyMatch(o -> optionText.equals(o.getText().trim()));
        if (!found) {
            throw new SapAssertException(label + " does not contain option [" + optionText + "]");
        }
        return this;
    }
}
