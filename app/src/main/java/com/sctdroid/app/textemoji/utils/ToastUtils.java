package com.sctdroid.app.textemoji.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by lixindong on 4/24/17.
 */

public class ToastUtils {
    private static Toast INSTANCE;

    public static void show(@NonNull Context context, @StringRes int resId, int duration) {
        show(context, context.getString(resId), duration);
    }

    public static void show(@NonNull Context context, @NonNull String text, int duration) {
        if (INSTANCE == null) {
            INSTANCE = Toast.makeText(context, text, duration);
        } else {
            INSTANCE.setText(text);
        }
        INSTANCE.show();
    }

    public static void DestoryInstance() {
        INSTANCE = null;
    }
}
