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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


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
            case "/content":
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
        String command = "";
        IdGlobal.Reset();
        if(request.getParameter("next") != null) { command = request.getParameter("next"); }
        else if (request.getParameter("previous") != null){ command = request.getParameter("previous"); }

        List<ViewContent> listContent = viewContentDAO.selectAllContents(command);

        request.setAttribute("ListContent", listContent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");
        dispatcher.forward(request,response);
    }


    private void deleteContent (HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int Id = Integer.parseInt((request.getParameter("Id")));
        viewContentDAO.deleteContent(Id);
        viewContentDAO.selectAllContents("Delete");
//        response.sendRedirect("view");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view");
        dispatcher.forward(request,response);
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
    private void updateContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int id = Integer.parseInt((request.getParameter("Id")));
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");
        String updateTime = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        ViewContent existingContent = new ViewContent(title, brief, content, id,updateTime);
        viewContentDAO.updateContent(existingContent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view");
        dispatcher.forward(request,response);
    }

    private void searchContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String command = "";
        IdGlobal.Reset();
        IdGlobal.PageLIMIT = 0;
        IdGlobal.searchForm = true;
        if(request.getParameter("next") != null)
        {
            command = request.getParameter("next");
        }
        else if (request.getParameter("previous") != null){
            command = request.getParameter("previous");
        }
        if (request.getParameter("search") != null && !Objects.equals(request.getParameter("search"), "")) {
            IdGlobal.searchValue = request.getParameter("search");
            List<ViewContent> listContent = viewContentDAO.searchContents(command);
            request.setAttribute("ListContent", listContent);
        }
        else {
            IdGlobal.searchValue = "";
            List<ViewContent> listContent = new ArrayList<>();
            request.setAttribute("ListContent", listContent);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");
        dispatcher.forward(request,response);
    }
}
