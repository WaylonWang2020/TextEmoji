package com.sctdroid.app.textemoji.share;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sctdroid.app.textemoji.data.bean.ChatItem;
import com.sctdroid.app.textemoji.emoji.StorageHelper;
import com.sctdroid.app.textemoji.utils.WeixinShareUtils;
import com.sctdroid.app.textemoji.utils.compact.Compact;
import com.sctdroid.app.textemoji.views.TextEmoji;

/**
 * Created by lixindong on 4/25/17.
 */

public class ShareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Compact.getInstance().init(this);

        Intent intent = getIntent();
        if (Intent.ACTION_SEND.equals(intent.getAction())
                && "text/plain".equals(intent.getType())) {
            String text = intent.getStringExtra(Intent.EXTRA_TEXT);
            // prepare to generate data
            ChatItem item = new ChatItem.Builder()
                    .content(text)
                    .build();
            TextEmoji textEmoji = new TextEmoji(this);
            textEmoji.setText(item);

            // prepare data to share
            String filename = item.content + System.currentTimeMillis() + ".png";
            Bitmap bitmap = textEmoji.getBitmap(false);

            // save and share it
            Uri uri = StorageHelper.saveBitmap(bitmap, filename, StorageHelper.DIR_TMP);
            WeixinShareUtils.shareImage(this, uri);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Compact.DestoryInstance();
    }
}
