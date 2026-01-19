package com.yourcompany.sap.components.gui;

import org.openqa.selenium.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SapGuiElement {
    protected final WebDriver driver;
    protected final WebElement el;
    private static final Logger LOG = Logger.getLogger(SapGuiElement.class.getName());

    public SapGuiElement(WebDriver driver, WebElement el) {
        this.driver = driver;
        this.el = el;
    }
    /**
     * Create a lightweight wrapper around a WebElement with JS fallbacks.
     *
     * @param driver webdriver
     * @param el     underlying element
     */
    public SapGuiElement(WebDriver driver, WebElement el) {
        this.driver = driver;
        this.el = el;
    }

    /**
     * Click the element. If native click fails, attempt a JS click.
     */
    public void click() {
        try {
            el.click();
        } catch (WebDriverException e) {
            LOG.log(Level.FINE, "native click failed, using JS fallback", e);
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ex) {
                LOG.log(Level.WARNING, "JS click also failed", ex);
            }
        }
    }
    /**
     * Return trimmed text of the element.
     */
    public String text() {
        try {
            return el.getText().trim();
        } catch (Exception e) {
            LOG.log(Level.FINER, "getting text failed", e);
            return "";
        }
    }
    /**
     * Return whether the element is displayed (safe-fail false).
     */
    public boolean isDisplayed() {
        try {
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Send keys to the element, falling back to a JS-based value append.
     */
    public void sendKeys(String text) {
        try {
            el.sendKeys(text);
        } catch (WebDriverException e) {
            LOG.log(Level.FINE, "sendKeys failed; using JS fallback", e);
            jsSend(text);
        }
    }
    /**
     * JS fallback that appends text to element's value and fires an input event.
     */
    protected void jsSend(String text) {
        if (!(driver instanceof JavascriptExecutor)) return;
        String script = "var el = arguments[0]; var text = arguments[1]; if(!el) return false; el.focus();"
                + " el.value = (el.value || '') + text; el.dispatchEvent(new Event('input',{bubbles:true})); return true;";
        try {
            ((JavascriptExecutor) driver).executeScript(script, el, text);
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "jsSend failed", ex);
        }
    }
}
