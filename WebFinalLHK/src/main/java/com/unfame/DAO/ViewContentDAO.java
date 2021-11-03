package com.unfame.DAO;
import com.unfame.Model.ViewContent;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewContentDAO {
    String driver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String database = "WebLHK?useSSL=false";
    String userid = "root";
    String password = "root";


    private static final String INSERT_CONTENT_SQL = "INSERT INTO Content" + " (Title, Brief, Content, CreateDate, UpdateTime, AuthorId) VALUES" + " (?, ? ,? ,? ,? ,? ,?);";
    private static final String DELETE_CONTENTS_SQL = "DELETE FROM Content WHERE Id = ?";
    private static final String UPDATE_CONTENTS_SQL  = "UPDATE Content SET Title = ?, Brief = ?, Content = ?, CreateDate = ?, UpdateTime = ?, AuthorId = ? WHERE Id = ?";
    private static final String SELECT_ALL_CONTENTS = "SELECT * FROM Content";
    private static final String SELECT_CONTENT_BY_ID = "SELECT Title, Brief, Content, CreateDate, UpdateTime, AuthorId FROM Content WHERE AuthorId = ?";

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

    //Insert Content


    //Update Content
    public boolean updateContent(ViewContent content) throws SQLException {
        boolean rowUpdated;
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CONTENTS_SQL);) {
            statement.setString(1, content.getTitle());
            statement.setString(2, content.getBrief());
            statement.setString(3, content.getCreatedDate());
            statement.setInt(4, content.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    //Delete Content
    public boolean deleteContent(int id) throws SQLException {
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CONTENTS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
