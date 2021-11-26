package com.unfame.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.unfame.model.Account;

public class RegisterAccountDAO {
	 String driver = "com.mysql.jdbc.Driver";
	 String connectionUrl = "jdbc:mysql://localhost:3306/";
	 String database = "WebLHK?useSSL=false";
	 String userid = "root";
	 String password = "root";
	 
	 private static final String INSERT_ACCOUNT_SQL = "insert into Member(Username, Password, Email) values (?,?,?)";
	
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
			ppstm.setString(2, acc.getPassword());
			ppstm.setString(3, acc.getEmail());
			ppstm.executeUpdate();
		} 
		catch (Exception e) {				
			e.printStackTrace();
		}
	}

//	public boolean checkAccountExist (Account acc){
//		boolean check = false;
//
//		try {
//			Connection con = getConnection();
//
//			PreparedStatement statement = con.prepareStatement(CHECK_ACC);
//			statement.setString(1, acc.getEmail());
//			statement.setString(2, acc.getPassword());
//			ResultSet rs = statement.executeQuery();
//			check = rs.next();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return check;
//	}

	public boolean checkEmailExist(Account acc) {
		boolean check = false;
		
		try {
			Connection con = getConnection();
			
			PreparedStatement ppstm = con.prepareStatement(CHECK_EXIST_ACC);
			
			ppstm.setString(1, acc.getEmail());
			ppstm.executeQuery();
			
			ResultSet rs = ppstm.executeQuery();	
			
			check = rs.next();			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return check;
	}
	 
}
