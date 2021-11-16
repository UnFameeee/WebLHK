package com.unfame.Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unfame.DAO.LoginDAO;
import com.unfame.Model.Account;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get account
		String email = request.getParameter("email");
		String password = request.getParameter("password");		
				
		Account account = new Account();
		LoginDAO loginDAO = new LoginDAO();
				
		//set information to account
		account.setEmail(email);
		account.setPassword(password);
				
		//check account
		if(loginDAO.checkAccount(account)) {
			
		}
		else {
			request.setAttribute("Message", "Email or password doesn't exist!!!");
			request.getRequestDispatcher("/View/login.jsp").forward(request, response);
		}	
	}

}
