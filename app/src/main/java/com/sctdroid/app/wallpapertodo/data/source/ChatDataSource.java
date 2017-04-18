package com.sctdroid.app.wallpapertodo.data.source;

import com.sctdroid.app.wallpapertodo.data.bean.ChatItem;

import java.util.List;

/**
 * Created by lixindong on 4/18/17.
 */

public interface ChatDataSource {
    List<ChatItem> getChats();
    void saveChats(List<ChatItem> items);
    void appendChat(ChatItem item);
}
