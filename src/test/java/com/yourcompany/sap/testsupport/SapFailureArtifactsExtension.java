package com.yourcompany.sap.testsupport;

import com.yourcompany.sap.engine.SapEvidence;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.nio.file.Path;

public class SapFailureArtifactsExtension implements TestWatcher {

    @Override
    /**
     * Executes testFailed operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param context input parameter
     * @param cause input parameter
     *
     * @return operation result
     */
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object instance = context.getRequiredTestInstance();
        if (!(instance instanceof WebDriverProvider provider)) return;

        WebDriver driver = provider.getDriver();
        if (driver == null) return;

        String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9._-]", "_");
        Path dir = Path.of("artifacts", testName);

        try {
            SapEvidence.screenshot(driver, "FAIL", dir);
            SapEvidence.htmlDump(driver, "FAIL", dir);
        } catch (Exception ignored) {
        }
    }
}
