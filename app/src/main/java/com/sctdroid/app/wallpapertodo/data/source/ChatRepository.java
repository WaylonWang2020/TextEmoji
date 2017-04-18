package com.sctdroid.app.wallpapertodo.data.source;

import com.sctdroid.app.wallpapertodo.data.bean.ChatItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 4/18/17.
 */

public class ChatRepository implements ChatDataSource {
    private static ChatRepository INSTANCE;
    private ChatRepository mRemoteDataSource;
    private ChatDataSource mLocalDataSource;
    private List<ChatsRepositoryObserver> mObservers = new ArrayList<>();


    public ChatRepository(ChatDataSource localDataSource, ChatRepository remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static ChatRepository getInstance(ChatDataSource localDataSource,
                                             ChatRepository remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ChatRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public List<ChatItem> getChats() {
        return mLocalDataSource.getChats();
    }

    @Override
    public void saveChats(List<ChatItem> items) {
        mLocalDataSource.saveChats(items);
        notifyContentObserver();
    }

    @Override
    public void appendChat(ChatItem item) {
        mLocalDataSource.appendChat(item);
        notifyContentObserver();
    }

    public void addContentObserver(ChatsRepositoryObserver observer) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    public void removeContentObserver(ChatsRepositoryObserver observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    private void notifyContentObserver() {
        for (ChatsRepositoryObserver observer : mObservers) {
            observer.onChatsChanged();
        }
    }

    public interface ChatsRepositoryObserver {

        void onChatsChanged();

    }

}
