package com.sctdroid.app.textemoji.emoji;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;

import com.sctdroid.app.textemoji.data.bean.ChatItem;
import com.sctdroid.app.textemoji.data.bean.EmojiCategory;
import com.sctdroid.app.textemoji.data.bean.Me;
import com.sctdroid.app.textemoji.data.source.ChatsLoader;
import com.sctdroid.app.textemoji.data.source.ChatsRepository;
import com.sctdroid.app.textemoji.data.source.EmojiLoader;
import com.sctdroid.app.textemoji.data.source.MeLoader;

import java.util.List;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiPresenter implements EmojiContract.Presenter, LoaderManager.LoaderCallbacks<List<ChatItem>> {
    private final EmojiContract.View mEmojiView;
    private final ChatsLoader mChatLoader;
    private final LoaderManager mLoaderManager;
    private final int CHATS_QUERY = 2;
    private final int ME_QUERY = 3;
    private final int EMOJI_QUERY = 4;
    private final ChatsRepository mRepository;
    private final MeLoader mMeLoader;
    private final EmojiLoader mEmojiLoader;
    private boolean mIsFirstTimeStart = false;

    public EmojiPresenter(EmojiLoader emojiLoader, MeLoader meLoader, ChatsLoader chatsLoader, LoaderManager loaderManager, ChatsRepository repository, @NonNull EmojiContract.View emojiView) {
        mEmojiLoader = emojiLoader;
        mMeLoader = meLoader;
        mChatLoader = chatsLoader;
        mLoaderManager = loaderManager;
        mRepository = repository;
        mEmojiView = emojiView;
        mEmojiView.setPresenter(this);
    }

    @Override
    public void start() {
        mLoaderManager.initLoader(CHATS_QUERY, null, this).forceLoad();
        mLoaderManager.initLoader(ME_QUERY, null, new LoaderManager.LoaderCallbacks<Me>() {
            @Override
            public Loader<Me> onCreateLoader(int id, Bundle args) {
                return mMeLoader;
            }

            @Override
            public void onLoadFinished(Loader<Me> loader, Me data) {
                mEmojiView.updateMe(data);
            }

            @Override
            public void onLoaderReset(Loader<Me> loader) {

            }
        }).forceLoad();
        mLoaderManager.initLoader(EMOJI_QUERY, null, new LoaderManager.LoaderCallbacks<List<EmojiCategory>>() {
            @Override
            public Loader<List<EmojiCategory>> onCreateLoader(int id, Bundle args) {
                return mEmojiLoader;
            }

            @Override
            public void onLoadFinished(Loader<List<EmojiCategory>> loader, List<EmojiCategory> data) {
                mEmojiView.initEmojiBoard(data);
            }

            @Override
            public void onLoaderReset(Loader<List<EmojiCategory>> loader) {

            }
        }).forceLoad();
    }

    @Override
    public Loader<List<ChatItem>> onCreateLoader(int id, Bundle args) {
        return mChatLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<ChatItem>> loader, List<ChatItem> data) {
        mEmojiView.showChats(data);
        if (mIsFirstTimeStart) {
            mEmojiView.scrollToTop();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ChatItem>> loader) {

    }

    @Override
    public void processInput(String inputText, int textSize, boolean withShadow) {
        if (TextUtils.isEmpty(inputText)) {
            mEmojiView.showEmptyText();
        } else {
            mRepository.appendChat(new ChatItem.Builder()
                    .content(inputText)
                    .avatarResId(-1)
                    .textSize(textSize)
                    .withShadow(withShadow)
                    .build());
            mEmojiView.clearEditText();
        }

    }

    @Override
    public Uri saveBitmap(Bitmap bitmap, String filename, String dirPath) {
        return StorageHelper.saveBitmap(bitmap, filename, dirPath);
    }

    public void isFirstTime(boolean isFirstTimeStart) {
        mIsFirstTimeStart = isFirstTimeStart;
    }
}
