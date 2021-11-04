package com.unfame.Web;

import com.unfame.DAO.ViewContentDAO;
import com.unfame.Model.ViewContent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
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
            case "/view":
                try {
                    listContent(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    private void listContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<ViewContent> listContent = viewContentDAO.selectAllContents();
        request.setAttribute("ListContent", listContent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");
        dispatcher.forward(request,response);
    }

    private void deleteContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int Id = Integer.parseInt((request.getParameter("Id")));
        viewContentDAO.deleteContent(Id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");
//        dispatcher.forward(request,response);
        response.sendRedirect("view");
    }
}
