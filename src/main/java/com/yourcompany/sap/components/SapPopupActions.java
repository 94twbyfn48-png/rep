package com.yourcompany.sap.components;

/**
 * Abstraction for common confirmation/cancellation popups.
 */
public interface SapPopupActions {

    /** Clicks the primary confirmation action (e.g., OK / Yes). */
    void confirm();

    /** Clicks the cancellation action (e.g., Cancel / No). */
    void cancel();
}
