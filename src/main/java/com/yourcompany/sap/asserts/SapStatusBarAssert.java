package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapStatusBar;

public class SapStatusBarAssert {
    private final SapStatusBar statusBar;

    public SapStatusBarAssert(SapStatusBar statusBar) {
        this.statusBar = statusBar;
    }

    public SapStatusBarAssert assertMessageContains(String expected) {
        String actual = statusBar.text();
        if (!actual.toLowerCase().contains(expected.toLowerCase())) {
            throw new SapAssertException("Status bar does not contain [" + expected + "], was [" + actual + "]");
        }
        return this;
    }

    public SapStatusBarAssert assertNotEmpty() {
        String actual = statusBar.text();
        if (actual.isBlank()) {
            throw new SapAssertException("Status bar is empty");
        }
        return this;
    }
}
