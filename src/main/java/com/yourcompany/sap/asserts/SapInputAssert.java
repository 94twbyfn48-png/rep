package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapInput;

public class SapInputAssert {
    private final SapInput input;
    private final String label;

    /**
     * Creates a new SapInputAssert instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param input input parameter
     * @param label input parameter
     */
    public SapInputAssert(SapInput input, String label) {
        this.input = input;
        this.label = label;
    }

    /**
     * Verifies assertValue operation.
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
    public SapInputAssert assertValue(String expected) {
        String actual = input.getValueByLabel(label);
        if (actual == null || !expected.equals(actual)) {
            throw new SapAssertException(label + " expected [" + expected + "] but was [" + actual + "]");
        }
        return this;
    }

    /**
     * Verifies assertEmpty operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public SapInputAssert assertEmpty() {
        String actual = input.getValueByLabel(label);
        if (actual != null && !actual.isBlank()) {
            throw new SapAssertException(label + " expected empty but was [" + actual + "]");
        }
        return this;
    }

    /**
     * Verifies assertNotEmpty operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public SapInputAssert assertNotEmpty() {
        String actual = input.getValueByLabel(label);
        if (actual == null || actual.isBlank()) {
            throw new SapAssertException(label + " is empty");
        }
        return this;
    }

    /**
     * Verifies assertMaxLength operation.
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
     * Verifies assertEnabled operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public SapInputAssert assertEnabled() {
        if (!input.byLabel(label).isEnabled()) {
            throw new SapAssertException(label + " is disabled");
        }
        return this;
    }

    /**
     * Verifies assertDisabled operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public SapInputAssert assertDisabled() {
        if (input.byLabel(label).isEnabled()) {
            throw new SapAssertException(label + " is enabled");
        }
        return this;
    }

    /**
     * Verifies assertReadOnly operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
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
