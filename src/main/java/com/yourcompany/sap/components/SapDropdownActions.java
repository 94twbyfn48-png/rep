package com.yourcompany.sap.components;

import org.openqa.selenium.support.ui.Select;

/**
 * Abstraction for dropdown/combobox interactions by label.
 */
public interface SapDropdownActions {

    /**
     * Locates a dropdown by label and returns a Selenium {@link Select} wrapper.
     */
    Select byLabel(String label);
}
