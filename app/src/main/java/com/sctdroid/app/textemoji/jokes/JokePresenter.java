package com.sctdroid.app.textemoji.jokes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.sctdroid.app.textemoji.data.bean.Joke;
import com.sctdroid.app.textemoji.data.source.JokesLoader;
import com.sctdroid.app.textemoji.data.source.JokesRepository;

import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public class JokePresenter implements JokesContract.Presenter, LoaderManager.LoaderCallbacks<List<Joke>> {
    private static final int JOKES_QUERY = 1;
    private final JokesContract.View mJokesView;
    private final JokesLoader mLoader;
    private final LoaderManager mLoaderManager;
    private final JokesRepository mRepository;

    public JokePresenter(@NonNull JokesLoader loader, @NonNull LoaderManager loaderManager,
                         @NonNull JokesRepository repository,
                         @NonNull JokesContract.View jokesView) {
        super();
        mJokesView = jokesView;
        mLoader = loader;
        mLoaderManager = loaderManager;
        mRepository = repository;
        jokesView.setPresenter(this);
    }

    @Override
    public void loadJokes(boolean forceUpdate) {
        mRepository.refreshJokes();
    }

    @Override
    public void create() {

    }

    @Override
    public void start() {
        mLoaderManager.initLoader(JOKES_QUERY, null, this).forceLoad();
    }

    @Override
    public Loader<List<Joke>> onCreateLoader(int id, Bundle args) {
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Joke>> loader, List<Joke> data) {
        mJokesView.showJokes(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Joke>> loader) {

    }
}
