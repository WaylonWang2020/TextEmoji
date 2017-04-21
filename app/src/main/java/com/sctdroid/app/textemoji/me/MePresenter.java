package com.sctdroid.app.textemoji.me;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.sctdroid.app.textemoji.data.bean.Me;
import com.sctdroid.app.textemoji.data.source.MeLoader;
import com.sctdroid.app.textemoji.data.source.MeRepository;

/**
 * Created by lixindong on 4/20/17.
 */

public class MePresenter implements MeContract.Presenter, LoaderManager.LoaderCallbacks<Me> {

    private final MeContract.View mView;
    private final MeRepository mRepository;
    private final MeLoader mLoader;
    private final LoaderManager mLoaderManager;

    private final int ME_QUERY = 3;

    public MePresenter(MeLoader loader, LoaderManager loaderManager, MeRepository repository, MeFragment meFragment) {
        mLoader = loader;
        mLoaderManager = loaderManager;
        mRepository = repository;
        mView = meFragment;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mLoaderManager.initLoader(ME_QUERY, null, this).forceLoad();
    }

    @Override
    public void updateAvatar(Bitmap bitmap) {
        // upload bitmap
        String avatarPath = mRepository.uploadAvatar(bitmap);
        // update me and put
        Me newMe = new Me.Builder()
                .avatar(avatarPath)
                .build();
        mRepository.putMe(newMe);
    }

    @Override
    public Loader<Me> onCreateLoader(int id, Bundle args) {
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<Me> loader, Me data) {
        mView.showMe(data);
    }

    @Override
    public void onLoaderReset(Loader<Me> loader) {

    }
}
