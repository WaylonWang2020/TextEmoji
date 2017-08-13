package com.sctdroid.app.textemoji.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sctdroid.app.textemoji.utils.Constants;

/**
 * Created by lixindong on 2017/8/12.
 */

public class FragmentFactory {
    public static Fragment newInstance(int type) {

        Bundle args = new Bundle();

        Fragment fragment;
        switch (type) {
            case Constants.TYPE_DISCOVERY:
                fragment = DiscoveryFragment_.newInstance();
                break;
            case Constants.TYPE_ME:
                fragment = MeFragment_.newInstance();
                break;
            case Constants.TYPE_STAR:
                fragment = StarFragment_.newInstance();
                break;
            case Constants.TYPE_TRENDS:
            default:
                fragment = TrendsFragment_.newInstance();
                break;
        }
        fragment.setArguments(args);
        return fragment;
    }
}
