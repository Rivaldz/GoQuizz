package com.valdo.goquizz.models;

import android.app.Activity;

public class pinSava extends Activity {

    static String pin = null;

    public static String getPin() {
        return pin;
    }

    public static void setPin(String pin) { pinSava.pin = pin;
    }
}




