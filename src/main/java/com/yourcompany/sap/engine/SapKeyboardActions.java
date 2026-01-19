package com.yourcompany.sap.engine;

import org.openqa.selenium.Keys;

/**
 * Abstraction for SAP keyboard shortcuts and key combinations.
 */
public interface SapKeyboardActions {

    // Function keys
    void f(int n);
    void f1();
    void f2();
    void f3();
    void f4();
    void f5();
    void f6();
    void f7();
    void f8();
    void f9();
    void f10();
    void f11();
    void f12();

    // Common SAP-ish
    void enter();
    void back();
    void save();
    void cancel();
    void esc();
    void tab();
    void shiftTab();

    // Combos
    void ctrl(Keys k);
    void shift(Keys k);
    void alt(Keys k);
    void ctrlShift(Keys k);
    void ctrlAlt(Keys k);
    void ctrlShiftAlt(Keys k);

    // Requested/common
    void ctrlF();
    void shiftF();
    void ctrlShiftF();

    void ctrlA();
    void ctrlC();
    void ctrlV();
    void ctrlX();
    void ctrlS();
    void ctrlEnter();
    void ctrlShiftEnter();
}
