package com.unfame.Web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unfame.DAO.LoginDAO;
import com.unfame.DAO.RegisterAccountDAO;
import com.unfame.DAO.ViewContentDAO;
import com.unfame.Global.UserIdGlobal;
import com.unfame.Model.Account;
import com.unfame.Model.ViewContent;


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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		this.doGet(request, response);
	}

	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();

		switch (action) {
			case "/register":
				try {
					showRegister(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/login":
				try {
					showLogin(request, response);
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

	private void loginAccount(HttpServletRequest request, HttpServletResponse response) throws
			ServletException, IOException, SQLException {
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
            
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view");

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			dispatcher.forward(request, response);
		} else {
			System.out.println("Wrong Email or password!!!");
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

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
		}
		else {
			System.out.println("Wrong");
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
	        
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
		dispatcher.forward(request, response);
	}
}
