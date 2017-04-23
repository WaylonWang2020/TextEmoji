package com.sctdroid.app.textemoji.utils.compact;

import android.net.Uri;

import java.io.File;

/**
 * Created by lixindong on 2017/4/23.
 */
public class UriM implements IUri {
    @Override
    public Uri fromFile(File file) {
        return Uri.fromFile(file);
    }
}
