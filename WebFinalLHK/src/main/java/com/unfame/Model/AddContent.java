package com.unfame.Model;

public class AddContent {
    private int id;
    private String Title;
    private String Brief;
    private String Content;
    private String CreatedDate;
    private String UpdateTime;
    private String AuthorId;

    public AddContent(int id, String title, String brief, String content, String createdDate, String updateTime, String authorId) {
        this.id = id;
        Title = title;
        Brief = brief;
        Content = content;
        CreatedDate = createdDate;
        UpdateTime = updateTime;
        AuthorId = authorId;
    }

    public AddContent(String title, String brief, String content, String createdDate, String authorId) {
        Title = title;
        Brief = brief;
        Content = content;
        CreatedDate = createdDate;
        AuthorId = authorId;
    }

    public AddContent(String title, String brief, String content, String createdDate, String updateTime, String authorId) {
        Title = title;
        Brief = brief;
        Content = content;
        CreatedDate = createdDate;
        UpdateTime = updateTime;
        AuthorId = authorId;
    }

    public AddContent(int id, String title, String brief, String content) {
        this.id = id;
        Title = title;
        Brief = brief;
        Content = content;
    }

    public AddContent(String title, String brief, String content) {
        Title = title;
        Brief = brief;
        Content = content;
    }

    public AddContent(String title, String brief, String content, String createdDate) {
        Title = title;
        Brief = brief;
        Content = content;
        CreatedDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBrief() {
        return Brief;
    }

    public void setBrief(String brief) {
        Brief = brief;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
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
}
