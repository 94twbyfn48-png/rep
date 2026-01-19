package com.yourcompany.sap.asserts;

import java.util.ArrayList;
import java.util.List;

public class SoftSapAssert {
    private final List<String> errors = new ArrayList<>();

    public void check(Runnable assertion, String context) {
        try {
            assertion.run();
        } catch (Exception e) {
            errors.add(context + " -> " + e.getMessage());
        }
    }

    /**
     * Execute an assertion lambda and collect any failures as soft errors.
     *
     * @param assertion runnable performing assertion
     * @param context   human-friendly context used in failure messages
     */
    public void check(Runnable assertion, String context) {
        try {
            assertion.run();
        } catch (Exception e) {
            errors.add(context + " -> " + e.getMessage());
        }
    }

    /**
     * If any soft assertions failed, throw a combined AssertionError.
     */
    public void assertAll() {
        if (!errors.isEmpty()) {
            throw new AssertionError(String.join("\n", errors));
        }
    }
}
