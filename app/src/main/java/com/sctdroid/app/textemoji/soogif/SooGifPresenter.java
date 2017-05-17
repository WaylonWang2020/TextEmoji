package com.sctdroid.app.textemoji.soogif;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.sctdroid.app.textemoji.data.bean.Gif;
import com.sctdroid.app.textemoji.data.source.GifRepository;
import com.sctdroid.app.textemoji.data.source.GifsLoader;

import java.util.List;

/**
 * Created by lixindong on 5/17/17.
 */

public class SooGifPresenter implements SooGifContract.Presenter, LoaderManager.LoaderCallbacks<List<Gif>> {
    private final GifRepository mGifRepository;
    private final GifsLoader mGifsLoader;
    private final LoaderManager mLoaderManager;

    private final int QUERY_GIF = 6;
    private final SooGifContract.View mSoogifView;

    public SooGifPresenter(LoaderManager loaderManager, GifsLoader gifsLoader, GifRepository gifRepository, SooGifContract.View soogifView) {
        mGifsLoader = gifsLoader;
        mGifRepository = gifRepository;
        mLoaderManager = loaderManager;
        mSoogifView = soogifView;

        soogifView.setPresenter(this);
    }

    @Override
    public void create() {
        mLoaderManager.initLoader(QUERY_GIF, null, this);
    }

    @Override
    public void start() {
        search("哈哈");
    }

    @Override
    public void search(String keyword) {
        mGifsLoader.setQueryFilter(new GifsLoader.QueryFilter(keyword));
        mGifRepository.refreshGifs();
    }

    @Override
    public Loader<List<Gif>> onCreateLoader(int id, Bundle args) {
        return mGifsLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Gif>> loader, List<Gif> data) {
        mSoogifView.updateData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Gif>> loader) {

    }
}
