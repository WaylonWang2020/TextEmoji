package com.sctdroid.app.textemoji.slide;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;


import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.views.PageIndicator;

import java.util.List;

/**
 * Created by lixindong on 2017/4/30.
 */

public class SlidePagerActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "slidepageractivity.extra.title";
    public static final String EXTRA_PICTURES = "slidepageractivity.extra.pictures";
    public static final String EXTRA_INDICATOR_TYPE = "slidepageractivity.extra.indicator.type";

    private PageIndicator mPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_slide_pager);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        SlidePagerAdapter pagerAdapter =
                new SlidePagerAdapter(getSupportFragmentManager());

        if (getIntent() == null) return;

        // set title
        ActionBar ab = getActionBar();
        if (ab != null) {
            String title = getIntent().getStringExtra(EXTRA_TITLE);
            if (!TextUtils.isEmpty(title)) {
                ab.setTitle(title);
            }
        }

        // set pictures
        List<Integer> pics = getIntent().getIntegerArrayListExtra(EXTRA_PICTURES);
        pagerAdapter.addAll(pics);

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPageIndicator = (PageIndicator) findViewById(R.id.indicator);
        mPageIndicator.setViewPager(pager);
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }
}