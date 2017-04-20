package com.sctdroid.app.wallpapertodo.emoji;

import android.graphics.Bitmap;
import android.net.Uri;

import com.sctdroid.app.wallpapertodo.BasePresenter;
import com.sctdroid.app.wallpapertodo.BaseView;
import com.sctdroid.app.wallpapertodo.data.bean.ChatItem;

import java.util.List;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiContract {
    interface Presenter extends BasePresenter {

        void processInput(String inputText, int fontSize, boolean withShadow);

        Uri saveBitmap(Bitmap bitmap, String filename);
    }
    interface View extends BaseView<Presenter> {

        void showChats(List<ChatItem> data);

        void showEmptyText();

        void clearEditText();
    }
}
