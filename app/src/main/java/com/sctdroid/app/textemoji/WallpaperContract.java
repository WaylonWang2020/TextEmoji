package com.sctdroid.app.textemoji;

import android.graphics.Bitmap;

/**
 * Created by lixindong on 4/11/17.
 */

public class WallpaperContract {
    interface Presenter extends BasePresenter {

    }
    interface View extends BaseView<Presenter> {

        void showWallPaper(Bitmap bitmap);
    }
}
