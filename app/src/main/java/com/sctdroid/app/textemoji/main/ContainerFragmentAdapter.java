package com.sctdroid.app.textemoji.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.utils.Constants;

/**
 * Created by lixindong on 2017/8/12.
 */

public class ContainerFragmentAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 4;

    private int[] mNameLsit = new int[] {
            R.string.trends,
            R.string.star,
            R.string.discovery,
            R.string.me
    };

    private int[] mColorList = new int[] {
            R.color.gunmetal,
            R.color.deep_sky_blue
    };

    private int[] mIconNormalList = new int[] {
            R.drawable.icon_trends,
            R.drawable.icon_star,
            R.drawable.icon_discovery,
            R.drawable.icon_me
    };

    private int[] mIconSelectedList = new int[] {
            R.drawable.icon_trends_focus,
            R.drawable.icon_star_focus,
            R.drawable.icon_discovery_focus,
            R.drawable.icon_me_focus
    };

    private final Context mContext;

    public ContainerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public Fragment getItem(int position) {
        int type = 0;
        switch (position) {
            case 0:
                type = Constants.TYPE_TRENDS;
                break;
            case 1:
                type = Constants.TYPE_STAR;
                break;
            case 2:
                type = Constants.TYPE_DISCOVERY;
                break;
            case 3:
                type = Constants.TYPE_ME;
                break;
        }
        return FragmentFactory.newInstance(type);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "动态";
            case 1:
                return "收藏";
            case 2:
                return "发现";
            case 3:
                return "我";
        }
        return "";
    }

    public CombinedTabView getTabView (int position) {
        return new CombinedTabView(getContext(), mNameLsit[position], mIconNormalList[position], mIconSelectedList[position], mColorList[0], mColorList[1]);
    }
}
