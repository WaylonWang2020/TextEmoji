package com.sctdroid.app.textemoji.discovery;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.data.source.GifDataSource;
import com.sctdroid.app.textemoji.data.source.GifRepository;
import com.sctdroid.app.textemoji.data.source.GifsLoader;
import com.sctdroid.app.textemoji.data.source.remote.SooGifRemoteDataSource;
import com.sctdroid.app.textemoji.data.source.remote.TenorGifRemoteDataSource;
import com.sctdroid.app.textemoji.utils.ActivityUtils;
import com.sctdroid.app.textemoji.utils.Constants;
import com.sctdroid.app.textemoji.utils.SharePreferencesUtils;
import com.sctdroid.app.textemoji.utils.TCAgentUtils;

import static com.sctdroid.app.textemoji.utils.Constants.KEY_GIF_SORUCE;

public class SearchableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.title_activity_search_result);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // Get the intent, verify the action and get the query
        String query = "";

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            TCAgentUtils.SearchGif(this, query);
        }

        GifFragment fragment = (GifFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = GifFragment.newInstance(query);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
        int source = SharePreferencesUtils.getInt(this, KEY_GIF_SORUCE);
        GifDataSource dataSource = null;
        if (source == 0) {
            dataSource = new SooGifRemoteDataSource(this);
        } else {
            dataSource = new TenorGifRemoteDataSource(this);
        }
        TCAgentUtils.search(this, Constants.LABEL_SEARCH_PAGE, source);
        GifRepository repository = GifRepository.getInstance(null, dataSource);
        GifsLoader loader = new GifsLoader(this, repository);
        GifPresenter presenter = new GifPresenter(fragment, getSupportLoaderManager(), loader, repository);
        presenter.setGifSource(source);

        int source_id = source == 0 ? R.string.source_soogif : R.string.source_tenor;
        String subtitle = getString(R.string.format_search, getString(source_id), query);
        toolbar.setSubtitle(subtitle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TCAgentUtils.onPageStart(this, SearchableActivity.class.getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        TCAgentUtils.onPageEnd(this, SearchableActivity.class.getSimpleName());
    }
}
