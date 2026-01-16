package com.yourcompany.sap.engine;

import org.openqa.selenium.*;

public class SapLocator {

    public static WebElement byLabelForId(WebDriver driver, String labelText) {
        WebElement label = driver.findElement(
                By.xpath("//label[contains(normalize-space(.),'" + escape(labelText) + "')]")
        );
        String forId = label.getAttribute("for");
        if (forId == null || forId.isBlank()) {
            throw new NoSuchElementException("Label found but has no @for: " + labelText);
        }
        return driver.findElement(By.id(forId));
    }

    public static WebElement byLabelHeuristic(WebDriver driver, String labelText) {
        try {
            return byLabelForId(driver, labelText);
        } catch (Exception ignored) {
        }

        try {
            return driver.findElement(By.xpath("//*[contains(@aria-label,'" + escape(labelText) + "')]"));
        } catch (Exception ignored) {
        }

        return driver.findElement(By.xpath("//*[contains(@placeholder,'" + escape(labelText) + "')]"));
    }

    private static String escape(String s) {
        return s.replace("'", "’");
    }
}
