package com.sctdroid.app.wallpapertodo.data.bean;

/**
 * Created by lixindong on 4/20/17.
 */

public class Me {
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
}
