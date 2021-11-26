package com.unfame.model;

public class ViewContent {
    private int id;
    private String Title;
    private String Brief;
    private String Content;
    private String Username;
    private String CreatedDate;
    private String UpdateTime;
    private String AuthorId;

    public ViewContent(int id, String title, String brief, String createdDate) {
        this.id = id;
        Title = title;
        Brief = brief;
        CreatedDate = createdDate;
    }

    public ViewContent(int id, String title, String brief, String username, String createdDate) {
        this.id = id;
        Title = title;
        Brief = brief;
        Username = username;
        CreatedDate = createdDate;
    }

    public ViewContent( String title, String brief, String content, int id, String updateTime) {
        this.id = id;
        Title = title;
        Brief = brief;
        Content = content;
        UpdateTime = updateTime;
    }


    public ViewContent(String title, String brief, String content, int id) {
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

    //get set username
    public String getUsername() {return Username;}

    public void setUsername(String username) {Username = username;}


    //Method
    public String getDate(String time){
        String result = "";
        for(int i = 0; i < 10; ++i) { result += time.charAt(i); }
        return result;
    }

    public String getTime(String time){
        String result = "";
        for(int i = 12; i < time.length(); ++i) { result += time.charAt(i); }
        return result;
    }
}
