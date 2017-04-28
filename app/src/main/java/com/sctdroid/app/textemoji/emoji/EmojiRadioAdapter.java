package com.sctdroid.app.textemoji.emoji;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RadioButton;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.data.bean.EmojiCategory;
import com.sctdroid.app.textemoji.views.adaptableviews.RadioAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lixindong on 4/28/17.
 */

public class EmojiRadioAdapter extends RadioAdapter {
    private List<EmojiCategory> mData = new ArrayList<>();
    private final Context mContext;

    public EmojiRadioAdapter(Context context) {
        mContext =context;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public EmojiCategory getItem(int position) {
        return position < getCount() ? mData.get(position) : EmojiCategory.NULL;
    }

    @Override
    public RadioButton getRadioButton(int position) {
        RadioButton button = (RadioButton) LayoutInflater.from(mContext).inflate(R.layout.emoji_radio_button, null);
        button.setText(getItem(position).icon);

        return button;
    }

    public void updateData(List<EmojiCategory> data) {
        mData.clear();
        if (!Collections.EMPTY_LIST.equals(data)) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
}
