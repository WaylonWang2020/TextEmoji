package com.sctdroid.app.textemoji.utils.compact;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.EditText;

import java.io.File;

/**
 * Created by lixindong on 2017/4/23.
 */
public class Compact implements IUri, IEditText {
    private static Compact INSTANCE;

    public static synchronized Compact getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Compact();
        }
        return INSTANCE;
    }

    public static void DestoryInstance() {
        INSTANCE = null;
    }

    private Context mContext;
    private IUri mUri;
    private IEditText mEditText;
    public void init(Context context) {
        mContext = context;
        initUri();
    }

    private void initUri() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mUri = new UriN(mContext);
        } else {
            mUri = new UriM();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mEditText = new EditText21();
        } else {
            mEditText = new EditText11();
        }
    }

    @Override
    public Uri fromFile(File file) {
        return mUri.fromFile(file);
    }

    @Override
    public void disableShowSoftInput(@NonNull EditText editText) {
        mEditText.disableShowSoftInput(editText);
    }
}
