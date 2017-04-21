package com.sctdroid.app.textemoji;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by lixindong on 4/11/17.
 */

public class WallpaperPresenter implements WallpaperContract.Presenter {
    private final Context mContext;
    private final WallpaperContract.View mWallPaperView;

    public WallpaperPresenter(Context context, @NonNull WallpaperContract.View wallpaperView) {
        super();
        mContext = context;
        mWallPaperView = wallpaperView;
        mWallPaperView.setPresenter(this);
    }

    @Override
    public void start() {
        loadWallpaper();
    }

    protected void loadWallpaper() {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                mWallPaperView.showWallPaper(bitmap);
                SetLockWallPaper(bitmap);
            }

            @Override
            protected Bitmap doInBackground(Void... params) {
                WallpaperManager wallpaperManager = WallpaperManager
                        .getInstance(mContext);
                // 获取当前壁纸
                return ((BitmapDrawable) wallpaperManager.getDrawable()).getBitmap();
            }
        }.execute();
    }
    private void SetLockWallPaper(Bitmap bm) {
        // TODO Auto-generated method stub
        try {
            WallpaperManager mWallManager = WallpaperManager.getInstance(mContext);
            Class class1 = mWallManager.getClass();//获取类名
            Method setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper",Bitmap.class);//获取设置锁屏壁纸的函数
            setWallPaperMethod.invoke(mWallManager, bm);//调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
            Toast.makeText(mContext, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
