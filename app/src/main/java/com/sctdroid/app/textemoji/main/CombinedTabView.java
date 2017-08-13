package com.sctdroid.app.textemoji.main;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sctdroid.app.textemoji.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lixindong on 2017/8/13.
 */

public class CombinedTabView extends RelativeLayout {
    private final int mNormal;
    private final int mSelected;
    private final int mColorNormal;
    private final int mColorSelected;
    private final int mName;

    private boolean mInited = false;

    ImageView mItemIcon;

    TextView mItemName;

    public CombinedTabView(Context context, @StringRes int name, @DrawableRes int normal, @DrawableRes int selected, @ColorRes int colorNormal, @ColorRes int colorSelected) {
        this(context, null, name, normal, selected, colorNormal, colorSelected);
    }
    public CombinedTabView(Context context, AttributeSet attrs, @StringRes int name, @DrawableRes int normal, @DrawableRes int selected, @ColorRes int colorNormal, @ColorRes int colorSelected) {
        super(context, attrs);
        inflate(context, R.layout.tab_item, this);
        mNormal = normal;
        mSelected = selected;
        mColorNormal = colorNormal;
        mColorSelected = colorSelected;
        mName = name;

        mItemIcon = (ImageView) findViewById(R.id.icon);
        mItemName = (TextView) findViewById(R.id.name);

        init();
    }

    void init() {
        mItemName.setText(mName);
        mItemIcon.setImageResource(mNormal);
    }

    public void setSelected(boolean isSelected) {
        super.setSelected(isSelected);
        mItemIcon.setImageResource(isSelected ? mSelected : mNormal);
        mItemName.setTextColor(isSelected ? mColorSelected : mColorNormal);
        mItemName.setTextColor(getContext().getResources().getColor(isSelected ? mColorSelected : mColorNormal));
    }
}
