package com.sctdroid.app.wallpapertodo.data.bean;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lixindong on 4/18/17.
 */

public class ChatItem {
    public final String content;
    public final int avatarResId;
    public final int fontSize;
    public final boolean withShadow;

    public static final ChatItem NULL = new ChatItem("", -1, 0, false) {
        @Override public boolean equals(Object o) {
            return o == this || o == null; // API specifies this broken equals implementation
        }
        @Override public String toString() {
            return "null";
        }
    };

    private ChatItem(String content, int avatarResId, int fontSize, boolean withShadow) {
        this.content = content;
        this.avatarResId = avatarResId;
        this.fontSize = fontSize;
        this.withShadow = withShadow;
    }

    public static class Builder {
        private int avatarResId = -1;
        private String content;
        private int fontSize = 0;
        private boolean withShadow = false;

        public Builder avatarResId(int avatarResId) {
            this.avatarResId = avatarResId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder fontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder withShadow(boolean withShadow) {
            this.withShadow = withShadow;
            return this;
        }

        public ChatItem build() {
            return new ChatItem(content, avatarResId, fontSize, withShadow);
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
            object.put("fontSize", fontSize);
            object.put("withShadow", withShadow);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static ChatItem fromJsonObject(@NonNull JSONObject object) {
        String content = object.optString("content");
        int avatarResId = object.optInt("avatarResId");
        int fontSize = object.optInt("fontSize");
        boolean withShadow = object.optBoolean("withShadow");
        return new Builder()
                .avatarResId(avatarResId)
                .content(content)
                .fontSize(fontSize)
                .withShadow(withShadow)
                .build();
    }
}
