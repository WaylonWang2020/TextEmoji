package com.sctdroid.app.textemoji.data.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lixindong on 4/28/17.
 */

public class Emoji {
    public static final Emoji NULL = new Emoji("", "") {
        @Override public boolean equals(Object o) {
            return o == this || o == null; // API specifies this broken equals implementation
        }
        @Override public String toString() {
            return "null";
        }
    };

    public final String name;
    public final String emoji;

    private Emoji(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    public static class Builder {
        private String name;
        private String emoji;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder emoji(String emoji) {
            this.emoji = emoji;
            return this;
        }

        public Emoji build() {
            return new Emoji(name, emoji);
        }
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {
        JSONObject result = new JSONObject();
        try {
            result.put("name", name);
            result.put("emoji", emoji);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Emoji fromJson(String json) {
        try {
            return fromJson(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Emoji.NULL;
    }

    public static Emoji fromJson(JSONObject object) {
        String name = object.optString("name");
        String emoji = object.optString("emoji");
        return Builder.newInstance()
                .emoji(emoji)
                .name(name)
                .build();
    }
}
