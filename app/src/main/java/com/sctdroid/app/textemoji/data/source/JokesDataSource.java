package com.sctdroid.app.textemoji.data.source;

import com.sctdroid.app.textemoji.data.bean.Joke;

import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public interface JokesDataSource {
    List<Joke> getJokes();
    void refreshJokes();
}
