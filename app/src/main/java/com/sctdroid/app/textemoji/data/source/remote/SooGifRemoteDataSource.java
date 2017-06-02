package com.sctdroid.app.textemoji.data.source.remote;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.sctdroid.app.textemoji.data.QueryFilter;
import com.sctdroid.app.textemoji.data.bean.Gif;
import com.sctdroid.app.textemoji.data.response.SooGifResponse;
import com.sctdroid.app.textemoji.data.source.GifDataSource;
import com.sctdroid.app.textemoji.utils.EncoderUtils;
import com.sctdroid.app.textemoji.utils.MetaUtils;
import com.sctdroid.app.textemoji.utils.network.HttpGetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lixindong on 5/16/17.
 */

public class SooGifRemoteDataSource implements GifDataSource {
    private static final String REQUEST_URL = "http://napi.soogif.com/oapi/backend/image/search";
    private static final String SCOPE = "zimo";
    private static String SALT = "";

    public static final String KEY_TAG = "text";
    public static final String KEY_PAGE_NUMBER = "pageNumber";
    public static final String KEY_PAGE_SIZE = "pageSize";

    public SooGifRemoteDataSource(Context context) {
        super();
        SALT = MetaUtils.getMetaData(context, "SOOGIF_SALT");
    }

    @Override
    public SooGifResponse getGifs(String tag) {
        return getGifs(tag, 0, 20);
    }

    @Override
    public SooGifResponse getGifs(QueryFilter filter) {
        if (filter == null) {
            return SooGifResponse.NULL;
        }
        String text = filter.get(KEY_TAG);
        String pageNumber = filter.get(KEY_PAGE_NUMBER);
        String pageSize = filter.get(KEY_PAGE_SIZE);

        SooGifResponse response = getGifs(text, pageNumber, pageSize);
        response.setQueryFilter(filter);

        return response;
    }

    private SooGifResponse getGifs(String tag, String pageNumber, String pageSize) {
        String result = request(tag, pageNumber, pageSize);

        SooGifResponse response = SooGifResponse.NULL;
        if (!TextUtils.isEmpty(result)) {
            response = parse(result);
        }

        return response;
    }

    @Override
    public SooGifResponse getGifs(String tag, int pageNumber, int pageSize) {
        return getGifs(tag, "" + pageNumber, "" + pageSize);
    }

    private SooGifResponse parse(String result) {
        SooGifResponse response = SooGifResponse.NULL;
        int pageNumber = 0;
        int pageSize = 0;
        int pageCount = 0;
        int allCount = 0;
        try {
            JSONObject object = new JSONObject(result);
            if (object.has("data")) {
                List<Gif> images = Collections.emptyList();
                JSONObject data = object.optJSONObject("data");
                if (!JSONObject.NULL.equals(data)) {
                    if (data.has("images")) {
                        images = parseData(data.optJSONArray("images"));
                    }
                    if (data.has("pagination")) {
                        JSONObject pagination = data.optJSONObject("pagination");
                        pageNumber = pagination.getInt("pageNumber");
                        pageSize = pagination.getInt("pageSize");
                        pageCount = pagination.getInt("pageCount");
                        allCount = pagination.getInt("allCount");
                    }

                    response = new SooGifResponse(images, pageNumber, pageSize, pageCount, allCount);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<Gif> parseData(JSONArray images) {
        List<Gif> res = new ArrayList<>();
        for (int i = 0; i < images.length(); i++) {
            Gif gif = parseItem(images.optJSONObject(i));
            res.add(gif);
        }
        return res;
    }

    private Gif parseItem(JSONObject object) {
        String url = object.optString("url");
        String preview = object.optString("fixedUrl");
        int width = object.optInt("width");
        int height = object.optInt("height");
        String title = object.optString("title");

        return Gif.Builder.newInstance()
                .height(height)
                .width(width)
                .preview(preview)
                .url(url)
                .title(title)
                .build();
    }

    @Override
    public void refreshGifs() {

    }

    private String request(String text, String pageNumber, String pageSize) {
        String timestamp = System.currentTimeMillis() + "";
        String sign = EncoderUtils.encodeMD5(text + SCOPE + timestamp + SALT);

        String url = REQUEST_URL
                + "?text=" + text
                + "&scope=" + SCOPE
                + "&timestamp=" + timestamp
                + "&sign=" + sign;

        if (!TextUtils.isEmpty(pageNumber)) {
            url += "&pageNumber=" + pageNumber;
        }

        if (!TextUtils.isEmpty(pageSize)) {
            url += "&pageSize=" + pageSize;
        }

        return HttpGetData.requestGet(url);
    }
}
