package com.maple.smaple.customview.jianshu.bean;

/**
 * Created by gaogu on 2018/1/11.
 */

public class BannerBean {

    private String image;
    private String title;

    public BannerBean(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
