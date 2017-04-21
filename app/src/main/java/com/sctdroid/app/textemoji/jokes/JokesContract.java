package com.sctdroid.app.textemoji.jokes;

import com.sctdroid.app.textemoji.BasePresenter;
import com.sctdroid.app.textemoji.BaseView;
import com.sctdroid.app.textemoji.data.bean.Joke;

import java.util.List;

/**
 * Created by lixindong on 4/14/17.
 */

public class JokesContract {
    public interface Presenter extends BasePresenter {
        void loadJokes(boolean forceUpdate);
    }
    public interface View extends BaseView<Presenter> {
        void showJokes(List<Joke> jokes);
    }
}
