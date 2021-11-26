package com.unfame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unfame.global.IdGlobal;
import com.unfame.model.Account;

public class LoginDAO {
	 String driver = "com.mysql.jdbc.Driver";
	 String connectionUrl = "jdbc:mysql://localhost:3306/";
	 String database = "WebLHK?useSSL=false";
	 String userid = "root";
     String password = "root";
	 
	 private static final String CHECK_ACC = "select * from member where Email = ? and Password = ?";
	 
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

	 public boolean checkAccount (Account acc){
		 boolean check = false;
		 
			 try {
				  Connection con = getConnection();

				  PreparedStatement statement = con.prepareStatement(CHECK_ACC);
				  statement.setString(1, acc.getEmail());
				  statement.setString(2, acc.getPassword());
				  statement.executeQuery();
				  
				  ResultSet rs = statement.executeQuery();
				  check = rs.next();
				  IdGlobal.UserId = rs.getInt("Id");
				  IdGlobal.Role = rs.getString("Role");
			 }
			 catch (Exception e) {
				 e.printStackTrace();
			 }
			 
		 return check;
	 }
}
