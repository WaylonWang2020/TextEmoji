package com.sctdroid.app.textemoji.data.source.local;

import com.sctdroid.app.textemoji.data.bean.Joke;
import com.sctdroid.app.textemoji.data.source.JokesDataSource;

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
