package com.yourcompany.sap.pages;

import com.yourcompany.sap.asserts.SapAssert;
import com.yourcompany.sap.components.*;
import com.yourcompany.sap.engine.*;
import org.openqa.selenium.WebDriver;

public abstract class BaseSapPage {
    protected final WebDriver browser;

    public final SapKeyboard keys;
    public final SapWait wait;
    public final SapAssert assertThat;

    public final SapGrid grid;
    public final SapInput input;
    public final SapCheckbox checkbox;
    public final SapDropdown dropdown;
    public final SapStatusBar statusBar;
    public final SapPopup popup;
    public final SapToolbar toolbar;

    /**
     * Base page shared utilities and components for SAP pages.
     *
     * @param browser the WebDriver used by the page
     */
    public BaseSapPage(WebDriver browser) {
        this.browser = browser;

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
