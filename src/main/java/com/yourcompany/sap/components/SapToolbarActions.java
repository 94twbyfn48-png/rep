package com.yourcompany.sap.components;

/**
 * Abstraction for common toolbar actions.
 */
public interface SapToolbarActions {

    /**
     * Clicks a toolbar button by its visible text.
     *
     * @param text exact (or near-exact) button text rendered in the toolbar
     */
    void clickButtonByText(String text);
}
