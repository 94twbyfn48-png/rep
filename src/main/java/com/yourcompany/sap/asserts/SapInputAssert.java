package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapInput;

public class SapInputAssert {
    private final SapInput input;
    private final String label;

    public SapInputAssert(SapInput input, String label) {
        this.input = input;
        this.label = label;
    }
    /**
     * Create assertions scoped to a specific input identified by label.
     *
     * @param input input helper
     * @param label label text to identify the input
     */
    public SapInputAssert(SapInput input, String label) {
        this.input = input;
        this.label = label;
    }

    /**
     * Assert that the input value equals the expected string.
     */
    public SapInputAssert assertValue(String expected) {
        String actual = input.getValueByLabel(label);
        if (actual == null || !expected.equals(actual)) {
            throw new SapAssertException(label + " expected [" + expected + "] but was [" + actual + "]");
        }
        return this;
    }

    /**
     * Assert that the input value is empty.
     */
    public SapInputAssert assertEmpty() {
        String actual = input.getValueByLabel(label);
        if (actual != null && !actual.isBlank()) {
            throw new SapAssertException(label + " expected empty but was [" + actual + "]");
        }
        return this;
    }

    /**
     * Assert that the input value is not empty.
     */
    public SapInputAssert assertNotEmpty() {
        String actual = input.getValueByLabel(label);
        if (actual == null || actual.isBlank()) {
            throw new SapAssertException(label + " is empty");
        }
        return this;
    }

    /**
     * Assert that the input's maxlength attribute equals the expected value.
     */
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

    /**
     * Assert that the input is enabled.
     */
    public SapInputAssert assertEnabled() {
        if (!input.byLabel(label).isEnabled()) {
            throw new SapAssertException(label + " is disabled");
        }
        return this;
    }

    /**
     * Assert that the input is disabled.
     */
    public SapInputAssert assertDisabled() {
        if (input.byLabel(label).isEnabled()) {
            throw new SapAssertException(label + " is enabled");
        }
        return this;
    }

    /**
     * Assert that the input is readonly (by checking common attributes).
     */
    public SapInputAssert assertReadOnly() {
        String ro = input.byLabel(label).getAttribute("readonly");
        if (ro == null) ro = input.byLabel(label).getAttribute("aria-readonly");
        if (ro == null || !(ro.equalsIgnoreCase("true") || ro.equalsIgnoreCase("readonly"))) {
            throw new SapAssertException(label + " is not readonly");
        }
        return this;
    }
}
