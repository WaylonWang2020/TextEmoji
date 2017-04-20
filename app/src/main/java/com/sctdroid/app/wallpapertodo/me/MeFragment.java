package com.sctdroid.app.wallpapertodo.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sctdroid.app.wallpapertodo.R;
import com.sctdroid.app.wallpapertodo.data.bean.Me;

/**
 * Created by lixindong on 4/20/17.
 */

public class MeFragment extends Fragment implements MeContract.View {

    private MeContract.Presenter mPresenter;

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_me, container, false);

        // do initial things here
        initHeadBar(root);

        return root;
    }

    private void initHeadBar(View root) {
        TextView title = (TextView) root.findViewById(R.id.title);
        ImageView left_option = (ImageView) root.findViewById(R.id.left_option);
        ImageView right_option = (ImageView) root.findViewById(R.id.right_option);
        title.setText(R.string.string_me);
        left_option.setVisibility(View.GONE);
        right_option.setVisibility(View.GONE);
    }

    /**
     * Methods for life circles
     */
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    /**
     * Methods for interface MeContract.View
     */

    @Override
    public void setPresenter(MeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMe(Me data) {

    }
}
