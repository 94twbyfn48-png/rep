package com.yourcompany.sap.engine;

import com.thy.testlibrary.browser.Browser; // TODO: Update package if your framework uses a different one.
import org.openqa.selenium.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class SapEvidence {

    /**
     * Executes screenshot operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     * @param name input parameter
     * @param dir input parameter
     *
     * @return operation result
     */
    public static Path screenshot(Browser browser, String name, Path dir) {
        try {
            Files.createDirectories(dir);
            File src = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.FILE);
            Path dst = dir.resolve(name + ".png");
            Files.copy(src.toPath(), dst, StandardCopyOption.REPLACE_EXISTING);
            return dst;
        } catch (Exception e) {
            throw new RuntimeException("Screenshot failed", e);
        }
    }

    /**
     * Executes htmlDump operation.
     *
     * <p><b>Implementation notes</b></p>
     * <ul>
     *   <li>Uses the framework <code>Browser</code> wrapper and calls Selenium via <code>browser.getDriver()</code>.</li>
     *   <li>Designed to be used from Page Objects extending <code>AbstractPage</code>.</li>
     * </ul>
     *
     * @param browser input parameter
     * @param name input parameter
     * @param dir input parameter
     *
     * @return operation result
     */
    public static Path htmlDump(Browser browser, String name, Path dir) {
        try {
            Files.createDirectories(dir);
            String html = browser.getDriver().getPageSource();
            Path dst = dir.resolve(name + ".html");
            Files.write(dst, html.getBytes(StandardCharsets.UTF_8));
            return dst;
        } catch (Exception e) {
            throw new RuntimeException("HTML dump failed", e);
        }
    }
}
