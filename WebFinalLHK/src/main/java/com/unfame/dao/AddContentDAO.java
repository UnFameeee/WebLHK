package com.unfame.dao;

import com.unfame.global.IdGlobal;
import com.unfame.model.AddContent;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AddContentDAO {

    private static final String INSERT_CONTENT_SQL = "INSERT INTO Content" + " ( Title, Brief, Content, CreateDate, AuthorId) VALUES" + " (?, ?, ?, ?, ?);";
    //private static final String INSERT_CONTENT_SQL = "INSERT INTO Content" + " (Title, Brief, Content, CreateDate, UpdateTime, AuthorId) VALUES" + " (?, ? ,? ,? ,? ,?);";
    private static final String DELETE_CONTENTS_SQL = "DELETE FROM Content WHERE Id = ?";
    private static final String UPDATE_CONTENTS_SQL  = "UPDATE Content SET Title = ?, Brief = ?, Content = ?, CreateDate = ?, UpdateTime = ?, AuthorId = ? WHERE Id = ?";
    private static final String SELECT_ALL_CONTENTS = "SELECT * FROM Content";
    private static final String SELECT_CONTENT_BY_ID = "SELECT Title, Brief, Content, CreateDate, UpdateTime, AuthorId FROM Content WHERE AuthorId = ?";

    public AddContentDAO(){}
    
    public void insertContent(AddContent content) throws SQLException {
        System.out.println(INSERT_CONTENT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DAL.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTENT_SQL)) {
            preparedStatement.setString(1, content.getTitle());
            preparedStatement.setString(2, content.getBrief());
            preparedStatement.setString(3, content.getContent());
            preparedStatement.setString(4, content.getCreatedDate());
            preparedStatement.setInt(5,  IdGlobal.UserId);
//            preparedStatement.setString(5, content.getUpdateTime());
//            preparedStatement.setString(6, content.getAuthorId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
