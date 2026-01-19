package com.yourcompany.sap.engine;

/**
 * Abstraction for wait utilities used in SAP GUI for HTML flows.
 */
public interface SapWaitActions {

    /** Waits until the DOM reports document.readyState == 'complete'. */
    void pageReady();

    /** Waits until SAP busy indicator overlays are no longer visible. */
    void notBusy();
}
