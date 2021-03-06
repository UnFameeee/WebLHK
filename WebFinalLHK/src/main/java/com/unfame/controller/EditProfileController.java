package com.unfame.controller;

import com.unfame.global.IdGlobal;
import com.unfame.model.EditProfile;
import com.unfame.dao.EditProfileDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

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
            case "/editProfile":
                try {
                    updateProfile(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    getProfile(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void getProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        EditProfile profile = editProfileDAO.getProfileById(IdGlobal.UserId);
        request.setAttribute("profile", profile);

        IdGlobal.searchValue = "";
        IdGlobal.searchForm = false;

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
        if (editProfileDAO.updateProfile(profile))
            IdGlobal.alertSuccess = "Edit Success";
        else
            IdGlobal.alertSuccess = "Edit Fail";
        response.sendRedirect("profile");
    }
}
