package com.sctdroid.app.textemoji.data.source;

import android.graphics.Bitmap;

import com.sctdroid.app.textemoji.data.bean.Me;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 4/20/17.
 */

public class MeRepository implements MeDataSource {
    private static MeRepository INSTANCE;
    private final MeDataSource mLocalDataSource;
    private final MeDataSource mRemoteDataSource;

    private List<MeRepositoryObserver> mObservers = new ArrayList<>();

    private MeRepository(MeDataSource localDataSource, MeDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public synchronized static MeRepository getInstance(MeDataSource localDataSource,
                                                 MeDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MeRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }
    @Override
    public Me getMe() {
        return mLocalDataSource.getMe();
    }

    @Override
    public void putMe(Me me) {
        mLocalDataSource.putMe(me);
        notifyContentObserver();
    }

    @Override
    public String uploadAvatar(Bitmap bitmap) {
        return mLocalDataSource.uploadAvatar(bitmap);
    }

    public void addContentObserver(MeRepositoryObserver observer) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    public void removeContentObserver(MeRepositoryObserver observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    public void notifyContentObserver() {
        for (MeRepositoryObserver observer : mObservers) {
            observer.onMeChanged();
        }
    }

    public interface MeRepositoryObserver {
        void onMeChanged();
    }
}
