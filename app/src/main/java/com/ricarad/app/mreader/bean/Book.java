package com.ricarad.app.mreader.bean;

/**
 * Created by dongdong on 2018/2/28.
 */

public class Book {
    private int imageId = 00;
    private String name;
    private String path;
    public Book(){

    }
    public Book(int imageId,String name){
        this.imageId = imageId;
        this.name = name;
    }
    public Book(int imageId, String name, String path) {
        this.imageId = imageId;
        this.name = name;
        this.path = path;
    }
    public String getPath() {
        return path;
    } public void setPath(String path) {
        this.path = path;
    }
    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
