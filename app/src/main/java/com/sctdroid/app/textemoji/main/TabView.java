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

@EViewGroup(R.layout.tab_item)
public class TabView extends RelativeLayout {
    private final int mNormal;
    private final int mSelected;
    private boolean mIsSelected = false;
    private final int mColorNormal;
    private final int mColorSelected;
    private final int mName;

    private boolean mInited = false;

    @ViewById(R.id.icon)
    ImageView mItemIcon;

    @ViewById(R.id.name)
    TextView mItemName;

    public TabView(Context context, @StringRes int name, @DrawableRes int normal, @DrawableRes int selected, @ColorRes int colorNormal, @ColorRes int colorSelected) {
        this(context, null, name, normal, selected, colorNormal, colorSelected);
    }
    public TabView(Context context, AttributeSet attrs, @StringRes int name, @DrawableRes int normal, @DrawableRes int selected, @ColorRes int colorNormal, @ColorRes int colorSelected) {
        super(context, attrs);
        mNormal = normal;
        mSelected = selected;
        mColorNormal = colorNormal;
        mColorSelected = colorSelected;
        mName = name;
    }

    @AfterViews
    @UiThread
    void init() {
        mItemName.setText(mName);
        mItemIcon.setImageResource(mNormal);
        mInited = true;
        setSelected(mIsSelected);
    }

    public void setSelected(boolean isSelected) {
        super.setSelected(isSelected);
        mIsSelected = isSelected;
        if (mInited) {
            mItemIcon.setImageResource(isSelected ? mSelected : mNormal);
            mItemName.setTextColor(isSelected ? mColorSelected : mColorNormal);
        }
    }

    public boolean isSelected() {
        return mIsSelected;
    }
}
