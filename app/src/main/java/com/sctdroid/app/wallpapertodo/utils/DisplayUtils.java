package com.sctdroid.app.wallpapertodo.utils;

import android.content.Context;

/**
 * Created by lixindong on 4/19/17.
 */

public class DisplayUtils {
    public static int dp2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

}
