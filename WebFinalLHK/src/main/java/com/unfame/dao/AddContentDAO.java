package com.unfame.dao;

import com.unfame.global.IdGlobal;
import com.unfame.model.AddContent;

import java.sql.*;
import java.sql.Connection;

public class AddContentDAO {

    private static final String INSERT_CONTENT_SQL = "INSERT INTO Content" + " ( Title, Brief, Content, CreateDate, AuthorId) VALUES" + " (?, ?, ?, ?, ?);";
    //private static final String INSERT_CONTENT_SQL = "INSERT INTO Content" + " (Title, Brief, Content, CreateDate, UpdateTime, AuthorId) VALUES" + " (?, ? ,? ,? ,? ,?);";

    public AddContentDAO(){}
    
    public void insertContent(AddContent content) throws SQLException {
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DAL.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTENT_SQL)) {
            preparedStatement.setString(1, content.getTitle());
            preparedStatement.setString(2, content.getBrief());
            preparedStatement.setString(3, content.getContent());
            preparedStatement.setString(4, content.getCreatedDate());
            preparedStatement.setInt(5,  IdGlobal.UserId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
