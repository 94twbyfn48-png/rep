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

    public String text() {
        try {
            return el.getText().trim();
        } catch (Exception e) {
            LOG.log(Level.FINER, "getting text failed", e);
            return "";
        }
    }

    public boolean isDisplayed() {
        try {
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void sendKeys(String text) {
        try {
            el.sendKeys(text);
        } catch (WebDriverException e) {
            LOG.log(Level.FINE, "sendKeys failed; using JS fallback", e);
            jsSend(text);
        }
    }

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
