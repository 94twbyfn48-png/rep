package com.yourcompany.sap.components;

import org.openqa.selenium.WebElement;

/**
 * Abstraction for reading SAP status bar messages.
 */
public interface SapStatusBarActions {

    /** @return the underlying status bar root element. */
    WebElement element();

    /** @return the visible message text currently shown in the status bar. */
    String text();
}
