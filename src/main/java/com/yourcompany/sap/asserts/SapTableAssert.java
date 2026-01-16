package com.yourcompany.sap.asserts;

import com.yourcompany.sap.components.SapGrid;

import java.util.List;
import java.util.Map;

public class SapTableAssert {
    private final List<Map<String, String>> table;

    public SapTableAssert(SapGrid grid, int maxRowsCap) {
        // Uses virtualized read by default to be robust
        this.table = grid.readTableVirtualized(maxRowsCap, 4);
    }

    public SapTableAssert assertRowExists(String identifier, String value) {
        boolean found = table.stream().anyMatch(r -> value.equals(r.get(identifier)));
        if (!found) {
            throw new SapAssertException("No row found where " + identifier + " = [" + value + "]");
        }
        return this;
    }

    public SapTableAssert assertColumnNotEmpty(String identifier) {
        boolean anyEmpty = table.stream().anyMatch(r -> r.get(identifier) == null || r.get(identifier).isBlank());
        if (anyEmpty) {
            throw new SapAssertException("Column " + identifier + " contains empty values");
        }
        return this;
    }

    public SapTableAssert assertRowCount(int expected) {
        if (table.size() != expected) {
            throw new SapAssertException("Expected row count " + expected + " but was " + table.size());
        }
        return this;
    }
}
