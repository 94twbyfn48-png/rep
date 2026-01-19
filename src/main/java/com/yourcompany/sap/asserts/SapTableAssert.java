package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapGrid;

import java.util.List;
import java.util.Map;

public class SapTableAssert {
    private final List<Map<String, String>> table;

    /**
     * Creates a new SapTableAssert instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param grid input parameter
     * @param maxRowsCap input parameter
     */
    public SapTableAssert(SapGrid grid, int maxRowsCap) {
        // Uses virtualized read by default to be robust
        this.table = grid.readTableVirtualized(maxRowsCap, 4);
    }

    /**
     * Verifies assertRowExists operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param identifier input parameter
     * @param value input parameter
     *
     * @return operation result
     */
    public SapTableAssert assertRowExists(String identifier, String value) {
        boolean found = table.stream().anyMatch(r -> value.equals(r.get(identifier)));
        if (!found) {
            throw new SapAssertException("No row found where " + identifier + " = [" + value + "]");
        }
        return this;
    }

    /**
     * Verifies assertColumnNotEmpty operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param identifier input parameter
     *
     * @return operation result
     */
    public SapTableAssert assertColumnNotEmpty(String identifier) {
        boolean anyEmpty = table.stream().anyMatch(r -> r.get(identifier) == null || r.get(identifier).isBlank());
        if (anyEmpty) {
            throw new SapAssertException("Column " + identifier + " contains empty values");
        }
        return this;
    }

    /**
     * Verifies assertRowCount operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param expected input parameter
     *
     * @return operation result
     */
    public SapTableAssert assertRowCount(int expected) {
        if (table.size() != expected) {
            throw new SapAssertException("Expected row count " + expected + " but was " + table.size());
        }
        return this;
    }
}
