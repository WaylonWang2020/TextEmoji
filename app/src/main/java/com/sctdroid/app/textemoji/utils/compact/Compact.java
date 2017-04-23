package com.sctdroid.app.textemoji.utils.compact;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import java.io.File;

/**
 * Created by lixindong on 2017/4/23.
 */
public class Compact {
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
    }

    public Uri fromFile(File file) {
        return mUri.fromFile(file);
    }
}
