package com.unfame.controller;

import com.unfame.dao.ViewContentDAO;
import com.unfame.global.IdGlobal;
import com.unfame.model.ViewContent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@WebServlet("/ViewContent")
public class ViewContentController extends HttpServlet {
    private ViewContentDAO viewContentDAO;

    public void init() {viewContentDAO = new ViewContentDAO();}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/delete":
                try {
                    deleteContent(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/showEdit":
                try {
                    showEditForm(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/update":
                try {
                    updateContent(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/search":
                try {
                    searchContent(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    listContent(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void listContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if(IdGlobal.UserId == -1){
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login_Page.tiles");
            dispatcher.forward(request,response);
        }
        else{
            String command = "";
            IdGlobal.searchValue = "";
            IdGlobal.searchForm = 0;
            if(request.getParameter("next") != null)
            {
                command = request.getParameter("next");
            }
            else if (request.getParameter("previous") != null){
                command = request.getParameter("previous");
            }

            List<ViewContent> listContent = viewContentDAO.selectAllContents(command);

            request.setAttribute("ListContent", listContent);
            RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");
            dispatcher.forward(request,response);
        }
    }

    private void deleteContent (HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        if(IdGlobal.UserId == -1){
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login_Page.tiles");
            dispatcher.forward(request,response);
        }
        else{
            int Id = Integer.parseInt((request.getParameter("Id")));
            viewContentDAO.deleteContent(Id);
            response.sendRedirect("view");
        }
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        int Id = Integer.parseInt(request.getParameter("Id"));
        ViewContent existingContent = viewContentDAO.selectContent(Id);
        request.setAttribute("content",existingContent);
        request.setAttribute("Id",Id);
        IdGlobal.searchValue = "";
        RequestDispatcher dispatcher=request.getRequestDispatcher("Add_Content.tiles");
        dispatcher.forward(request,response);
    }
    private void updateContent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        int id = Integer.parseInt((request.getParameter("Id")));
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");
        String updateTime = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        ViewContent existingContent = new ViewContent(title, brief, content, id,updateTime);
        viewContentDAO.updateContent(existingContent);
        response.sendRedirect("view");
    }

    private void searchContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String command = "";
        IdGlobal.searchForm = 1;
        if(request.getParameter("next") != null)
        {
            command = request.getParameter("next");
        }
        else if (request.getParameter("previous") != null){
            command = request.getParameter("previous");
        }
        if (request.getParameter("search") != null) {
            IdGlobal.searchValue = request.getParameter("search");
            List<ViewContent> listContent = viewContentDAO.searchContents(command);
            request.setAttribute("ListContent", listContent);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");
        dispatcher.forward(request,response);
    }
}
