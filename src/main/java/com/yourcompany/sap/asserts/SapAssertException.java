package com.yourcompany.sap.asserts;

public class SapAssertException extends RuntimeException {
    /**
     * Creates a new SapAssertException instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param message input parameter
     */
    public SapAssertException(String message) {
        super(message);
    }
}
