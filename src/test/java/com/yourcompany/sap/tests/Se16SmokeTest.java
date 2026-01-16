package com.yourcompany.sap.tests;

import com.yourcompany.sap.asserts.SoftSapAssert;
import com.yourcompany.sap.pages.Se16Page;
import com.yourcompany.sap.testsupport.SapFailureArtifactsExtension;
import com.yourcompany.sap.testsupport.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(SapFailureArtifactsExtension.class)
public class Se16SmokeTest implements WebDriverProvider {

    private WebDriver browser;

    @Override
    public WebDriver getDriver() {
        return browser;
    }

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
        // browser.get("SAP WEBGUI URL");
    }

    @AfterEach
    void teardown() {
        if (browser != null) browser.quit();
    }

    @Test
    void se16_virtualized_and_scrollToRow() {
        Se16Page se16 = new Se16Page(browser);

        se16.setTableName("KNA1").execute();

        var all = se16.readAllVirtualized(2000, 6);
        Assertions.assertTrue(all.size() > 0, "No rows read (virtualized)");

        boolean ok = se16.grid.scrollToRow(250, 50);
        System.out.println("scrollToRow(250) visible? " + ok);

        se16.assertThat
                .table(2000)
                .assertColumnNotEmpty("BUKRS");

        SoftSapAssert soft = new SoftSapAssert();
        soft.check(() -> se16.assertThat.statusBar().assertNotEmpty(), "Status bar not empty");
        soft.assertAll();
    }
}
