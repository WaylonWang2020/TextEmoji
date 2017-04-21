package com.sctdroid.app.textemoji.data.bean;

/**
 * Created by lixindong on 4/13/17.
 */
public class Joke {
    public static Joke NULL = new Joke("","","","");

    protected final String title;
    protected final String text;
    protected final String create_time;
    protected final String image;

    public Joke(String title, String text, String create_time, String image) {
        this.title = title;
        this.text = text;
        this.create_time = create_time;
        this.image = image;
    }

    public static class Builder {
        protected String title;
        protected String text;
        protected String create_time;
        protected String image;

        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder text(String text) {
            this.text = text;
            return this;
        }
        public Builder createTime(String create_time) {
            this.create_time = create_time;
            return this;
        }
        public Builder image(String image) {
            this.image = image;
            return this;
        }
        public Joke build() {
            return new Joke(title, text, create_time, image);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getCreateTime() {
        return create_time;
    }

    public String getImage() {
        return image;
    }
}
