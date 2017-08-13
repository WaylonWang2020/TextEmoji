package com.sctdroid.app.textemoji.main;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sctdroid.app.textemoji.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lixindong on 2017/8/12.
 */

@EActivity(R.layout.activity_container)
public class ContainerActivity extends AppCompatActivity {

    @ViewById(R.id.view_pager)
    ViewPager mViewPager;

    @ViewById(R.id.tab_layout)
    TabLayout mTabLayout;

    ContainerFragmentAdapter mAdapter;

    @AfterViews
    @UiThread
    void init() {
        mAdapter = new ContainerFragmentAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            CombinedTabView tabView = mAdapter.getTabView(i);
            mTabLayout.getTabAt(i).setCustomView(tabView);
//            mTabLayout.getTabAt(i).setText(mAdapter.getPageTitle(i));
        }

//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//                    TabView tabView = (TabView) mTabLayout.getTabAt(i).getCustomView();
//                    if (tabView != null) {
//                        tabView.setSelected(i == position);
//                    }
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }
}
