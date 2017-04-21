package com.sctdroid.app.textemoji.data.source;

import android.support.annotation.NonNull;

import com.sctdroid.app.textemoji.data.bean.Joke;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public class JokesRepository implements JokesDataSource {
    private static JokesRepository INSTANCE = null;

    private final JokesDataSource mJokesRemoteDataSource;
    private final JokesDataSource mJokesLocalDataSource;

    private List<JokesRepositoryObserver> mObservers = new ArrayList<JokesRepositoryObserver>();

    public static JokesRepository getInstance(JokesDataSource tasksRemoteDataSource,
                                              JokesDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new JokesRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    // Prevent direct instantiation.
    private JokesRepository(@NonNull JokesDataSource tasksRemoteDataSource,
                            @NonNull JokesDataSource tasksLocalDataSource) {
        mJokesRemoteDataSource = tasksRemoteDataSource;
        mJokesLocalDataSource = tasksLocalDataSource;
    }

    @Override
    public List<Joke> getJokes() {
        return mJokesRemoteDataSource.getJokes();
    }

    public void addContentObserver(JokesRepositoryObserver observer) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    public void removeContentObserver(JokesRepositoryObserver observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    private void notifyContentObserver() {
        for (JokesRepositoryObserver observer : mObservers) {
            observer.onJokesChanged();
        }
    }

    @Override
    public void refreshJokes() {
        notifyContentObserver();
    }

    public interface JokesRepositoryObserver {

        void onJokesChanged();

    }
}
