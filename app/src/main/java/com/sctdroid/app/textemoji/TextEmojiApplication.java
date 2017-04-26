package com.sctdroid.app.textemoji;

import android.app.Application;

import com.tendcloud.tenddata.TCAgent;

/**
 * Created by lixindong on 2017/4/26.
 */
public class TextEmojiApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TCAgent.init(this);
        TCAgent.setReportUncaughtExceptions(true);
        TCAgent.setAntiCheatingEnabled(this, true);
    }
}
