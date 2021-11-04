package com.unfame.Web;

import com.unfame.DAO.ViewContentDAO;
import com.unfame.Model.ViewContent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/test")
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
                deleteContent(request, response);
                break;
            case "/edit":
                showEditContentForm(request, response);
                break;
            case "/update":
                updateContent(request, response);
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

//          case "/new":
//               showNewForm(request, response);
//               break;
//          case "/insert":
//               insertContent(request, response);
//               break;
        }

    }

    private void listContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<ViewContent> listContent = viewContentDAO.selectAllContents();
        request.setAttribute("ListContent", listContent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("View_Content.tiles");
        dispatcher.forward(request,response);
    }

    private void deleteContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewContent.tiles");
        dispatcher.forward(request,response);
    }

    private void updateContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewContent.tiles");
        dispatcher.forward(request,response);
    }

    private void showEditContentForm (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewContent.tiles");
        dispatcher.forward(request,response);
    }
}
