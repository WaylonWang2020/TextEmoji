package com.sctdroid.app.textemoji.views;

import android.content.Context;
import android.util.AttributeSet;

import com.sctdroid.app.textemoji.views.adaptableviews.ViewTager;

/**
 * Created by lixindong on 4/28/17.
 */

public class EmojiTager extends ViewTager {
    public EmojiTager(Context context) {
        this(context, null);
    }

    public EmojiTager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiTager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
