package com.yourcompany.sap.testsupport;

import com.yourcompany.sap.engine.SapEvidence;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.nio.file.Path;

public class SapFailureArtifactsExtension implements TestWatcher {
    /**
     * JUnit extension that captures screenshots and HTML on test failure.
     */
    @Override
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
        } catch (Exception e) {
            // Log or ignore - failing to write artifacts shouldn't mask the original failure
        }
    }
}
