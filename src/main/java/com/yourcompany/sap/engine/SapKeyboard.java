package com.yourcompany.sap.engine;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SapKeyboard {
    private final WebDriver driver;
    private static final Logger LOG = Logger.getLogger(SapKeyboard.class.getName());

    /**
     * Keyboard helper for sending keys to the SAP WebGUI.
     *
     * @param driver WebDriver instance used for sending keys and executing JS fallbacks
     */
    public SapKeyboard(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Determine the element that should receive keyboard events. Attempts to
     * use the active element and falls back to the document body.
     *
     * @return element to receive keys or null if none available
     */
    private WebElement target() {
        try {
            return driver.switchTo().activeElement();
        } catch (WebDriverException e) {
            LOG.log(Level.FINE, "activeElement() failed, falling back to body", e);
        } catch (Exception e) {
            LOG.log(Level.FINER, "unexpected error getting active element", e);
        }

        try {
            return driver.findElement(By.tagName("body"));
        } catch (NoSuchElementException e) {
            LOG.log(Level.WARNING, "no <body> element found; returning null", e);
            return null;
        }
    }

    /**
     * Send key sequences to the current target. If native WebDriver sendKeys fails,
     * attempt a JS-based fallback.
     *
     * @param keys key sequences to send
     */
    private void send(CharSequence... keys) {
        WebElement t = target();
        if (t == null) {
            LOG.warning("No target to send keys to");
            return;
        }

        try {
            t.sendKeys(keys);
        } catch (WebDriverException e) {
            LOG.log(Level.FINE, "sendKeys failed; attempting JS fallback", e);
            jsSend(t, concat(keys));
        }
    }

    /**
     * Send a chord (combination) of keys, using Selenium's Keys.chord.
     */
    private void chord(Keys... keys) {
        send(Keys.chord(keys));
    }

    /**
     * Concatenate provided CharSequence pieces into a single string used by JS fallback.
     */
    private static String concat(CharSequence... keys) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence k : keys) sb.append(k);
        return sb.toString();
    }

    /**
     * JS fallback to dispatch keyboard events and append characters to an element's value.
     * This emulates typing at a DOM level for inputs where sendKeys cannot reach.
     */
    private void jsSend(WebElement element, String text) {
        if (!(driver instanceof JavascriptExecutor)) return;
        String script = "var el = arguments[0]; var text = arguments[1];\n"
                + "if (!el) return false;\n"
                + "el.focus();\n"
                + "function fire(n, opts){var e = new KeyboardEvent(n, opts); el.dispatchEvent(e);}\n"
                + "for (var i=0;i<text.length;i++){\n"
                + "  var ch = text.charAt(i);\n"
                + "  fire('keydown', {key: ch, char: ch, bubbles:true});\n"
                + "  fire('keypress', {key: ch, char: ch, bubbles:true});\n"
                + "  el.value = (el.value || '') + ch;\n"
                + "  fire('input', {bubbles:true});\n"
                + "  fire('keyup', {key: ch, char: ch, bubbles:true});\n"
                + "}\n"
                + "return true;";

        try {
            ((JavascriptExecutor) driver).executeScript(script, element, text);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "JS fallback send failed", e);
        }
    }

    /**
     * Send a function key (F1..F12).
     *
     * @param n function key number
     */
    // Function keys
    public void f(int n) {
        send(Keys.valueOf("F" + n));
    }

    public void f1() { f(1); }
    public void f2() { f(2); }
    public void f3() { f(3); }
    public void f4() { f(4); }
    public void f5() { f(5); }
    public void f6() { f(6); }
    public void f7() { f(7); }
    public void f8() { f(8); }
    public void f9() { f(9); }
    public void f10() { f(10); }
    public void f11() { f(11); }
    public void f12() { f(12); }

    // Common SAP-ish
    /**
     * Press Enter key.
     */
    public void enter() { send(Keys.ENTER); }

    /**
     * Press SAP 'Back' (mapped to F3 by convention).
     */
    public void back() { f3(); }

    /**
     * Press SAP 'Save' (mapped to F11 by convention).
     */
    public void save() { f11(); }

    /**
     * Press SAP 'Cancel' (mapped to F12 by convention).
     */
    public void cancel() { f12(); }

    /**
     * Press Escape.
     */
    public void esc() { send(Keys.ESCAPE); }

    /**
     * Press Tab.
     */
    public void tab() { send(Keys.TAB); }

    /**
     * Press Shift+Tab.
     */
    public void shiftTab() { chord(Keys.SHIFT, Keys.TAB); }

    // Combos
    /**
     * Send CTRL + k.
     */
    public void ctrl(Keys k) { chord(Keys.CONTROL, k); }

    /**
     * Send SHIFT + k.
     */
    public void shift(Keys k) { chord(Keys.SHIFT, k); }

    /**
     * Send ALT + k.
     */
    public void alt(Keys k) { chord(Keys.ALT, k); }

    /**
     * Send CTRL + SHIFT + k.
     */
    public void ctrlShift(Keys k) { chord(Keys.CONTROL, Keys.SHIFT, k); }

    /**
     * Send CTRL + ALT + k.
     */
    public void ctrlAlt(Keys k) { chord(Keys.CONTROL, Keys.ALT, k); }

    /**
     * Send CTRL + SHIFT + ALT + k.
     */
    public void ctrlShiftAlt(Keys k) { chord(Keys.CONTROL, Keys.SHIFT, Keys.ALT, k); }

    // Requested
    public void ctrlF() { ctrl(Keys.F); }
    public void shiftF() { chord(Keys.SHIFT, Keys.F); } // SHIFT + 'F'
    public void ctrlShiftF() { ctrlShift(Keys.F); }

    public void ctrlA() { chord(Keys.CONTROL, Keys.A); }
    public void ctrlC() { chord(Keys.CONTROL, Keys.C); }
    public void ctrlV() { chord(Keys.CONTROL, Keys.V); }
    public void ctrlX() { chord(Keys.CONTROL, Keys.X); }
    public void ctrlS() { chord(Keys.CONTROL, Keys.S); }
    public void ctrlEnter() { chord(Keys.CONTROL, Keys.ENTER); }
    public void ctrlShiftEnter() { chord(Keys.CONTROL, Keys.SHIFT, Keys.ENTER); }
}
