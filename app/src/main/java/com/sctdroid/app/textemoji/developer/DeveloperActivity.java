package com.sctdroid.app.textemoji.developer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.utils.ActivityUtils;
import com.sctdroid.app.textemoji.utils.TCAgentUtils;

/**
 * Created by lixindong on 4/21/17.
 */

public class DeveloperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_developer);

        DeveloperFragment fragment = (DeveloperFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = DeveloperFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
        TCAgentUtils.onPageStart(this, DeveloperActivity.class.getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TCAgentUtils.onPageEnd(this, DeveloperActivity.class.getSimpleName());
    }
}
