package com.unfame.Web;

import com.unfame.DAO.ViewContentDAO;
import com.unfame.Model.ViewContent;

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
            default:
                break;
        }

    }

    private void listContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String command = "";
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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        dispatcher.forward(request,response);
    }

    private void deleteContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int Id = Integer.parseInt((request.getParameter("Id")));
        viewContentDAO.deleteContent(Id);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.sendRedirect("view");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        int Id = Integer.parseInt(request.getParameter("Id"));
        ViewContent existingContent = viewContentDAO.selectContent(Id);
        request.setAttribute("content",existingContent);
        request.setAttribute("Id",Id);
        RequestDispatcher dispatcher=request.getRequestDispatcher("Add_Content.tiles");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        dispatcher.forward(request,response);
    }
    private void updateContent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        System.out.println("update was here");

        int id = Integer.parseInt((request.getParameter("Id")));
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String content = request.getParameter("content");
        String updateTime = new SimpleDateFormat("dd:MM:yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        ViewContent existingContent = new ViewContent(title, brief, content, id,updateTime);
        viewContentDAO.updateContent(existingContent);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.sendRedirect("view");
    }
}
