package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapGrid;

import java.util.Map;

public class SapRowAssert {
    private final int rowIndex;
    private final Map<String, String> row;

    public SapRowAssert(SapGrid grid, int rowIndex) {
        this.rowIndex = rowIndex;
        this.row = grid.rowAsMap(rowIndex);
    }
    /**
     * Create assertions for the given row index using data read from the grid.
     *
     * @param grid     grid helper
     * @param rowIndex zero-based row index
     */
    public SapRowAssert(SapGrid grid, int rowIndex) {
        this.rowIndex = rowIndex;
        this.row = grid.rowAsMap(rowIndex);
    }

    /**
     * Assert that the column value equals expected.
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
     * Assert that the column value is not empty.
     */
    public SapRowAssert assertNotEmpty(String col) {
        String v = row.get(col);
        if (v == null || v.isBlank()) {
            throw new SapAssertException("Row " + rowIndex + " | " + col + " is empty");
        }
        return this;
    }

    /**
     * Assert that the column value contains the provided substring.
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
