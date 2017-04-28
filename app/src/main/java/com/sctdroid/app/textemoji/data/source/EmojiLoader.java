package com.sctdroid.app.textemoji.data.source;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.sctdroid.app.textemoji.data.bean.EmojiCategory;

import java.util.List;

/**
 * Created by lixindong on 4/28/17.
 */

public class EmojiLoader extends AsyncTaskLoader<List<EmojiCategory>> {
    private final EmojiRepository mRepository;

    public EmojiLoader(Context context, EmojiRepository repository) {
        super(context);
        mRepository = repository;
    }

    @Override
    public List<EmojiCategory> loadInBackground() {
        return mRepository.getList();
    }
}
