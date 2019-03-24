package com.example.domenico.Zarathustra.backend.api;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {
    private static Context c;

    public static void init(Context c) {
        SharedPreferencesManager.c = c;
    }
    public static void set(String name, String value) {
        SharedPreferences sharedPref = c.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(name, value);
        editor.apply();
    }
    public static String get (String name){
        return c.getSharedPreferences("prefs", MODE_PRIVATE).getString(name, null);
    }

    public static String get (String name, String defVal){
        return c.getSharedPreferences("prefs", MODE_PRIVATE).getString(name, defVal);
    }
}
