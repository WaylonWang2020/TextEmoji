package com.sctdroid.app.textemoji.data.bean;

import com.sctdroid.app.textemoji.utils.EmojiUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lixindong on 4/28/17.
 */

public class EmojiCategory {
    public static final EmojiCategory NULL = new EmojiCategory("", "", null) {
        @Override public boolean equals(Object o) {
            return o == this || o == null; // API specifies this broken equals implementation
        }
        @Override public String toString() {
            return "null";
        }
    };

    public final String name;
    public final String icon;
    public final List<Emoji> data;

    private EmojiCategory(String name, String icon, List<Emoji> data) {
        this.name = name;
        this.icon = icon;
        this.data = data;
    }

    public static class Builder {
        private String name;
        private String icon;
        private List<Emoji> data;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder icon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder data(List<Emoji> data) {
            this.data = data;
            return this;
        }

        public EmojiCategory build() {
            return new EmojiCategory(name, icon, data);
        }
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {
        JSONObject result = new JSONObject();

        try {
            result.put("name", name);
            result.put("icon", icon);

            if (!Collections.EMPTY_LIST.equals(data)) {
                JSONArray array = new JSONArray();
                for (Emoji emoji : data) {
                    array.put(emoji.toJsonObject());
                }
                result.put("data", array);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static EmojiCategory fromJson(String json) {
        try {
            return fromJson(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return EmojiCategory.NULL;
    }

    public static EmojiCategory fromJson(JSONObject object) {
        String name = object.optString("name");
        String icon = object.optString("icon");

        List<Emoji> data = Collections.emptyList();
        JSONArray array = object.optJSONArray("data");
        if (array != null && array.length() > 0) {
            data = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Emoji emoji = Emoji.fromJson(array.optJSONObject(i));
                if (EmojiUtils.isEmoji(emoji.emoji)) {
                    data.add(emoji);
                }
            }
        }
        return Builder.newInstance()
                .name(name)
                .icon(icon)
                .data(data)
                .build();
    }
}
