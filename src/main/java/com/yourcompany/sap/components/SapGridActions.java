package com.yourcompany.sap.components;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

/**
 * Abstraction for interacting with SAP GUI for HTML table/grid controls.
 *
 * <p>Exposes the stable, supported operations that page objects and assertions
 * rely on. Keeping pages dependent on this interface makes it easier to:
 * <ul>
 *   <li>Mock grids in unit tests without a real browser.</li>
 *   <li>Swap implementations for different SAP themes/versions.</li>
 *   <li>Hide low-level Selenium plumbing behind a consistent API.</li>
 * </ul>
 */
public interface SapGridActions {

    /** Finds a grid cell by its row/column indices. */
    WebElement findCellByRowCol(int rowIndex, int colIndex);

    /** Returns the best input-like element inside a cell (or the cell itself as a fallback). */
    WebElement getCellInput(WebElement cell);

    /** Reads visible text from a cell (trimmed). */
    String getCellText(int row, int col);

    /** Clicks a cell and types a new value (Ctrl+A + Delete + value). */
    void setCellValue(int row, int col, String value);

    /**
     * Returns a map of column-index to identifier/header.
     * Implementations may use a first-row heuristic and/or a TH/role fallback.
     */
    Map<Integer, String> identifiers();

    /** Returns a single row as a map from identifier/header to cell value. */
    Map<String, String> rowAsMap(int rowIndex);

    /**
     * Scrolls the grid until the target row is likely rendered (virtualization-aware).
     *
     * @param targetRow 0-based target row index
     * @param maxScrolls safety limit to avoid infinite scrolling
     * @return true if the target row appears to be reachable/rendered; false otherwise
     */
    boolean scrollToRow(int targetRow, int maxScrolls);

    /** Reads the entire table (best-effort) by iterating through virtualized content. */
    List<Map<String, String>> readTableVirtualized(int maxRowsCap, int stallRounds);
}
