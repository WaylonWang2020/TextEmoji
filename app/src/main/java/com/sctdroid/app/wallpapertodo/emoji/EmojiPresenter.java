package com.sctdroid.app.wallpapertodo.emoji;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;

import com.sctdroid.app.wallpapertodo.data.bean.ChatItem;
import com.sctdroid.app.wallpapertodo.data.source.ChatsLoader;
import com.sctdroid.app.wallpapertodo.data.source.ChatsRepository;

import java.util.List;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiPresenter implements EmojiContract.Presenter, LoaderManager.LoaderCallbacks<List<ChatItem>> {
    private final EmojiContract.View mEmojiView;
    private final ChatsLoader mChatLoader;
    private final LoaderManager mLoaderManager;
    private final int CHATS_QUERY = 1;
    private final ChatsRepository mRepository;

    public EmojiPresenter(ChatsLoader chatsLoader, LoaderManager loaderManager, ChatsRepository repository, @NonNull EmojiContract.View emojiView) {
        mChatLoader = chatsLoader;
        mLoaderManager = loaderManager;
        mRepository = repository;
        mEmojiView = emojiView;
        mEmojiView.setPresenter(this);
    }

    @Override
    public void start() {
        mLoaderManager.initLoader(CHATS_QUERY, null, this).forceLoad();
    }

    @Override
    public Loader<List<ChatItem>> onCreateLoader(int id, Bundle args) {
        return mChatLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<ChatItem>> loader, List<ChatItem> data) {
        mEmojiView.showChats(data);
    }

    @Override
    public void onLoaderReset(Loader<List<ChatItem>> loader) {

    }

    @Override
    public void processInput(String inputText, int fontSize, boolean withShadow) {
        if (TextUtils.isEmpty(inputText)) {
            mEmojiView.showEmptyText();
        } else {
            mRepository.appendChat(new ChatItem.Builder()
                    .content(inputText)
                    .avatarResId(-1)
                    .fontSize(fontSize)
                    .withShadow(withShadow)
                    .build());
            mEmojiView.clearEditText();
        }

    }

    @Override
    public Uri saveBitmap(Bitmap bitmap, String filename) {
        return StorageHelper.saveBitmap(bitmap, filename);
    }
}
