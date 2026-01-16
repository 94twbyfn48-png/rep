package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapInput;

public class SapInputAssert {
    private final SapInput input;
    private final String label;

    public SapInputAssert(SapInput input, String label) {
        this.input = input;
        this.label = label;
    }

    public SapInputAssert assertValue(String expected) {
        String actual = input.getValueByLabel(label);
        if (actual == null || !expected.equals(actual)) {
            throw new SapAssertException(label + " expected [" + expected + "] but was [" + actual + "]");
        }
        return this;
    }

    public SapInputAssert assertEmpty() {
        String actual = input.getValueByLabel(label);
        if (actual != null && !actual.isBlank()) {
            throw new SapAssertException(label + " expected empty but was [" + actual + "]");
        }
        return this;
    }

    public SapInputAssert assertNotEmpty() {
        String actual = input.getValueByLabel(label);
        if (actual == null || actual.isBlank()) {
            throw new SapAssertException(label + " is empty");
        }
        return this;
    }

    public SapInputAssert assertMaxLength(int expected) {
        String maxLenStr = input.byLabel(label).getAttribute("maxlength");
        if (maxLenStr == null || maxLenStr.isBlank()) {
            throw new SapAssertException(label + " has no maxlength attribute");
        }
        int actual = Integer.parseInt(maxLenStr);
        if (actual != expected) {
            throw new SapAssertException(label + " maxlength expected " + expected + " but was " + actual);
        }
        return this;
    }

    public SapInputAssert assertEnabled() {
        if (!input.byLabel(label).isEnabled()) {
            throw new SapAssertException(label + " is disabled");
        }
        return this;
    }

    public SapInputAssert assertDisabled() {
        if (input.byLabel(label).isEnabled()) {
            throw new SapAssertException(label + " is enabled");
        }
        return this;
    }

    public SapInputAssert assertReadOnly() {
        String ro = input.byLabel(label).getAttribute("readonly");
        if (ro == null) ro = input.byLabel(label).getAttribute("aria-readonly");
        if (ro == null || !(ro.equalsIgnoreCase("true") || ro.equalsIgnoreCase("readonly"))) {
            throw new SapAssertException(label + " is not readonly");
        }
        return this;
    }
}
