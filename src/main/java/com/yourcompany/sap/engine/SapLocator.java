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

    /**
     * Find an input by locating a <label> whose text contains the given labelText
     * and using the label's {@code for} attribute as the id of the input.
     *
     * @param driver    webdriver to search with
     * @param labelText visible label text to search for
     * @return the associated input element
     */
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

    /**
     * Heuristic lookup for an input by label: tries label->for, then aria-label, then placeholder.
     *
     * @param driver    webdriver to search with
     * @param labelText text of the label to search for
     * @return matched WebElement
     */
    public static WebElement byLabelHeuristic(WebDriver driver, String labelText) {
        try {
            return byLabelForId(driver, labelText);
        } catch (Exception ignored) {
        }

        try {
            return driver.findElement(By.xpath("//*[contains(@aria-label,'" + escape(labelText) + "')]") );
        } catch (Exception ignored) {
        }

        return driver.findElement(By.xpath("//*[contains(@placeholder,'" + escape(labelText) + "')]") );
    }

    /**
     * Escape single quotes for XPath usage by replacing with a look-alike character.
     */
    private static String escape(String s) {
        return s.replace("'", "’");
    }
}
