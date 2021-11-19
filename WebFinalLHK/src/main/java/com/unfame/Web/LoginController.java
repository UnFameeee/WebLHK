package com.unfame.Web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unfame.DAO.LoginDAO;
import com.unfame.DAO.ViewContentDAO;
import com.unfame.Model.Account;
import com.unfame.Model.ViewContent;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private LoginDAO loginDAO;

	public void init() {loginDAO = new LoginDAO();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		this.doGet(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getServletPath();

		switch (action) {
			case "/login":
				try {
					login(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
		}
		//get account
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Account account = new Account();
		LoginDAO loginDAO = new LoginDAO();

		//set information to account
		account.setEmail(email);
		account.setPassword(password);

		//check account
		if (loginDAO.checkAccount(account)) {

		} else {
			request.setAttribute("Message", "Email or password doesn't exist!!!");
			request.getRequestDispatcher("/View/login.jsp").forward(request, response);
		}

	}

	private void login (HttpServletRequest request, HttpServletResponse response) throws
			ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		Account account = new Account(email, pass);

		if(loginDAO.checkAccount(account)){

		}else{

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		dispatcher.forward(request, response);
	}
}
