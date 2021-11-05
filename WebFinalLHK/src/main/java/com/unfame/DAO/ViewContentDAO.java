package com.unfame.DAO;
import com.unfame.Model.ViewContent;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewContentDAO {
    String driver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String database = "WebLHK?useSSL=false";
    String userid = "root";
    String password = "root";


//    private static final String INSERT_CONTENT_SQL = "INSERT INTO Content" + " (Title, Brief, Content, CreateDate, UpdateTime, AuthorId) VALUES" + " (?, ? ,? ,? ,? ,? ,?);";
    private static final String DELETE_CONTENTS_SQL = "DELETE FROM Content WHERE Id = ?";
    private static final String SELECT_ALL_CONTENTS = "SELECT * FROM Content";
    private static final String SELECT_CONTENT_BY_ID = "SELECT Title, Brief, Content, CreateDate, UpdateTime, AuthorId FROM Content WHERE Id = ?";
    private static final String UPDATE_CONTENT_SQL = "UPDATE Content set Title = ?,Brief= ?, Content =? , UpdateTime =? where id = ?;";

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
    public List<ViewContent> selectAllContents()  {
        List<ViewContent> content = new ArrayList<>();
        //Step 1: Connection
        try(Connection connection = getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_CONTENTS);){
            System.out.println(prepareStatement);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String brief = rs.getString("Brief");
                String createdDate = rs.getString("CreateDate");
                content.add(new ViewContent(id, title, brief, createdDate));
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
}
