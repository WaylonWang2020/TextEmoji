package com.sctdroid.app.textemoji.data.source;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.sctdroid.app.textemoji.data.bean.Me;

/**
 * Created by lixindong on 4/20/17.
 */

public class MeLoader extends AsyncTaskLoader<Me> implements MeRepository.MeRepositoryObserver{
    private final MeRepository mRepository;

    public MeLoader(Context context, @NonNull MeRepository repository) {
        super(context);
        mRepository = repository;
    }

    @Override
    public Me loadInBackground() {
        return mRepository.getMe();
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
    public void onMeChanged() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
