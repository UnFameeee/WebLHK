package com.unfame.dao;
import com.unfame.global.IdGlobal;
import com.unfame.model.ViewContent;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewContentDAO {
    String driver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String database = "WebLHK?useSSL=false";
    String userid = "root";
    String password = "root";

//    private static final String INSERT_CONTENT_SQL = "INSERT INTO Content" + " (Title, Brief, Content, CreateDate, UpdateTime, AuthorId) VALUES" + " (?, ? ,? ,? ,? ,? ,?);";
    private static final String DELETE_CONTENTS_SQL = "DELETE FROM Content WHERE Id = ?";
//    private static final String SELECT_ALL_CONTENTS = "SELECT * FROM Content";

    private static final String SELECT_CONTENT_BY_ID = "SELECT Title, Brief, Content, CreateDate, UpdateTime, AuthorId FROM Content WHERE Id = ?";
    private static final String UPDATE_CONTENT_SQL = "UPDATE Content set Title = ?,Brief= ?, Content =? , UpdateTime =? where id = ?;";
    //OR authorid LIKE '%' ? '%'
    private static final String SEARCH_TOTAL_NUMBER_CONTENTS_ADMIN = "SELECT COUNT(Id) AS max FROM weblhk.content WHERE id LIKE '%' ? '%' OR title LIKE '%' ? '%' OR brief LIKE '%' ? '%' OR content LIKE '%' ? '%' OR createdate LIKE '%' ? '%'";
    private static final String SEARCH_TOTAL_NUMBER_CONTENTS_MEMBER = "SELECT COUNT(Id) AS max FROM weblhk.content WHERE id LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR title LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR brief LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR content LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR createdate LIKE '%' ? '%'AND AuthorId = " + IdGlobal.UserId;
    private static final String SEARCH_CONTENTS_ADMIN = "SELECT Content.Id, Content.Title, Content.Brief, Content.Content, Member.Username, Content.CreateDate FROM Content, Member  WHERE Content.AuthorId = Member.Id AND Content.id LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND title LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND brief LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND content LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND createdate LIKE '%' ? '%'LIMIT ? , 10";
    private static final String SEARCH_CONTENTS_MEMBER = "SELECT * FROM weblhk.content WHERE id LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR title LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR brief LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR content LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR createdate LIKE '%' ? '%'AND AuthorId = " + IdGlobal.UserId + " LIMIT ? , 10";;

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(connectionUrl + database, userid, password);

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public ViewContentDAO() {}

    //Select all Content
    public List<ViewContent> selectAllContents(String command)  {
        List<ViewContent> content = new ArrayList<>();
        //Step 1: Connection

        //If the role is admin (show All) / member (show Member)
        String SELECT_TOTAL_NUMBER_CONTENTS;
        if(Objects.equals(IdGlobal.Role, "Admin")){
            SELECT_TOTAL_NUMBER_CONTENTS = "SELECT COUNT(Id) AS max FROM Content";
        }
        else{
            SELECT_TOTAL_NUMBER_CONTENTS = "SELECT COUNT(Id) AS max FROM Content WHERE AuthorId = " + IdGlobal.UserId;
        }

        try(Connection connection = getConnection(); ){
            PreparedStatement prepareStatement ;
            prepareStatement = connection.prepareStatement(SELECT_TOTAL_NUMBER_CONTENTS);
            ResultSet rs1 = prepareStatement.executeQuery();
            int maxRow = 0;
            if (rs1.next()) { maxRow = rs1.getInt("max"); }

            //check max page min page
            if(Objects.equals(command, "Next")){
                if((IdGlobal.PageLIMIT < (maxRow/10)*10) && (maxRow % 10 != 0)){
                    IdGlobal.PageLIMIT+=10;
                }
            }else if(Objects.equals(command, "Previous")){
                if(IdGlobal.PageLIMIT > 0){
                    IdGlobal.PageLIMIT-=10;
                }
            }

            String SELECT_SOME_CONTENTS;
            if(Objects.equals(IdGlobal.Role, "Admin")){
                SELECT_SOME_CONTENTS = "SELECT Content.Id, Content.Title, Content.Brief, Content.Content, Member.Username, Content.CreateDate " +
                                        "FROM Content, Member  WHERE Content.AuthorId = Member.Id LIMIT " + IdGlobal.PageLIMIT + ", 10";
            }
            else{
                SELECT_SOME_CONTENTS = "SELECT * FROM Content WHERE AuthorId = " + IdGlobal.UserId + " LIMIT " + IdGlobal.PageLIMIT + ", 10";
            }

            System.out.println(SELECT_SOME_CONTENTS);
            prepareStatement = connection.prepareStatement(SELECT_SOME_CONTENTS);
            ResultSet rs2 = prepareStatement.executeQuery();
            if(Objects.equals(IdGlobal.Role, "Admin")){
                while (rs2.next()){
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String username = rs2.getString("Username");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, username, createdDate));
                }
            }else{
                while (rs2.next()){
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, createdDate));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return content;
    }
    //Select Content By ID

    //Delete Content
    public boolean deleteContent(int id) throws SQLException {
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CONTENTS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public ViewContent selectContent(int id){
        ViewContent viewcontent = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String title = rs.getString("Title");
                String brief = rs.getString("Brief");
                String content = rs.getString("Content");
                viewcontent = new ViewContent( title, brief, content, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viewcontent;
    }
    public boolean updateContent(ViewContent viewContent) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CONTENT_SQL);) {
            statement.setString(1, viewContent.getTitle());
            statement.setString(2, viewContent.getBrief());
            statement.setString(3, viewContent.getContent());
            statement.setString(4, viewContent.getUpdateTime());
            statement.setInt(5, viewContent.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<ViewContent> searchContents(String search, String command)  {
        List<ViewContent> content = new ArrayList<>();
        IdGlobal.searchValue = search;

        try(Connection connection = getConnection(); ){
            PreparedStatement prepareStatement ;

            if(Objects.equals(IdGlobal.Role, "Admin")){
                prepareStatement = connection.prepareStatement(SEARCH_TOTAL_NUMBER_CONTENTS_ADMIN);
            }
            else{
                prepareStatement = connection.prepareStatement(SEARCH_TOTAL_NUMBER_CONTENTS_MEMBER);
            }

            prepareStatement.setString(1, search);
            prepareStatement.setString(2, search);
            prepareStatement.setString(3, search);
            prepareStatement.setString(4, search);
            prepareStatement.setString(5, search);
            ResultSet rs1 = prepareStatement.executeQuery();
            int maxRow = 0;
            if (rs1.next()) { maxRow = rs1.getInt("max"); }

            //check max page min page
            if(Objects.equals(command, "Next")){
                if(IdGlobal.PageLIMIT < (maxRow/10)*10){
                    IdGlobal.PageLIMIT+=10;
                }
            }else if(Objects.equals(command, "Previous")){
                if(IdGlobal.PageLIMIT > 0){
                    IdGlobal.PageLIMIT-=10;
                }
            }

           if(Objects.equals(IdGlobal.Role, "Admin")){
                prepareStatement = connection.prepareStatement(SEARCH_CONTENTS_ADMIN);
            }
            else{
                prepareStatement = connection.prepareStatement(SEARCH_CONTENTS_MEMBER);
            }
            prepareStatement.setString(1, search);
            prepareStatement.setString(2, search);
            prepareStatement.setString(3, search);
            prepareStatement.setString(4, search);
            prepareStatement.setString(5, search);
            prepareStatement.setInt(6, IdGlobal.PageLIMIT);
            ResultSet rs2 = prepareStatement.executeQuery();

            if(Objects.equals(IdGlobal.Role, "Admin")){
                while (rs2.next()){
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String username = rs2.getString("Username");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, username, createdDate));
                }
            }else{
                while (rs2.next()){
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, createdDate));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return content;
    }
}