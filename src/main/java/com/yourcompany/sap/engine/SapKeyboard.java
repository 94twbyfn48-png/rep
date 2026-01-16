package com.yourcompany.sap.engine;

import org.openqa.selenium.*;

public class SapKeyboard {
    private final WebDriver driver;

    public SapKeyboard(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement target() {
        try {
            return driver.switchTo().activeElement();
        } catch (Exception ignored) {
        }
        return driver.findElement(By.tagName("body"));
    }

    private void send(CharSequence... keys) {
        target().sendKeys(keys);
    }

    private void chord(Keys... keys) {
        send(Keys.chord(keys));
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
