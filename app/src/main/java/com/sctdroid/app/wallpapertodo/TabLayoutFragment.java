package com.sctdroid.app.wallpapertodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sctdroid.app.wallpapertodo.data.source.*;
import com.sctdroid.app.wallpapertodo.data.source.JokesRepository;
import com.sctdroid.app.wallpapertodo.data.source.local.JokesLocalDataSource;
import com.sctdroid.app.wallpapertodo.data.source.remote.JokesRemoteDataSource;
import com.sctdroid.app.wallpapertodo.jokes.JokeFragment;
import com.sctdroid.app.wallpapertodo.jokes.JokePresenter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TextView mTextView;
    private List<String> mTabList = new ArrayList<>();
    private TabLayoutFragmentAdapter mAdapter;
    private int[] mTabImgs = new int[]{R.drawable.home, R.drawable.collect, R.drawable.collect, R.drawable.collect};
    private List<Fragment> mFragments = new ArrayList<>();
    private JokePresenter mJokePresenter;
    private JokesRepository mJokesRepository;

    public static TabLayoutFragment newInstance(String s) {
        TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        tabLayoutFragment.setArguments(bundle);
        return tabLayoutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablayout, container, false);
        mTextView = (TextView) view.findViewById(R.id.activity_text_view);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        initTabList();
        mAdapter = new TabLayoutFragmentAdapter(getChildFragmentManager(), mTabList, getActivity(), mFragments, mTabImgs);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
        mTabLayout.addOnTabSelectedListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initFragmentList();
    }

    private void setDefaultFragment() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.sub_content, JokeFragment.newInstance(getString(R.string.item_home)))
                .commit();
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        JokeFragment jokeFragment = JokeFragment.newInstance(getString(R.string.item_home));
        // Create the presenter
        mJokesRepository = JokesRepository.getInstance(new JokesRemoteDataSource(), new JokesLocalDataSource());
        JokesLoader tasksLoader = new JokesLoader(getActivity(), mJokesRepository);
        mJokePresenter = new JokePresenter(
                tasksLoader,
                getActivity().getSupportLoaderManager(),
                mJokesRepository,
                jokeFragment
        );

        mFragments.add(jokeFragment);
        mFragments.add(CollectFragment.newInstance(getString(R.string.item_collect)));
        mFragments.add(CollectFragment.newInstance(getString(R.string.item_collect)));
        mFragments.add(CollectFragment.newInstance(getString(R.string.item_collect)));

    }

    /**
     * init the tab list.
     */
    private void initTabList() {
        mTabList.clear();
        mTabList.add(getString(R.string.item_home));
        mTabList.add(getString(R.string.item_collect));
        mTabList.add(getString(R.string.item_collect));
        mTabList.add(getString(R.string.item_collect));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabSelectedState(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabUnSelectedState(tab);
    }


    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setTabSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        String s = tabText.getText().toString();
        if (getString(R.string.item_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.home_fill);
        } else if (getString(R.string.item_collect).equals(s)) {
            tabIcon.setImageResource(R.drawable.collect_fill);
        } else if (getString(R.string.item_collect).equals(s)) {
            tabIcon.setImageResource(R.drawable.collect_fill);
        } else if (getString(R.string.item_collect).equals(s)) {
            tabIcon.setImageResource(R.drawable.collect_fill);
        }
    }

    private void setTabUnSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.black_1));
        String s = tabText.getText().toString();
        if (getString(R.string.item_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.home);
        } else if (getString(R.string.item_collect).equals(s)) {
            tabIcon.setImageResource(R.drawable.collect);
        } else if (getString(R.string.item_collect).equals(s)) {
            tabIcon.setImageResource(R.drawable.collect);
        } else if (getString(R.string.item_collect).equals(s)) {
            tabIcon.setImageResource(R.drawable.collect);
        }
    }
}
