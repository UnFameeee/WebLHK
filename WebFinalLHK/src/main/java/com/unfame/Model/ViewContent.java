package com.unfame.Model;

public class ViewContent {
    private int id;
    private String Title;
    private String Brief;
    private String CreatedDate;

    public ViewContent(int id, String title, String brief, String createdDate) {
        this.id = id;
        Title = title;
        Brief = brief;
        CreatedDate = createdDate;
    }

    public ViewContent(String title, String brief, String createdDate) {
        Title = title;
        Brief = brief;
        CreatedDate = createdDate;
    }

    //get set id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //get set title
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    //get set brief
    public String getBrief() {
        return Brief;
    }

    public void setBrief(String brief) {
        Brief = brief;
    }

    //get set created date
    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
