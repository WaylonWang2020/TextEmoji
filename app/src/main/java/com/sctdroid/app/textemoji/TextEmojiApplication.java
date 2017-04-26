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
        // 如果已经在AndroidManifest.xml配置了App ID和渠道ID，调用TCAgent.init(this)即可；或与AndroidManifest.xml中的对应参数保持一致。
        TCAgent.setReportUncaughtExceptions(true);
        TCAgent.setAntiCheatingEnabled(this, true);
    }
}
