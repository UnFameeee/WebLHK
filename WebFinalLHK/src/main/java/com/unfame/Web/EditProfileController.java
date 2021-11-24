package com.unfame.Web;

import com.unfame.Global.UserIdGlobal;
import com.unfame.Model.EditProfile;
import com.unfame.DAO.EditProfileDAO;
import com.unfame.Model.ViewContent;
import com.unfame.Web.LoginController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/EditProfile")
public class EditProfileController extends HttpServlet {
    private EditProfileDAO editProfileDAO;

    public void init() {this.editProfileDAO = new EditProfileDAO();}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action){
            case "/showEditProfile":
                try {
                    getProfile(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/editProfile":
                try {
                    updateProfile(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void getProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        int id = Integer.parseInt(request.getParameter("IdUser"));

        EditProfile profile = editProfileDAO.getProfileById(UserIdGlobal.UserId);
        request.setAttribute("profile", profile);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Edit_Profile.tiles");
        dispatcher.forward(request,response);
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt((request.getParameter("Id")));
        String firstname = request.getParameter("Firstname");
        String lastname = request.getParameter("Lastname");
        String email = request.getParameter("Email");
        String phone = request.getParameter("Phone");
        String description = request.getParameter("Description");

        EditProfile profile = new EditProfile(id, firstname, lastname, email, phone, description);
        editProfileDAO.updateProfile(profile);
        response.sendRedirect("showEditProfile");
    }
}
