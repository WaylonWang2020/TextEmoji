package com.sctdroid.app.textemoji.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by lixindong on 4/25/17.
 */

public class WeixinShareUtils {
    public static void shareImage(Context context, Uri uri) {
        Intent sendIntent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        sendIntent.setComponent(comp);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("image/*");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(sendIntent);
    }
}
