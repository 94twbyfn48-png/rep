package com.yourcompany.sap.asserts;

import java.util.ArrayList;
import java.util.List;

public class SoftSapAssert {
    private final List<String> errors = new ArrayList<>();

    /**
     * Verifies check operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param assertion input parameter
     * @param context input parameter
     *
     * @return operation result
     */
    public void check(Runnable assertion, String context) {
        try {
            assertion.run();
        } catch (Exception e) {
            errors.add(context + " -> " + e.getMessage());
        }
    }

    /**
     * Verifies assertAll operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public void assertAll() {
        if (!errors.isEmpty()) {
            throw new AssertionError(String.join("\n", errors));
        }
    }
}
