package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapGrid;

import java.util.Map;

public class SapRowAssert {
    private final int rowIndex;
    private final Map<String, String> row;

    /**
     * Creates a new SapRowAssert instance.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param grid input parameter
     * @param rowIndex input parameter
     */
    public SapRowAssert(SapGrid grid, int rowIndex) {
        this.rowIndex = rowIndex;
        this.row = grid.rowAsMap(rowIndex);
    }

    /**
     * Verifies assertEquals operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param col input parameter
     * @param expected input parameter
     *
     * @return operation result
     */
    public SapRowAssert assertEquals(String col, String expected) {
        String actual = row.get(col);
        if (actual == null || !expected.equals(actual)) {
            throw new SapAssertException("Row " + rowIndex + " | " + col +
                    " expected [" + expected + "] but was [" + actual + "]");
        }
        return this;
    }

    /**
     * Verifies assertNotEmpty operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param col input parameter
     *
     * @return operation result
     */
    public SapRowAssert assertNotEmpty(String col) {
        String v = row.get(col);
        if (v == null || v.isBlank()) {
            throw new SapAssertException("Row " + rowIndex + " | " + col + " is empty");
        }
        return this;
    }

    /**
     * Verifies assertContains operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param col input parameter
     * @param part input parameter
     *
     * @return operation result
     */
    public SapRowAssert assertContains(String col, String part) {
        String v = row.get(col);
        if (v == null || !v.contains(part)) {
            throw new SapAssertException("Row " + rowIndex + " | " + col +
                    " does not contain [" + part + "], was [" + v + "]");
        }
        return this;
    }
}
