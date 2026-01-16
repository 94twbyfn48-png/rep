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
