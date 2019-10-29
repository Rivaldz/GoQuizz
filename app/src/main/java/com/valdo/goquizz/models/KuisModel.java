package com.valdo.goquizz.models;

public class KuisModel {
    public String title;
    public String description;
    public int image;

    public KuisModel(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
