package com.maple.smaple.customview.timeline;

/**
 * Created by gaogu on 2018/1/22.
 */

public class TimeLineBean {
    private String title;
    private String time;
    private String content;
    private boolean isLast;

    public TimeLineBean(String title, String time, String content, boolean isLast) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.isLast = isLast;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
