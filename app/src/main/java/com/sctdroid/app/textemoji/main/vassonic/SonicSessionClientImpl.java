package com.sctdroid.app.textemoji.main.vassonic;

import android.os.Bundle;
import android.webkit.WebView;

import com.tencent.sonic.sdk.SonicSessionClient;

import java.util.HashMap;

/**
 * Created by lixindong on 2017/8/13.
 */

public class SonicSessionClientImpl extends SonicSessionClient {
    private WebView webView;
    public void bindWebView(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void loadUrl(String url, Bundle extraData) {
        webView.loadUrl(url);
    }

    @Override
    public void loadDataWithBaseUrl(String baseUrl, String data, String mimeType, String encoding,
                                    String historyUrl) {
        webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    @Override
    public void loadDataWithBaseUrlAndHeader(String baseUrl, String data, String mimeType, String encoding, String historyUrl, HashMap<String, String> headers) {

    }
}