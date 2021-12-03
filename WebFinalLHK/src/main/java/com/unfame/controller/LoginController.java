package com.unfame.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unfame.dao.LoginDAO;
import com.unfame.dao.RegisterAccountDAO;
import com.unfame.global.IdGlobal;
import com.unfame.model.Account;


@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private LoginDAO loginDAO;
	private RegisterAccountDAO registerDAO;

	public void init() {
		loginDAO = new LoginDAO();
		registerDAO = new RegisterAccountDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
			case "/register":
				try {
					showRegister(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/registerAccount":
				try {
					registerAccount(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/loginAccount":
				try {
					loginAccount(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/logoutAccount":
				try {
					logoutAccount(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			default:
				try {
					showLogin(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
		}
	}

	private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Login_Page.tiles");
		dispatcher.forward(request, response);
	}

	private void showRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("Register_Page.tiles");
		dispatcher.forward(request, response);

	}

	private void loginAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		Account account = new Account(pass, email);
		
		String remember = request.getParameter("remember_me");

		if (loginDAO.checkAccount(account)) {
			//Tạo cookie
			if(remember != null) {
				Cookie c = new Cookie("check", "OK");
	            c.setMaxAge(3600);
	            response.addCookie(c);
			}

			response.sendRedirect(request.getContextPath() + "/view");
		} 
		else {
			request.setAttribute("Message", "alert('Email or password incorrect!!!');");
			request.getRequestDispatcher("/login").forward(request, response);	
		}

	}

	private void registerAccount (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		Account account = new Account(username, pass, email);

		//check email
		if(!registerDAO.checkEmailExist(account)) {
			registerDAO.insertUser(account);
			response.sendRedirect(request.getContextPath() + "/login");
		}
		else {
			request.setAttribute("Message", "alert('Username or email is already existed!!!');");
			request.getRequestDispatcher("/register").forward(request, response);
		}
	}
	
	private void logoutAccount (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Cookie[] cookies = request.getCookies();
	        
	    for(Cookie c: cookies){
	    	if(c.getName().equals("check")){    			
	    		c.setMaxAge(0);  
	    		response.addCookie(c);
	    	}    			
	    }

		IdGlobal.PageNumber = 0;
	    IdGlobal.UserId = -1;
		IdGlobal.Role = "";
		IdGlobal.Reset();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
		dispatcher.forward(request, response);
	}
}
