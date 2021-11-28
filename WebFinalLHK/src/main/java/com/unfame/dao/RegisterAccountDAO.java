package com.unfame.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.unfame.model.Account;

public class RegisterAccountDAO {
	 
	 private static final String INSERT_ACCOUNT_SQL = "insert into Member(Role, Username, Password, Email, CreatedDate) values (?,?,?,?,now())";
	
	 private static final String CHECK_EXIST_ACC = "select * from Member where Email = ? or Username = ?";	  
	 
	 public void insertUser(Account acc) {
		try {
		
			Connection con = DAL.getConnection();
			PreparedStatement ppstm = con.prepareStatement(INSERT_ACCOUNT_SQL);
			ppstm.setString(1, "Member");
			ppstm.setString(2, acc.getUsername());
			ppstm.setString(3, acc.getPassword());
			ppstm.setString(4, acc.getEmail());
			ppstm.executeUpdate();
		} 
		catch (Exception e) {				
			e.printStackTrace();
		}
	}

	public boolean checkEmailExist(Account acc) {
		boolean check = false;
		
		try {
			Connection con = DAL.getConnection();
			
			PreparedStatement ppstm = con.prepareStatement(CHECK_EXIST_ACC);
			
			ppstm.setString(1, acc.getEmail());
			ppstm.setString(2, acc.getUsername());	
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
