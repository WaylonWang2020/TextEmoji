package com.sctdroid.app.wallpapertodo.data.source.local;

import com.sctdroid.app.wallpapertodo.data.bean.Joke;
import com.sctdroid.app.wallpapertodo.data.source.JokesDataSource;

import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public class JokesLocalDataSource implements JokesDataSource {
    @Override
    public List<Joke> getJokes() {
        return null;
    }

    @Override
    public void refreshJokes() {

    }
}
