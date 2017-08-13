package com.sctdroid.app.textemoji.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sctdroid.app.textemoji.R;

import org.androidannotations.annotations.EFragment;

/**
 * Created by lixindong on 2017/8/12.
 */

@EFragment(R.layout.activity_main)
public class MeFragment extends Fragment {
    public static MeFragment newInstance() {

        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment_();
        fragment.setArguments(args);
        return fragment;
    }
}
