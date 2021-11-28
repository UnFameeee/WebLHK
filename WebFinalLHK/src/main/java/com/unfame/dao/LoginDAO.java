package com.unfame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.unfame.config.Encode;
import com.unfame.global.IdGlobal;
import com.unfame.model.Account;

public class LoginDAO {
	 
	 private static final String CHECK_ACC = "select * from member where Email = ? and Password = ?";

	 public boolean checkAccount (Account acc) {
		 boolean check = false;
		 
		 String pass = Encode.getMd5(acc.getPassword());
		 
			 try {
				  Connection con = DAL.getConnection();

				  PreparedStatement statement = con.prepareStatement(CHECK_ACC);
				  statement.setString(1, acc.getEmail());
				  statement.setString(2, pass);
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
