package com.maple.smaple.customview.jianshu.bean;

/**
 * Created by gaogu on 2018/1/11.
 */

public class MenuBean {
    private String image;
    private String name;

    public MenuBean(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
