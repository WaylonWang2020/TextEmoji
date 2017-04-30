package com.sctdroid.app.textemoji.slide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 2017/4/30.
 */

public class SlidePagerAdapter extends FragmentStatePagerAdapter {
    private List<Integer> picList = new ArrayList<>();

    public SlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return SlidePageFragment.newInstance(picList.get(i), i == getCount() - 1);
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    public void addAll(List<Integer> picList) {
        this.picList = picList;
    }
}
