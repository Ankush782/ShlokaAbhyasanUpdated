package com.dsss.ankush.shlokaabhyasanupdated;

import java.io.Serializable;

public class SubCategoryType implements Serializable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getHymn() {
        return hymn;
    }

    public void setHymn(String hymn) {
        this.hymn = hymn;
    }

    String name,image,discription,hymn;
}
