package com.sctdroid.app.textemoji.slide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sctdroid.app.textemoji.R;

/**
 * Created by lixindong on 2017/4/30.
 */

public class SlidePageFragment extends Fragment {
    private static final String PIC_RES_ID = "slidepagefragment.pic_res_id";
    private static final String SHOW_START_BUTTON = "slidepagefragment.show_start_button";

    public static SlidePageFragment newInstance(@NonNull final int picResId, boolean showStartButton) {
        Bundle arguments = new Bundle();
        arguments.putInt(PIC_RES_ID, picResId);
        arguments.putBoolean(SHOW_START_BUTTON, showStartButton);

        SlidePageFragment fragment = new SlidePageFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slide_page, container, false);

        ImageView view = (ImageView) rootView.findViewById(R.id.pic);
        TextView button = (TextView) rootView.findViewById(R.id.button);

        Bundle arguments = getArguments();
        if (arguments != null) {
            int resId = arguments.getInt(PIC_RES_ID);
            boolean showStartButton = arguments.getBoolean(SHOW_START_BUTTON);
            Glide.with(getActivity())
                    .load(resId)
                    .into(view);
            if (showStartButton) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });
                button.setVisibility(View.VISIBLE);
            } else {
                button.setOnClickListener(null);
                button.setVisibility(View.GONE);
            }
        }

        return rootView;
    }
}
