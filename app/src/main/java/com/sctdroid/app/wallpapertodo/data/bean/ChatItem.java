package com.sctdroid.app.wallpapertodo.data.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lixindong on 4/18/17.
 */

public class ChatItem {
    public final String content;
    public final int avatarResId;

    public static final ChatItem NULL = new ChatItem("", -1) {
        @Override public boolean equals(Object o) {
            return o == this || o == null; // API specifies this broken equals implementation
        }
        @Override public String toString() {
            return "null";
        }
    };

    private ChatItem(String content, int avatarResId) {
        this.content = content;
        this.avatarResId = avatarResId;
    }

    public static class Builder {
        private int avatarResId;
        private String content;

        public Builder avatarResId(int avatarResId) {
            this.avatarResId = avatarResId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public ChatItem build() {
            return new ChatItem(content, avatarResId);
        }

    }

    public String toJson() {
        JSONObject object = toJsonObject();
        return object.toString();
    }

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("content", content);
            object.put("avatarResId", avatarResId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
