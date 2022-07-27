package com.byu.kurs.model;
public class Course {
    int id, category;
    String img, name, title, date, level, color, url;
    public Course(int id, String img, String name, String title, String date, String level, String color, String url, int category) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.title = title;
        this.date = date;
        this.level = level;
        this.color = color;
        this.url = url;
        this.category = category;
    }
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {return name; }
    public void setName(String name) {this.name = name; }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
