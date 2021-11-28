package com.unfame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unfame.global.IdGlobal;
import com.unfame.model.Account;

public class LoginDAO {
	 
	 private static final String CHECK_ACC = "select * from member where Email = ? and Password = ?";

	 public boolean checkAccount (Account acc){
		 boolean check = false;
		 
			 try {
				  Connection con = DAL.getConnection();

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
