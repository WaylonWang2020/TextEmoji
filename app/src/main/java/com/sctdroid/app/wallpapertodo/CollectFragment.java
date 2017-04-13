package com.sctdroid.app.wallpapertodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lixindong on 4/13/17.
 */

public class CollectFragment extends Fragment {
    public static CollectFragment newInstance(String s){
        CollectFragment collectFragment = new CollectFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        collectFragment.setArguments(bundle);
        return collectFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_content, container, false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constants.ARGS);
        TextView textView = (TextView) view.findViewById(R.id.fragment_text_view);
        textView.setText(s);
        return view;
    }
}
