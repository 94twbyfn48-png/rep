package com.yourcompany.sap.engine;

import org.openqa.selenium.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class SapEvidence {

    public static Path screenshot(WebDriver driver, String name, Path dir) {
        try {
            Files.createDirectories(dir);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dst = dir.resolve(name + ".png");
            Files.copy(src.toPath(), dst, StandardCopyOption.REPLACE_EXISTING);
            return dst;
        } catch (Exception e) {
            throw new RuntimeException("Screenshot failed", e);
        }
    }
    /**
     * Capture a screenshot and write it to the given directory.
     *
     * @param driver webdriver to capture from
     * @param name   base file name (without extension)
     * @param dir    target directory
     * @return path to the saved screenshot file
     */
    public static Path screenshot(WebDriver driver, String name, Path dir) {
        try {
            Files.createDirectories(dir);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dst = dir.resolve(name + ".png");
            Files.copy(src.toPath(), dst, StandardCopyOption.REPLACE_EXISTING);
            return dst;
        } catch (Exception e) {
            throw new RuntimeException("Screenshot failed", e);
        }
    }

    /**
     * Dump the current page HTML to a file in the provided directory.
     *
     * @param driver webdriver to read page source from
     * @param name   base file name (without extension)
     * @param dir    target directory
     * @return path to the saved HTML file
     */
    public static Path htmlDump(WebDriver driver, String name, Path dir) {
        try {
            Files.createDirectories(dir);
            String html = driver.getPageSource();
            Path dst = dir.resolve(name + ".html");
            Files.write(dst, html.getBytes(StandardCharsets.UTF_8));
            return dst;
        } catch (Exception e) {
            throw new RuntimeException("HTML dump failed", e);
        }
    }
}
