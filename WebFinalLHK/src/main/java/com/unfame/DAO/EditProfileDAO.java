package com.unfame.DAO;

import com.unfame.Model.EditProfile;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditProfileDAO {
    String driver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String database = "WebLHK?useSSL=false";
    String userid = "root";
    String password = "root";

    private static final String GET_ID_BY_EMAIL = "SELECT Id from Member where Email = ?";
    private static final String SELECT_PROFILE_BY_ID = "SELECT Firstname, Lastname, Email, Phone, Description FROM Member WHERE Id = ?";
    private static final String UPDATE_PROFILE_BY_ID = "UPDATE Member SET Firstname = ?, Lastname = ?, Phone = ?, Description = ? WHERE Id = ?";

    public EditProfileDAO(){}

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

    private String CheckNullAndSet(String par) {
        if (par == null)
            return "";
        return par;
    }

    //Select profile by id
    public EditProfile getProfileById(int id)  {
        EditProfile profile = new EditProfile();
        try(Connection connection = getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(SELECT_PROFILE_BY_ID);){
            prepareStatement.setString(1, String.valueOf(id));
            ResultSet rs = prepareStatement.executeQuery();

            if (rs.next()){
                String firstName = CheckNullAndSet(rs.getString("Firstname"));
                String lastName = CheckNullAndSet(rs.getString("Lastname"));
                String email = CheckNullAndSet(rs.getString("Email"));
                String phone = CheckNullAndSet(rs.getString("Phone"));
                String description = CheckNullAndSet(rs.getString("Description"));

                profile = new EditProfile(id, firstName, lastName, email, phone, description);
            }
            else
                profile = new EditProfile(id, "", "", "", "", "");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return profile;
    }

    //Update profile by id
    public boolean updateProfile(EditProfile profile) throws SQLException {
        boolean updateCheck = false;
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PROFILE_BY_ID);) {
            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setString(3, profile.getPhoneNumber());
            statement.setString(4, profile.getDescription());
            statement.setInt(5, profile.getId());
            updateCheck = statement.executeUpdate() > 0;
        }
        return updateCheck;
    }
    
    //Select id by Email
    public EditProfile getIdbyEmail(String email) {
    	EditProfile profile = new EditProfile();
    	
    	try {
    		Connection connection = getConnection(); 
        	PreparedStatement prepareStatement = connection.prepareStatement(GET_ID_BY_EMAIL);
        	prepareStatement.setString(1, email);
        	ResultSet rs = prepareStatement.executeQuery();
        	
        	while (rs.next()){
                int id = Integer.parseInt(rs.getString(1));
                profile.setId(id);
            }
        	
    	}
	    catch (SQLException e){
	        e.printStackTrace();
	    }    	
    	
    	return profile;  	
    }
}
