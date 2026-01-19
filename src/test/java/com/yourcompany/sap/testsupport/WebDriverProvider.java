package com.yourcompany.sap.testsupport;

import org.openqa.selenium.WebDriver;

public interface WebDriverProvider {
    /**
     * Return the WebDriver instance provided by the test.
     *
     * @return WebDriver or null
     */
    WebDriver getDriver();
}
