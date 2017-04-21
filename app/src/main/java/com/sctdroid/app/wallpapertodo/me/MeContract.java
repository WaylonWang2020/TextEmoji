package com.sctdroid.app.wallpapertodo.me;

import android.graphics.Bitmap;

import com.sctdroid.app.wallpapertodo.BasePresenter;
import com.sctdroid.app.wallpapertodo.BaseView;
import com.sctdroid.app.wallpapertodo.data.bean.Me;

/**
 * Created by lixindong on 4/20/17.
 */

public class MeContract {
    interface Presenter extends BasePresenter {

        void updateAvatar(Bitmap bitmap);
    }

    interface View extends BaseView<Presenter> {

        void showMe(Me data);

    }
}
