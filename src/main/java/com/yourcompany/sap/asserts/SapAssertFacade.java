package com.yourcompany.sap.asserts;

/**
 * Abstraction for the assertion factory/facade used by pages.
 *
 * <p>This facade creates strongly-typed assertion helpers (table, row, input, etc.).
 * Depending on this interface instead of the concrete {@link SapAssert} makes it
 * easier to replace assertion impls or mock them in tests.
 */
public interface SapAssertFacade {

    SapRowAssert row(int rowIndex);

    SapTableAssert table(int maxRowsCap);

    SapInputAssert input(String label);

    SapCheckboxAssert checkbox(String label);

    SapDropdownAssert dropdown(String label);

    SapStatusBarAssert statusBar();
}
