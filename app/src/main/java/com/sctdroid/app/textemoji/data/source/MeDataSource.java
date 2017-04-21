package com.sctdroid.app.textemoji.data.source;

import android.graphics.Bitmap;

import com.sctdroid.app.textemoji.data.bean.Me;

/**
 * Created by lixindong on 4/20/17.
 */

public interface MeDataSource {
    Me getMe();
    void putMe(Me me);
    String uploadAvatar(Bitmap bitmap);
}
