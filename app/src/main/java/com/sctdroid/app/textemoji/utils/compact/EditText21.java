package com.sctdroid.app.textemoji.utils.compact;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.EditText;

/**
 * Created by lixindong on 2017/5/1.
 */

public class EditText21 implements IEditText {
    @Override
    @RequiresApi(21)
    public void disableShowSoftInput(@NonNull EditText editText) {
        editText.setShowSoftInputOnFocus(false);
    }
}
