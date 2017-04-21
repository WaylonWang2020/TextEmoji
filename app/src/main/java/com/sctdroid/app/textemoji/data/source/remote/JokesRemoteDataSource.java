package com.sctdroid.app.textemoji.data.source.remote;

import com.sctdroid.app.textemoji.data.JokeParser;
import com.sctdroid.app.textemoji.data.bean.Joke;
import com.sctdroid.app.textemoji.data.source.JokesDataSource;
import com.show.api.ShowApiRequest;

import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public class JokesRemoteDataSource implements JokesDataSource {
    @Override
    public void refreshJokes() {

    }

    @Override
    public List<Joke> getJokes() {
        String appid = "35563";
        String secret = "10e933fd427b4eb58fe9b1f02963733e";
        final String res = new ShowApiRequest("http://route.showapi.com/341-2", appid, secret)
                .addTextPara("time", "")
                .addTextPara("page", "")
                .addTextPara("maxResult", "")
                .post();

        final List<Joke> result = new JokeParser().parse(res);
        return result;

    }
}
