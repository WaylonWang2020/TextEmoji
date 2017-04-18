package com.sctdroid.app.wallpapertodo.data.source.local;

import com.sctdroid.app.wallpapertodo.data.bean.ChatItem;
import com.sctdroid.app.wallpapertodo.data.source.ChatDataSource;

import java.util.List;

/**
 * Created by lixindong on 4/18/17.
 */

public class ChatLocalDataSource implements ChatDataSource {
    @Override
    public List<ChatItem> getChats() {
        return null;
    }

    @Override
    public void saveChats(List<ChatItem> items) {

    }

    @Override
    public void appendChat(ChatItem item) {

    }
}
