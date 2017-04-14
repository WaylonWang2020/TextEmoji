package com.sctdroid.app.wallpapertodo.jokes;

import com.sctdroid.app.wallpapertodo.BasePresenter;
import com.sctdroid.app.wallpapertodo.BaseView;
import com.sctdroid.app.wallpapertodo.data.bean.Joke;

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
