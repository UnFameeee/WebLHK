package com.unfame.DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unfame.Model.Account;

public class RegisterAccountDAO {
	 String driver = "com.mysql.jdbc.Driver";
	 String connectionUrl = "jdbc:mysql://localhost:3306/";
	 String database = "WebLHK?useSSL=false";
	 String userid = "root";
	 String password = "root";
	 
	 private static final String INSERT_ACCOUNT_SQL = "insert into Member(Username, Email, Password) values (?,?,?)";
	 private static final String CHECK_EXIST_ACC = "select * from Member where Email = ?";
	 
	 protected Connection getConnection(){
		 Connection connection = null;
	     try{
	    	 Class.forName(driver);
	         connection = DriverManager.getConnection(connectionUrl + database, userid, password);

	     } 
	     catch (ClassNotFoundException | SQLException e){
	         e.printStackTrace();
	     }
	     
	     return connection;
	 }
	 
	 
	 public void insertUser(Account acc) {
		try {
			Connection con = getConnection();
			PreparedStatement ppstm = con.prepareStatement(INSERT_ACCOUNT_SQL);
			ppstm.setString(1, acc.getUsername());
			ppstm.setString(2, acc.getEmail());
			ppstm.setString(3, acc.getPassword());
			ppstm.executeUpdate();
		} 
		catch (Exception e) {				
			e.printStackTrace();
		}
	}	 
	 
	public boolean checkEmailExist(Account acc) {
		boolean check = false;
		
		try {
			Connection con = getConnection();
			PreparedStatement ppstm = con.prepareStatement(CHECK_EXIST_ACC);
			ppstm.setString(1, acc.getEmail());
			ppstm.executeUpdate();
			check = ppstm.executeUpdate() > 0;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return check;
	}
	 
}
