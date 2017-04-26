package com.sctdroid.app.textemoji.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lixindong on 4/26/17.
 */

public class OptionUtils {
    private static final String SP_NAME = "options";
    private static final String KEY_OPTION_WITH_SHADOW= "withShadow";
    private static final String KEY_OPTION_TEXT_SIZE= "textSize";

    public static boolean withShadow(Context context, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_OPTION_WITH_SHADOW, defValue);
    }

    public static void applyWithShadow(Context context, boolean withShadow) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_OPTION_WITH_SHADOW, withShadow).apply();
    }

    public static int textSize(Context context, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(KEY_OPTION_TEXT_SIZE, defValue);
    }
    public static void applyTextSize(Context context, int textSize) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(KEY_OPTION_TEXT_SIZE, textSize).apply();
    }


}
