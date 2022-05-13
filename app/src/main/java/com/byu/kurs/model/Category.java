package com.byu.kurs.model;
public class Category {
    int id;
    String title, img;
    public Category(int id, String title, String img) {
        this.id = id;
        this.img = img;
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {return img;}
}
