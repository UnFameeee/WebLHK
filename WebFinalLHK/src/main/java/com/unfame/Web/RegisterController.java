package com.unfame.Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unfame.DAO.RegisterAccountDAO;
import com.unfame.Model.Account;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get information from user
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");		
		
		Account account = new Account();
		RegisterAccountDAO registerDAO = new RegisterAccountDAO();
		
		//set information to account
		account.setUsername(username);
		account.setEmail(email);
		account.setPassword(password);
		
		//check email
		if(registerDAO.checkEmailExist(account)) {
			registerDAO.insertUser(account);
			response.sendRedirect(request.getContextPath()+"/View/login.jsp");
		}
		else {
			request.setAttribute("Message", "Email already is existed!!!");
			request.getRequestDispatcher("/View/register.jsp").forward(request, response);
		}	
	}

}
