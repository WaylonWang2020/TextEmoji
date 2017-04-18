package com.sctdroid.app.wallpapertodo.emoji;

import android.support.annotation.NonNull;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiPresenter implements EmojiContract.Presenter {
    private final EmojiContract.View mEmojiView;

    public EmojiPresenter(@NonNull EmojiContract.View emojiView) {
        mEmojiView = emojiView;
        mEmojiView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
