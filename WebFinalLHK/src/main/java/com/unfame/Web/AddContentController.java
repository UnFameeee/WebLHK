package com.unfame.Web;

import com.unfame.DAO.AddContentDAO;
import com.unfame.Model.AddContent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/AddContent")
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
        String createDate = new SimpleDateFormat("dd:MM:yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println(createDate);

        AddContent addContent = new AddContent(title,brief,content,createDate);
        AddContentDAO addContentDAO = new AddContentDAO();
        addContentDAO.insertContent(addContent);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Add_Content.tiles");
        dispatcher.forward(request,response);
    }
}
