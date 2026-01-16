package com.yourcompany.sap.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class Se16Page extends BaseSapPage {

    public Se16Page(WebDriver browser) {
        super(browser);
    }

    // Change label if needed in your system
    public Se16Page setTableName(String table) {
        input.setByLabel("Table Name", table);
        keys.enter();
        wait.pageReady();
        return this;
    }

    public Se16Page execute() {
        keys.f8();
        wait.pageReady();
        wait.notBusy();
        return this;
    }

    public List<Map<String, String>> readAllVirtualized(int maxRows, int stallRounds) {
        return grid.readTableVirtualized(maxRows, stallRounds);
    }
}
