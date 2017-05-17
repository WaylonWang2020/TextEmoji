package com.sctdroid.app.textemoji.soogif;

import com.sctdroid.app.textemoji.BasePresenter;
import com.sctdroid.app.textemoji.BaseView;
import com.sctdroid.app.textemoji.data.bean.Gif;

import java.util.List;

/**
 * Created by lixindong on 5/17/17.
 */

public class SooGifContract {
    interface Presenter extends BasePresenter {

        void search(String keyword);
    }

    interface View extends BaseView<Presenter> {

        void updateData(List<Gif> data);

    }
}
