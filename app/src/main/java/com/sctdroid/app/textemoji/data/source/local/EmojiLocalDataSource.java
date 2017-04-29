package com.sctdroid.app.textemoji.data.source.local;

import android.content.Context;

import com.sctdroid.app.textemoji.data.bean.EmojiCategory;
import com.sctdroid.app.textemoji.data.source.EmojiDataSource;
import com.sctdroid.app.textemoji.utils.AssetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lixindong on 4/28/17.
 */

public class EmojiLocalDataSource implements EmojiDataSource {
    private final String FILENAME = "emoji_list.json";

    private Context mContext;

    public EmojiLocalDataSource(Context context) {
        mContext = context;
    }

    @Override
    public List<EmojiCategory> getList() {
        String data = AssetUtils.readAssertResource(mContext, FILENAME);
        return parse(data);
    }

    private List<EmojiCategory> parse(String data) {
        List<EmojiCategory> result = Collections.emptyList();
        try {
            JSONObject object = new JSONObject(data);
            JSONArray array = object.optJSONArray("data");
            if (array != null && array.length() > 0) {
                result = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    EmojiCategory category = EmojiCategory.fromJson(array.optJSONObject(i));
                    if (!category.isEmpty()) {
                        result.add(category);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
