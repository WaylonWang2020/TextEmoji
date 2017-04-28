package com.sctdroid.app.textemoji.data.source;

import com.sctdroid.app.textemoji.data.bean.EmojiCategory;

import java.util.List;

/**
 * Created by lixindong on 4/28/17.
 */

public class EmojiRepository implements EmojiDataSource {
    private static EmojiRepository INSTANCE;
    private final EmojiDataSource mLocalDataSource;
    private final EmojiDataSource mRemoteDataSource;

    public static synchronized EmojiRepository getInstance(EmojiDataSource localDataSource,
                                              EmojiDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new EmojiRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private EmojiRepository(EmojiDataSource localDataSource,
                            EmojiDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public List<EmojiCategory> getList() {
        return mLocalDataSource.getList();
    }
}
