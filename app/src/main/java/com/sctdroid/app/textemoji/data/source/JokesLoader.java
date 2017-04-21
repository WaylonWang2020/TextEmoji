package com.sctdroid.app.textemoji.data.source;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.sctdroid.app.textemoji.data.bean.Joke;

import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public class JokesLoader extends AsyncTaskLoader<List<Joke>> implements JokesRepository.JokesRepositoryObserver {
    private final JokesRepository mRepository;

    public JokesLoader(Context context, @NonNull JokesRepository repository) {
        super(context);
        mRepository = repository;
    }

    @Override
    public List<Joke> loadInBackground() {
        return mRepository.getJokes();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        mRepository.removeContentObserver(this);
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onStartLoading() {
        mRepository.addContentObserver(this);
    }

    @Override
    public void onJokesChanged() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
