package com.tone.bottomtabview;

public class TabViewItem {

    private String title="";
    private int resId;
    private int type;

    public TabViewItem(String title, int resId) {
        this.title = title;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
