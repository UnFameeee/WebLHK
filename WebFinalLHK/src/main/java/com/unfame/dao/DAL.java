package com.unfame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAL {
	public static String driver = "com.mysql.jdbc.Driver";
	public static String connectionUrl = "jdbc:mysql://localhost:3306/";
	public static String database = "WebLHK?useSSL=false";
	public static String userid = "root";
	public static String password = "root";
     
     public static Connection getConnection(){
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
}
