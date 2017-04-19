package com.sctdroid.app.wallpapertodo.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sctdroid.app.wallpapertodo.R;
import com.sctdroid.app.wallpapertodo.data.source.ChatsRepository;
import com.sctdroid.app.wallpapertodo.data.source.ChatsLoader;
import com.sctdroid.app.wallpapertodo.data.source.local.ChatsLocalDataSource;
import com.sctdroid.app.wallpapertodo.utils.ActivityUtils;

/**
 * Created by lixindong on 4/18/17.
 */

public class EmojiActivity extends AppCompatActivity {
    private EmojiFragment mEmojiFragment;
    private EmojiPresenter mEmojiPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_emoji);

        EmojiFragment emojiFragment = (EmojiFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (emojiFragment == null) {
            emojiFragment = EmojiFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), emojiFragment, R.id.contentFrame);
        }

        ChatsRepository repository = ChatsRepository.getInstance(new ChatsLocalDataSource(this), null);
        ChatsLoader chatsLoader = new ChatsLoader(this, repository);
        mEmojiPresenter = new EmojiPresenter(chatsLoader, getSupportLoaderManager(), repository, emojiFragment);
    }
}
