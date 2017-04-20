package com.sctdroid.app.wallpapertodo.data.source;

import com.sctdroid.app.wallpapertodo.data.bean.Me;

/**
 * Created by lixindong on 4/20/17.
 */

public interface MeDataSource {
    Me getMe();
    void putMe(Me me);
}
