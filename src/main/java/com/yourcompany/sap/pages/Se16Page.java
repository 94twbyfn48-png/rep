package com.yourcompany.sap.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class Se16Page extends BaseSapPage {

    public Se16Page(WebDriver browser) {
        super(browser);
    }
    /**
     * Create a page object for SE16-like screens.
     *
     * @param browser webdriver used by the page
     */
    public Se16Page(WebDriver browser) {
        super(browser);
    }

    /**
     * Set the table name input (label may need adjustment per system) and submit.
     *
     * @param table table name to set
     * @return this page instance
     */
    public Se16Page setTableName(String table) {
        input.setByLabel("Table Name", table);
        keys.enter();
        wait.pageReady();
        return this;
    }

    /**
     * Execute the selection (press F8) and wait until the page is ready and not busy.
     *
     * @return this page instance
     */
    public Se16Page execute() {
        keys.f8();
        wait.pageReady();
        wait.notBusy();
        return this;
    }

    /**
     * Read the virtualized table using the grid helper.
     *
     * @param maxRows    maximum number of rows to read
     * @param stallRounds how many stall rounds to tolerate
     * @return list of rows represented as maps
     */
    public List<Map<String, String>> readAllVirtualized(int maxRows, int stallRounds) {
        return grid.readTableVirtualized(maxRows, stallRounds);
    }
}
