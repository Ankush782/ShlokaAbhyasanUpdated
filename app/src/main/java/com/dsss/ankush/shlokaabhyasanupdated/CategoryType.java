package com.dsss.ankush.shlokaabhyasanupdated;

import java.io.Serializable;

public class CategoryType implements Serializable{
    String name,image,discriptions,subcategory;

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

    public String getDiscriptions() {
        return discriptions;
    }

    public void setDiscriptions(String discriptions) {
        this.discriptions = discriptions;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
