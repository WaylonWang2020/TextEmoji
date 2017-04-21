package com.sctdroid.app.textemoji.emoji;

import android.graphics.Bitmap;
import android.net.Uri;

import com.sctdroid.app.textemoji.BasePresenter;
import com.sctdroid.app.textemoji.BaseView;
import com.sctdroid.app.textemoji.data.bean.ChatItem;
import com.sctdroid.app.textemoji.data.bean.Me;

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

        void updateMe(Me me);
    }
}
