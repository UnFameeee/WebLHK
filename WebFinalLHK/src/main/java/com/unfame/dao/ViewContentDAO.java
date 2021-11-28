package com.unfame.dao;
import com.unfame.global.IdGlobal;
import com.unfame.model.ViewContent;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewContentDAO {

    private static final String DELETE_CONTENTS_SQL = "DELETE FROM Content WHERE Id = ?";

    private static final String SELECT_CONTENT_BY_ID = "SELECT Title, Brief, Content, CreateDate, UpdateTime, AuthorId FROM Content WHERE Id = ?";
    private static final String UPDATE_CONTENT_SQL = "UPDATE Content set Title = ?,Brief= ?, Content =? , UpdateTime =? where id = ?;";

    private static final String SELECT_COUNT_TOTAL_CONTENTS_ADMIN = "SELECT COUNT(Id) AS max FROM Content";
    private static final String SELECT_COUNT_TOTAL_CONTENTS_MEMBER = "SELECT COUNT(Id) AS max FROM Content WHERE AuthorId = " + IdGlobal.UserId;

    private static final String SEARCH_TOTAL_NUMBER_CONTENTS_ADMIN = "SELECT COUNT(Id) AS max FROM weblhk.content WHERE id LIKE '%' ? '%' OR title LIKE '%' ? '%' OR brief LIKE '%' ? '%' OR content LIKE '%' ? '%' OR createdate LIKE '%' ? '%'";
    private static final String SEARCH_TOTAL_NUMBER_CONTENTS_MEMBER = "SELECT COUNT(Id) AS max FROM weblhk.content WHERE id LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR title LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR brief LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR content LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR createdate LIKE '%' ? '%'AND AuthorId = " + IdGlobal.UserId;
    private static final String SEARCH_CONTENTS_ADMIN = "SELECT Content.Id, Content.Title, Content.Brief, Content.Content, Member.Username, Content.CreateDate FROM Content, Member  WHERE Content.AuthorId = Member.Id AND Content.id LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND title LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND brief LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND content LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND createdate LIKE '%' ? '%'LIMIT ? , 10";
    private static final String SEARCH_CONTENTS_MEMBER = "SELECT * FROM weblhk.content WHERE id LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR title LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR brief LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR content LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR createdate LIKE '%' ? '%'AND AuthorId = " + IdGlobal.UserId + " LIMIT ? , 10";;
    
    public ViewContentDAO() {}

    //Select Content (Admin/Member)
    public List<ViewContent> selectAllContents(String command)  {
        List<ViewContent> content = new ArrayList<>();
        PreparedStatement prepareStatement;
        //Step 1: Connection

        try(Connection connection = DAL.getConnection(); ){
            //If the role is admin (show All) / member (show Member)
            if(Objects.equals(IdGlobal.Role, "Admin")){ prepareStatement = connection.prepareStatement(SELECT_COUNT_TOTAL_CONTENTS_ADMIN); }
            else{ prepareStatement = connection.prepareStatement(SELECT_COUNT_TOTAL_CONTENTS_MEMBER); }

            ResultSet rs1 = prepareStatement.executeQuery();
            int maxRow = 0;
            if (rs1.next()){ maxRow = rs1.getInt("max"); }


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


            String SELECT_SOME_CONTENTS_MEMBER = "SELECT * FROM Content WHERE AuthorId = " + IdGlobal.UserId + " LIMIT " + IdGlobal.PageLIMIT + ", 10";
            String SELECT_SOME_CONTENTS_ADMIN = "SELECT Content.Id, Content.Title, Content.Brief, Content.Content, Member.Username, Content.CreateDate FROM Content, Member  WHERE Content.AuthorId = Member.Id LIMIT " + IdGlobal.PageLIMIT + ", 10";
            ResultSet rs2;
            if(Objects.equals(IdGlobal.Role, "Admin")){
                prepareStatement = connection.prepareStatement(SELECT_SOME_CONTENTS_ADMIN);
                rs2 = prepareStatement.executeQuery();
                while (rs2.next()) {
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String username = rs2.getString("Username");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, username, createdDate));
                }
            }else{
                prepareStatement = connection.prepareStatement(SELECT_SOME_CONTENTS_MEMBER);
                rs2 = prepareStatement.executeQuery();
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

    //Delete Content
    public void deleteContent(int id) throws SQLException {
        try(Connection connection = DAL.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CONTENTS_SQL);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ViewContent selectContent(int id){
        ViewContent viewcontent = null;
        try (Connection connection = DAL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
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
    public void updateContent(ViewContent viewContent) throws SQLException {
        try (Connection connection = DAL.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CONTENT_SQL);) {
            statement.setString(1, viewContent.getTitle());
            statement.setString(2, viewContent.getBrief());
            statement.setString(3, viewContent.getContent());
            statement.setString(4, viewContent.getUpdateTime());
            statement.setInt(5, viewContent.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<ViewContent> searchContents(String command)  {
        List<ViewContent> content = new ArrayList<>();

        try(Connection connection = DAL.getConnection(); ){
            PreparedStatement prepareStatement ;

            if(Objects.equals(IdGlobal.Role, "Admin")){
                prepareStatement = connection.prepareStatement(SEARCH_TOTAL_NUMBER_CONTENTS_ADMIN);
            }
            else{
                prepareStatement = connection.prepareStatement(SEARCH_TOTAL_NUMBER_CONTENTS_MEMBER);
            }

            prepareStatement.setString(1,  IdGlobal.searchValue);
            prepareStatement.setString(2,  IdGlobal.searchValue);
            prepareStatement.setString(3,  IdGlobal.searchValue);
            prepareStatement.setString(4,  IdGlobal.searchValue);
            prepareStatement.setString(5,  IdGlobal.searchValue);
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
            prepareStatement.setString(1,  IdGlobal.searchValue);
            prepareStatement.setString(2,  IdGlobal.searchValue);
            prepareStatement.setString(3,  IdGlobal.searchValue);
            prepareStatement.setString(4,  IdGlobal.searchValue);
            prepareStatement.setString(5,  IdGlobal.searchValue);
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