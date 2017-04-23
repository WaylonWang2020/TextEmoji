package com.sctdroid.app.textemoji.utils.compact;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by lixindong on 2017/4/23.
 */
public class UriN implements IUri {
    private final Context mContext;

    public UriN(Context context) {
        mContext = context;
    }

    @Override
    public Uri fromFile(File file) {
        return FileProvider.getUriForFile(mContext, "com.sctdroid.app.textemoji.fileProvider", file);
    }
}
