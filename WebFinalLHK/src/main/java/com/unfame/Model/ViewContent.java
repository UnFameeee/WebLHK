package com.unfame.Model;

public class ViewContent {
    private int id;
    private String Title;
    private String Brief;
    private String Content;
    private String CreatedDate;
    private String UpdateTime;
    private String AuthorId;

    public ViewContent(int id, String title, String brief, String createdDate) {
        this.id = id;
        Title = title;
        Brief = brief;
        CreatedDate = createdDate;
    }
    public ViewContent( String title, String brief,int id, String updateTime) {
        this.id = id;
        Title = title;
        Brief = brief;
        UpdateTime = updateTime;
    }


    public ViewContent( String title, String brief, String content, int id) {
        this.id = id;
        Title = title;
        Brief = brief;
        Content = content;
    }

    public ViewContent(String title, String brief, String createdDate) {
        Title = title;
        Brief = brief;
        CreatedDate = createdDate;
    }

    public ViewContent(int id, String title, String brief) {
        this.id = id;
        Title = title;
        Brief = brief;
    }

    public ViewContent(int id, String title, String brief, String content, String createdDate, String updateTime, String authorId) {
        this.id = id;
        Title = title;
        Brief = brief;
        Content = content;
        CreatedDate = createdDate;
        UpdateTime = updateTime;
        AuthorId = authorId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
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
