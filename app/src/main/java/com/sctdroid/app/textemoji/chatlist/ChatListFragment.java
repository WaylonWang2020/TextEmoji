package com.sctdroid.app.textemoji.chatlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.emoji.EmojiActivity;

/**
 * Created by lixindong on 5/16/17.
 */

public class ChatListFragment extends Fragment implements View.OnClickListener {
    public static ChatListFragment newInstance() {
        return new ChatListFragment();
    }

    private final int LIST_COUNT = 3;

    private int[] viewList = new int[] {
            R.id.custom_emojis,
            R.id.feedback,
            R.id.favourite
    };

    private int[] iconList = new int[] {
            R.drawable.avatar,
            R.drawable.avatar,
            R.drawable.avatar
    };

    private int[] titleList = new int[] {
            R.string.custom_emojis,
            R.string.feedback,
            R.string.favourite
    };

    private int[] summaryList = new int[] {
            R.string.my_emojis,
            R.string.history_with_developer,
            R.string.good_things_here
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chatlist, container, false);

        initViews(root);

        return root;
    }

    private void initViews(View root) {
        for (int i = 0; i < LIST_COUNT; i++) {
            initItem(root, viewList[i], iconList[i], titleList[i], summaryList[i]);
        }
    }

    private void initItem(View rootView, int viewId, @DrawableRes int iconId, @StringRes int titleId, @StringRes int summaryId) {
        View view = rootView.findViewById(viewId);
        ImageView icon = (ImageView) view.findViewById(R.id.item_icon);
        TextView title = (TextView) view.findViewById(R.id.item_title);
        TextView summary = (TextView) view.findViewById(R.id.item_summary);

        icon.setImageResource(iconId);
        title.setText(titleId);
        summary.setText(summaryId);

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_emojis:
                Intent intent = new Intent(getActivity(), EmojiActivity.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                break;
            case R.id.favourite:
                break;
        }
    }
}
