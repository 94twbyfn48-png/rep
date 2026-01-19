package com.yourcompany.sap.components.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SapGuiTable extends SapGuiElement {
    private final WebElement tableRoot;

    public SapGuiTable(WebDriver driver, WebElement tableRoot) {
        super(driver, tableRoot);
        this.tableRoot = tableRoot;
    }
    /**
     * Construct a table wrapper for an HTML table root element.
     *
     * @param driver    webdriver
     * @param tableRoot root element of the table
     */
    public SapGuiTable(WebDriver driver, WebElement tableRoot) {
        super(driver, tableRoot);
        this.tableRoot = tableRoot;
    }

    /**
     * Return table rows (safe-fail to empty list on errors).
     */
    public List<WebElement> rows() {
        try {
            return tableRoot.findElements(By.xpath(".//tr"));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Return the cell at the specified 0-based row and 0-based column indices.
     */
    public WebElement getCell(int rowIndex, int colIndex) {
        List<WebElement> r = rows();
        if (rowIndex < 0 || rowIndex >= r.size()) throw new IndexOutOfBoundsException("rowIndex");
        List<WebElement> cells = r.get(rowIndex).findElements(By.xpath(".//th|.//td"));
        if (colIndex < 0 || colIndex >= cells.size()) throw new IndexOutOfBoundsException("colIndex");
        return cells.get(colIndex);
    }

    /**
     * Find the first row index containing the provided text. Returns -1 when not found.
     */
    public int findRowIndexByCellText(String text) {
        List<WebElement> r = rows();
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i).getText().contains(text)) return i;
        }
        return -1;
    }
}
