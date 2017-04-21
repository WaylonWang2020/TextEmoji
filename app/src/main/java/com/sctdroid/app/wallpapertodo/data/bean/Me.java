package com.sctdroid.app.wallpapertodo.data.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lixindong on 4/20/17.
 */

public class Me {
    public static final Me NULL = new Me("") {
        @Override public boolean equals(Object o) {
            return o == this || o == null; // API specifies this broken equals implementation
        }
        @Override public String toString() {
            return "null";
        }
    };

    public final String avatar;

    private Me(String avatar) {
        this.avatar = avatar;
    }

    public static class Builder {
        public String avatar;

        public Builder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Me build() {
            return new Me(avatar);
        }
    }

    public String toJson() {
        JSONObject object = new JSONObject();
        try {
            object.put("avatar", avatar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public static Me fromJson(String json) {
        try {
            return fromJson(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Me.NULL;
    }

    public static Me fromJson(JSONObject object) {
        String avatar = object.optString("avatar");
        return new Builder().avatar(avatar).build();
    }
}
