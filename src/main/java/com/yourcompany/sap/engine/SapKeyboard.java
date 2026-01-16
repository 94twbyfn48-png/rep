package com.yourcompany.sap.engine;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SapKeyboard {
    private final WebDriver driver;
    private static final Logger LOG = Logger.getLogger(SapKeyboard.class.getName());

    public SapKeyboard(WebDriver driver) {
        this.driver = driver;
    }

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

    private void chord(Keys... keys) {
        send(Keys.chord(keys));
    }

    private static String concat(CharSequence... keys) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence k : keys) sb.append(k);
        return sb.toString();
    }

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
    public void enter() { send(Keys.ENTER); }
    public void back() { f3(); }
    public void save() { f11(); }
    public void cancel() { f12(); }
    public void esc() { send(Keys.ESCAPE); }
    public void tab() { send(Keys.TAB); }
    public void shiftTab() { chord(Keys.SHIFT, Keys.TAB); }

    // Combos
    public void ctrl(Keys k) { chord(Keys.CONTROL, k); }
    public void shift(Keys k) { chord(Keys.SHIFT, k); }
    public void alt(Keys k) { chord(Keys.ALT, k); }
    public void ctrlShift(Keys k) { chord(Keys.CONTROL, Keys.SHIFT, k); }
    public void ctrlAlt(Keys k) { chord(Keys.CONTROL, Keys.ALT, k); }
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
