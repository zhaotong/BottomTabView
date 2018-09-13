package com.tone.myapplication;

import java.util.Objects;

public class Channel {
    private String title;
    private long id;
    private String content;

    public Channel(String title, String content, long id) {
        this.title = title;
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return id == channel.id &&
                Objects.equals(title, channel.title) &&
                Objects.equals(content, channel.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, id, content);
    }
}
