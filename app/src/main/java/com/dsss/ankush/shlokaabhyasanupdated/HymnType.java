package com.dsss.ankush.shlokaabhyasanupdated;

import java.io.Serializable;

public class HymnType implements Serializable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHymn() {
        return hymn;
    }

    public void setHymn(String hymn) {
        this.hymn = hymn;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    String name,discription,image,hymn,reference;
}
