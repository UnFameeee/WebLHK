package com.unfame.Web;

import com.unfame.DAO.AddContentDAO;
import com.unfame.Model.AddContent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/test")
public class AddContentController extends HttpServlet {

    private AddContentDAO addContentDAO;

    public void init() {addContentDAO = new AddContentDAO();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {

            case "/addContent":
                try {
                    addContent(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;

        }

    }

    private void addContent(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException{

        String title =request.getParameter("title");
        String brief =request.getParameter("brief");
        String content =request.getParameter("content");
        AddContent addContent = new AddContent(8,title,brief,content);
        AddContentDAO addContentDAO = new AddContentDAO();
        addContentDAO.insertContent(addContent);

        response.sendRedirect("ViewContent.tiles");
    }
}
