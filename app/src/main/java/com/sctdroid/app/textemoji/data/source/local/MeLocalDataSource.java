package com.sctdroid.app.textemoji.data.source.local;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.sctdroid.app.textemoji.data.bean.Me;
import com.sctdroid.app.textemoji.data.source.MeDataSource;
import com.sctdroid.app.textemoji.utils.FileAccessUtils;

/**
 * Created by lixindong on 4/20/17.
 */

public class MeLocalDataSource implements MeDataSource {
    public static final String FILENAME = "me.json";
    public static final String AVATAR_FILENAME = "avatar.png";
    private final Context mContext;

    public MeLocalDataSource(Context context) {
        mContext = context;
    }

    @Override
    public Me getMe() {
        // get data
        String data = FileAccessUtils.read(getPath());
        if (TextUtils.isEmpty(data)) {
            return Me.NULL;
        }

        return Me.fromJson(data);
    }

    @Override
    public void putMe(Me me) {
        FileAccessUtils.write(getPath(), me.toJson());
    }

    @Override
    public String uploadAvatar(Bitmap bitmap) {
        FileAccessUtils.writeBitmap(getAvatarFilename(), bitmap);
        return getAvatarFilename();
    }

    private String getPath() {
        return mContext.getFilesDir().getPath() + "/" + FILENAME;
    }

    private String getAvatarFilename() {
        return mContext.getFilesDir().getPath() + "/" + AVATAR_FILENAME;
    }
}
