package com.finnovation.task;

import java.io.Serializable;

public class ImagesDao implements Serializable {

    private String id = "";
    private String images = "";

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
