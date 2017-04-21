package com.sctdroid.app.textemoji.data;

import android.text.TextUtils;

import com.sctdroid.app.textemoji.data.bean.Joke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lixindong on 4/13/17.
 */

public class JokeParser {
    public List<Joke> parse(String content) {
        if (TextUtils.isEmpty(content)) return Collections.emptyList();

        try {
            JSONObject object = new JSONObject(content);
            if (JSONObject.NULL.equals(object)) {
                return Collections.emptyList();
            }
            object = object.getJSONObject("showapi_res_body");
            if (JSONObject.NULL.equals(object)) {
                return Collections.emptyList();
            }

            JSONArray array = object.optJSONArray("contentlist");
            if (array == null) {
                return Collections.emptyList();
            }

            List<Joke> list = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Joke joke = new Joke.Builder()
                        .image(array.getJSONObject(i).getString("img"))
                        .createTime(array.getJSONObject(i).getString("ct"))
                        .title(array.getJSONObject(i).getString("title"))
                        .build();
                list.add(joke);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
