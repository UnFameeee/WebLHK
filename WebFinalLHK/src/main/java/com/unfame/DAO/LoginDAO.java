package com.unfame.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unfame.Global.UserIdGlobal;
import com.unfame.Model.Account;

public class LoginDAO {
	 String driver = "com.mysql.jdbc.Driver";
	 String connectionUrl = "jdbc:mysql://localhost:3306/";
	 String database = "WebLHK?useSSL=false";
	 String userid = "root";
     //String password = "root";
	 String password = "123456";
	 
	 private static final String CHECK_ACC = "select * from Member where Email = ? and Password = ?";
	 
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
				  ResultSet rs = statement.executeQuery();
				  UserIdGlobal.UserId = Integer.parseInt(rs.getString(1));
				  check = rs.next();
			 }
			 catch (Exception e) {
				 e.printStackTrace();
			 }
		 return check;
	 }
}
