package com.sctdroid.app.textemoji.utils.compact;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.EditText;

import java.lang.reflect.Method;

/**
 * Created by lixindong on 2017/5/1.
 */

public class EditText11 implements IEditText {
    @Override
    @RequiresApi(11)
    public void disableShowSoftInput(@NonNull EditText editText) {
        Class<EditText> cls = EditText.class;
        Method method;
        try {
            method = cls.getMethod("setShowSoftInputOnFocus",boolean.class);
            method.setAccessible(true);
            method.invoke(editText, false);
        }catch (Exception e) {
            // TODO: handle exception
        }

        try {
            method = cls.getMethod("setSoftInputShownOnFocus",boolean.class);
            method.setAccessible(true);
            method.invoke(editText, false);
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}
