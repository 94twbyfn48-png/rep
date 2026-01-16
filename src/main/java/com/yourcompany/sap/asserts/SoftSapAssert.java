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

    public void assertAll() {
        if (!errors.isEmpty()) {
            throw new AssertionError(String.join("\n", errors));
        }
    }
}
