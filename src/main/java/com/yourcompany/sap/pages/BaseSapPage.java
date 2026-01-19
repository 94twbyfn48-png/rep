package com.yourcompany.sap.pages;

import com.yourcompany.sap.asserts.SapAssertFacade;
import com.yourcompany.sap.asserts.SapAssert;
import com.yourcompany.sap.components.SapCheckboxActions;
import com.yourcompany.sap.components.SapCheckbox;
import com.yourcompany.sap.components.SapDropdownActions;
import com.yourcompany.sap.components.SapDropdown;
import com.yourcompany.sap.components.SapGridActions;
import com.yourcompany.sap.components.SapGrid;
import com.yourcompany.sap.components.SapInputActions;
import com.yourcompany.sap.components.SapInput;
import com.yourcompany.sap.components.SapPopupActions;
import com.yourcompany.sap.components.SapPopup;
import com.yourcompany.sap.components.SapStatusBarActions;
import com.yourcompany.sap.components.SapStatusBar;
import com.yourcompany.sap.components.SapToolbarActions;
import com.yourcompany.sap.components.SapToolbar;
import com.yourcompany.sap.engine.SapKeyboardActions;
import com.yourcompany.sap.engine.SapKeyboard;
import com.yourcompany.sap.engine.SapWaitActions;
import com.yourcompany.sap.engine.SapWait;

import javax.annotation.PostConstruct;

// TODO: Update these imports to match your framework packages.
import com.thy.testlibrary.pages.AbstractPage;
import com.thy.testlibrary.browser.Browser;

/**
 * Base class for SAP GUI for HTML page objects.
 *
 * <p>Designed to be used with your existing framework:
 * <ul>
 *   <li>{@code AbstractPage} is Spring-managed and exposes {@code protected Browser browser}.</li>
 *   <li>{@code Browser} wraps Selenium and provides {@code getDriver()}.</li>
 * </ul>
 */
public abstract class BaseSapPage extends AbstractPage {

    /** Keyboard shortcuts and key-chords (F-keys, Ctrl/Shift combos, etc.). */
    protected SapKeyboardActions keys;

    /** Waiting utilities (document ready, busy indicator, etc.). */
    protected SapWaitActions wait;

    /** Assertion facade for common SAP UI checks. */
    protected SapAssertFacade assertThat;

    /** SAP table/grid helpers (virtualization-aware reading/scrolling). */
    protected SapGridActions grid;

    /** Generic input helpers (text inputs). */
    protected SapInputActions input;

    /** Checkbox helpers. */
    protected SapCheckboxActions checkbox;

    /** Dropdown / combobox helpers. */
    protected SapDropdownActions dropdown;

    /** Status bar helpers (messages, severity, etc.). */
    protected SapStatusBarActions statusBar;

    /** Popup/dialog helpers. */
    protected SapPopupActions popup;

    /** Toolbar helpers. */
    protected SapToolbarActions toolbar;

    /**
     * Initializes all SAP helpers using the framework's {@link Browser}.
     *
     * <p>Runs after Spring injects {@code browser} into {@link AbstractPage}.
     */
    @PostConstruct
    /**
     * Creates initSapHelpers operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    protected void initSapHelpers() {
        // NOTE: AbstractPage already initializes PageFactory elements in its own @PostConstruct.
        // Here we only wire our helper classes.
        this.keys = new SapKeyboard(browser);
        this.wait = new SapWait(browser);
        this.assertThat = new SapAssert(browser);

        this.grid = new SapGrid(browser);
        this.input = new SapInput(browser);
        this.checkbox = new SapCheckbox(browser);
        this.dropdown = new SapDropdown(browser);
        this.statusBar = new SapStatusBar(browser);
        this.popup = new SapPopup(browser);
        this.toolbar = new SapToolbar(browser);
    }
}
