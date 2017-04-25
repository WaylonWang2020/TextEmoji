package com.sctdroid.app.textemoji.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.data.source.ChatsRepository;
import com.sctdroid.app.textemoji.data.source.ChatsLoader;
import com.sctdroid.app.textemoji.data.source.MeLoader;
import com.sctdroid.app.textemoji.data.source.MeRepository;
import com.sctdroid.app.textemoji.data.source.local.ChatsLocalDataSource;
import com.sctdroid.app.textemoji.data.source.local.MeLocalDataSource;
import com.sctdroid.app.textemoji.utils.ActivityUtils;
import com.sctdroid.app.textemoji.utils.Constants;
import com.sctdroid.app.textemoji.utils.ToastUtils;
import com.sctdroid.app.textemoji.utils.compact.Compact;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiActivity extends AppCompatActivity {
    private EmojiFragment mEmojiFragment;
    private EmojiPresenter mEmojiPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Compact.DestoryInstance();
        ToastUtils.DestoryInstance();
        ShareSDK.stopSDK();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_emoji);

        Compact.getInstance().init(this);
        ShareSDK.initSDK(this, Constants.SHARE_SDK_APPID);

        mEmojiFragment = (EmojiFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mEmojiFragment == null) {
            mEmojiFragment = EmojiFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mEmojiFragment, R.id.contentFrame);
        }

        ChatsRepository repository = ChatsRepository.getInstance(new ChatsLocalDataSource(this), null);
        ChatsLoader chatsLoader = new ChatsLoader(this, repository);
        MeRepository meRepository = MeRepository.getInstance(new MeLocalDataSource(this), null);
        MeLoader meLoader = new MeLoader(this, meRepository);
        mEmojiPresenter = new EmojiPresenter(meLoader, chatsLoader, getSupportLoaderManager(), repository, mEmojiFragment);
    }

    private long lastPressTime = 0;

    @Override
    public void onBackPressed() {
        if (mEmojiFragment.onBackPressed()) {
            return;
        }
        if (isFirstTimePress()) {
            return;
        }
        super.onBackPressed();
    }

    private boolean isFirstTimePress() {
        long current = System.currentTimeMillis();
        long span = current - lastPressTime;
        if (span >= Constants.EXIT_TIME_SPAN) {
            lastPressTime = current;
            ToastUtils.show(this, R.string.press_again_to_exit, Toast.LENGTH_SHORT);
            return true;
        } else {
            return false;
        }
    }
}
