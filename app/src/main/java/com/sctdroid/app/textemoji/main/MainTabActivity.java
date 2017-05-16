package com.sctdroid.app.textemoji.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sctdroid.app.textemoji.CollectFragment;
import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.TabLayoutFragmentAdapter;
import com.sctdroid.app.textemoji.data.source.ChatsLoader;
import com.sctdroid.app.textemoji.data.source.ChatsRepository;
import com.sctdroid.app.textemoji.data.source.EmojiLoader;
import com.sctdroid.app.textemoji.data.source.EmojiRepository;
import com.sctdroid.app.textemoji.data.source.GifRepository;
import com.sctdroid.app.textemoji.data.source.GifsLoader;
import com.sctdroid.app.textemoji.data.source.JokesLoader;
import com.sctdroid.app.textemoji.data.source.JokesRepository;
import com.sctdroid.app.textemoji.data.source.MeLoader;
import com.sctdroid.app.textemoji.data.source.MeRepository;
import com.sctdroid.app.textemoji.data.source.local.ChatsLocalDataSource;
import com.sctdroid.app.textemoji.data.source.local.EmojiLocalDataSource;
import com.sctdroid.app.textemoji.data.source.local.JokesLocalDataSource;
import com.sctdroid.app.textemoji.data.source.local.MeLocalDataSource;
import com.sctdroid.app.textemoji.data.source.remote.GifRemoteDataSource;
import com.sctdroid.app.textemoji.data.source.remote.JokesRemoteDataSource;
import com.sctdroid.app.textemoji.emoji.EmojiFragment;
import com.sctdroid.app.textemoji.emoji.EmojiPresenter;
import com.sctdroid.app.textemoji.jokes.JokeFragment;
import com.sctdroid.app.textemoji.jokes.JokePresenter;
import com.sctdroid.app.textemoji.me.MeContract;
import com.sctdroid.app.textemoji.me.MeFragment;
import com.sctdroid.app.textemoji.me.MePresenter;
import com.sctdroid.app.textemoji.utils.ActivityUtils;
import com.sctdroid.app.textemoji.utils.Constants;
import com.sctdroid.app.textemoji.utils.SharePreferencesUtils;
import com.sctdroid.app.textemoji.utils.TCAgentUtils;
import com.sctdroid.app.textemoji.utils.compact.Compact;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

public class MainTabActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> mTabList = new ArrayList<>();
    private TabLayoutFragmentAdapter mAdapter;
    private int[] mTabImgs = new int[]{R.drawable.home, R.drawable.collect, R.drawable.collect, R.drawable.collect};
    private List<Fragment> mFragments = new ArrayList<>();
    private JokePresenter mJokePresenter;
    private JokesRepository mJokesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Compact.getInstance().init(this);
        ShareSDK.initSDK(this, Constants.SHARE_SDK_APPID);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        initTabList();
        mAdapter = new TabLayoutFragmentAdapter(getSupportFragmentManager(), mTabList, this, mFragments, mTabImgs);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
        mTabLayout.addOnTabSelectedListener(this);

        TCAgentUtils.onPageStart(this, com.sctdroid.app.textemoji.MainActivity.class.getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TCAgentUtils.onPageEnd(this, com.sctdroid.app.textemoji.MainActivity.class.getSimpleName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        initFragmentList();
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();

        /* Emoji Fragment */
        boolean isFirstTimeStart = SharePreferencesUtils.isFirstTimeStart(this);
        SharePreferencesUtils.applyFirstTimeStart(this, false);

        EmojiFragment mEmojiFragment = EmojiFragment.newInstance();

        ChatsRepository repository = ChatsRepository.getInstance(new ChatsLocalDataSource(this), null);
        ChatsLoader chatsLoader = new ChatsLoader(this, repository);
        MeRepository meRepository = MeRepository.getInstance(new MeLocalDataSource(this), null);
        MeLoader meLoader = new MeLoader(this, meRepository);
        EmojiRepository emojiRepository = EmojiRepository.getInstance(new EmojiLocalDataSource(this), null);
        EmojiLoader emojiLoader = new EmojiLoader(this, emojiRepository);
        GifRepository gifRepository = GifRepository.getInstance(null, new GifRemoteDataSource());
        GifsLoader gifsLoader = new GifsLoader(this, gifRepository);
        EmojiPresenter mEmojiPresenter = new EmojiPresenter(emojiLoader, meLoader, chatsLoader, gifsLoader, getSupportLoaderManager(), repository, gifRepository, mEmojiFragment);
        mEmojiPresenter.isFirstTime(isFirstTimeStart);


        /* Me Fragment */
        MeFragment fragment = MeFragment.newInstance();

        MeRepository merepository = MeRepository.getInstance(new MeLocalDataSource(this), null);
        MeLoader loader = new MeLoader(this, merepository);
        MeContract.Presenter presenter = new MePresenter(loader, getSupportLoaderManager(), merepository, fragment);

        mFragments.add(mEmojiFragment);
        mFragments.add(fragment);
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
        tabText.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
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
        tabText.setTextColor(ContextCompat.getColor(this, R.color.black_1));
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
