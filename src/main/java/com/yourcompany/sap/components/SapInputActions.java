package com.yourcompany.sap.components;

import org.openqa.selenium.WebElement;

/**
 * Abstraction for interacting with SAP GUI for HTML input fields.
 *
 * <p>Pages should prefer depending on this interface rather than the concrete
 * {@link SapInput} class when you want easier testing/mocking or when you want
 * to swap the underlying implementation without changing page code.
 */
public interface SapInputActions {

    /**
     * Locates an input element by using a label heuristic.
     *
     * <p>Typical SAP GUI for HTML screens render labels separately from inputs.
     * Implementations may match the label text and then find the most likely
     * corresponding input next to it.
     *
     * @param label Visible label text (case/locale sensitive depending on system).
     * @return The matched input element.
     */
    WebElement byLabel(String label);

    /**
     * Sets the value of an input that is identified by its label text.
     *
     * <p>Implementations should click/focus the input, clear existing value, and
     * then send the new value.
     *
     * @param label Visible label text.
     * @param value Value to set.
     */
    void setByLabel(String label, String value);

    /**
     * Reads the current value of an input identified by label.
     *
     * <p>Implementations may read the <code>value</code> attribute first and fall
     * back to element text if needed.
     *
     * @param label Visible label text.
     * @return Current value (never null; may be empty).
     */
    String getValueByLabel(String label);
}
