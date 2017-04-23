package com.sctdroid.app.textemoji.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.data.source.ChatsRepository;
import com.sctdroid.app.textemoji.data.source.ChatsLoader;
import com.sctdroid.app.textemoji.data.source.MeLoader;
import com.sctdroid.app.textemoji.data.source.MeRepository;
import com.sctdroid.app.textemoji.data.source.local.ChatsLocalDataSource;
import com.sctdroid.app.textemoji.data.source.local.MeLocalDataSource;
import com.sctdroid.app.textemoji.utils.ActivityUtils;
import com.sctdroid.app.textemoji.utils.compact.Compact;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiActivity extends AppCompatActivity {
    private EmojiFragment mEmojiFragment;
    private EmojiPresenter mEmojiPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Compact.DestoryInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_emoji);

        Compact.getInstance().init(this);

        EmojiFragment emojiFragment = (EmojiFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (emojiFragment == null) {
            emojiFragment = EmojiFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), emojiFragment, R.id.contentFrame);
        }

        ChatsRepository repository = ChatsRepository.getInstance(new ChatsLocalDataSource(this), null);
        ChatsLoader chatsLoader = new ChatsLoader(this, repository);
        MeRepository meRepository = MeRepository.getInstance(new MeLocalDataSource(this), null);
        MeLoader meLoader = new MeLoader(this, meRepository);
        mEmojiPresenter = new EmojiPresenter(meLoader, chatsLoader, getSupportLoaderManager(), repository, emojiFragment);
    }
}
