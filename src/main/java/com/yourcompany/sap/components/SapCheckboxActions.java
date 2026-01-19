package com.yourcompany.sap.components;

import org.openqa.selenium.WebElement;

/**
 * Abstraction for checkbox interactions by label.
 */
public interface SapCheckboxActions {

    /** Locates the checkbox element associated with a visible label. */
    WebElement byLabel(String label);

    /** @return true if checkbox is currently checked. */
    boolean isChecked(String label);
}
