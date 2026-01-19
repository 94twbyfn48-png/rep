package com.yourcompany.sap.testsupport;

import org.openqa.selenium.WebDriver;

public interface WebDriverProvider {
    /**
     * Gets getDriver operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @return operation result
     */
    WebDriver getDriver();
}
