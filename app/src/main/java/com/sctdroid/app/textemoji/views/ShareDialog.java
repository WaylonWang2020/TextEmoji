package com.sctdroid.app.textemoji.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.sctdroid.app.textemoji.R;

/**
 * Created by lixindong on 2017/5/26.
 */

public class ShareDialog extends Dialog {
    private ImageView mImage;
    private RecyclerView.ViewHolder mViewHolder;

    public ShareDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);

        mImage = (ImageView) findViewById(R.id.image);
    }

    public void bind(@NonNull RecyclerView.ViewHolder vh) {
        mViewHolder = vh;
    }
}
