package com.sctdroid.app.textemoji.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.webkit.WebView;

import com.sctdroid.app.textemoji.R;
import com.sctdroid.app.textemoji.main.vassonic.SonicEngineHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lixindong on 2017/8/12.
 */

@EFragment(R.layout.layout_webview)
public class StarFragment extends Fragment {
    public static StarFragment newInstance() {

        Bundle args = new Bundle();

        StarFragment fragment = new StarFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @ViewById(R.id.webview)
    WebView mWebview;

    @AfterViews
    void init() {
        SonicEngineHelper helper = new SonicEngineHelper(getContext());
        helper.start(mWebview, "http://antiless.com:8080/#/star", null);
    }
}
