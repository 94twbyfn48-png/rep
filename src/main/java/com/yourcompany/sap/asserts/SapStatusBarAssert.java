package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapStatusBar;

public class SapStatusBarAssert {
    private final SapStatusBar statusBar;

    /**
     * Creates a new SapStatusBarAssert instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param statusBar input parameter
     */
    public SapStatusBarAssert(SapStatusBar statusBar) {
        this.statusBar = statusBar;
    }

    /**
     * Verifies assertMessageContains operation.
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
    public SapStatusBarAssert assertMessageContains(String expected) {
        String actual = statusBar.text();
        if (!actual.toLowerCase().contains(expected.toLowerCase())) {
            throw new SapAssertException("Status bar does not contain [" + expected + "], was [" + actual + "]");
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
    public SapStatusBarAssert assertNotEmpty() {
        String actual = statusBar.text();
        if (actual.isBlank()) {
            throw new SapAssertException("Status bar is empty");
        }
        return this;
    }
}
