package com.sctdroid.app.wallpapertodo.data.source;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.sctdroid.app.wallpapertodo.data.bean.ChatItem;

import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public class ChatsLoader extends AsyncTaskLoader<List<ChatItem>> implements ChatRepository.ChatsRepositoryObserver {
    private final ChatRepository mRepository;

    public ChatsLoader(Context context, @NonNull ChatRepository repository) {
        super(context);
        mRepository = repository;
    }

    @Override
    public List<ChatItem> loadInBackground() {
        return mRepository.getChats();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        mRepository.removeContentObserver(this);
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onStartLoading() {
        mRepository.addContentObserver(this);
    }

    @Override
    public void onChatsChanged() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
