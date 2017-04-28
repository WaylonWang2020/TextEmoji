package com.sctdroid.app.textemoji.data.source;

import com.sctdroid.app.textemoji.data.bean.EmojiCategory;

import java.util.List;

/**
 * Created by lixindong on 4/28/17.
 */

public interface EmojiDataSource {
    List<EmojiCategory> getList();
}
