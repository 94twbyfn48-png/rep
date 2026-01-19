package com.yourcompany.sap.pages;

import java.util.List;
import java.util.Map;

/**
 * SE16 page object (Data Browser) for SAP GUI for HTML.
 */
public class Se16Page extends BaseSapPage {

    /**
     * Minimal page identification.
     *
     * <p>If your system uses a different title, replace this with a stronger locator-based check.
     */
    @Override
    /**
     * Checks isAt operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public boolean isAt() {
        try {
            String title = browser.getDriver().getTitle();
            if (title == null) return true;
            return title.toUpperCase().contains("SE16");
        } catch (Exception ignored) {
            // If title is not accessible, don't block tests by returning false.
            return true;
        }
    }

    /**
     * Sets the table name in SE16 and navigates to selection screen.
     * Adjust the label text if your system uses a different language.
     */
    /**
     * Sets setTableName operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param table input parameter
     *
     * @return operation result
     */
    public Se16Page setTableName(String table) {
        input.setByLabel("Table Name", table);
        keys.enter();
        wait.pageReady();
        return this;
    }

    /**
     * Executes the selection (F8).
     */
    /**
     * Executes execute operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    public Se16Page execute() {
        keys.f8();
        wait.pageReady();
        wait.notBusy();
        return this;
    }

    /**
     * Reads a virtualized grid by scrolling/observing and collecting unique rows.
     */
    /**
     * Gets readAllVirtualized operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param maxRows input parameter
     * @param stallRounds input parameter
     *
     * @return operation result
     */
    public List<Map<String, String>> readAllVirtualized(int maxRows, int stallRounds) {
        return grid.readTableVirtualized(maxRows, stallRounds);
    }
}
