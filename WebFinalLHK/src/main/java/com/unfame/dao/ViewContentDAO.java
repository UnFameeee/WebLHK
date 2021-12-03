package com.unfame.dao;
import com.unfame.global.IdGlobal;
import com.unfame.model.ViewContent;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewContentDAO {

    private static final String SELECT_CONTENT_BY_ID = "SELECT Title, Brief, Content, CreateDate, UpdateTime, AuthorId FROM Content WHERE Id = ?";
    private static final String UPDATE_CONTENT_SQL = "UPDATE Content set Title = ?,Brief= ?, Content =? , UpdateTime =? where id = ?;";

    private static final String DELETE_CONTENTS_SQL = "DELETE FROM Content WHERE Id = ?";
    private static final String SELECT_COUNT_TOTAL_CONTENTS_ADMIN = "SELECT COUNT(Id) AS max FROM Content";
    private static final String SELECT_COUNT_TOTAL_CONTENTS_MEMBER = "SELECT COUNT(Id) AS max FROM Content WHERE AuthorId = " + IdGlobal.UserId;
    private static final String SELECT_SOME_CONTENTS_MEMBER = "SELECT * FROM Content WHERE AuthorId = " + IdGlobal.UserId + " LIMIT ?, ?";
    private static final String SELECT_SOME_CONTENTS_ADMIN = "SELECT Content.Id, Content.Title, Content.Brief, Content.Content, Member.Username, Content.CreateDate FROM Content, Member  WHERE Content.AuthorId = Member.Id LIMIT ?, ?";

    private static final String SEARCH_TOTAL_NUMBER_CONTENTS_ADMIN = "SELECT COUNT(Id) AS max FROM weblhk.content WHERE id LIKE '%' ? '%' OR title LIKE '%' ? '%' OR brief LIKE '%' ? '%' OR content LIKE '%' ? '%' OR createdate LIKE '%' ? '%'";
    private static final String SEARCH_TOTAL_NUMBER_CONTENTS_MEMBER = "SELECT COUNT(Id) AS max FROM weblhk.content WHERE id LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR title LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR brief LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR content LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR createdate LIKE '%' ? '%'AND AuthorId = " + IdGlobal.UserId;
    private static final String SEARCH_CONTENTS_ADMIN = "SELECT Content.Id, Content.Title, Content.Brief, Content.Content, Member.Username, Content.CreateDate FROM Content, Member  WHERE Content.AuthorId = Member.Id AND Content.id LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND title LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND brief LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND content LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND createdate LIKE '%' ? '%' OR Content.AuthorId = Member.Id AND Member.Username LIKE '%' ? '%' LIMIT ? , 10";
    private static final String SEARCH_CONTENTS_MEMBER = "SELECT * FROM weblhk.content WHERE id LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR title LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR brief LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR content LIKE '%' ? '%' AND AuthorId = " + IdGlobal.UserId + " OR createdate LIKE '%' ? '%'AND AuthorId = " + IdGlobal.UserId + " LIMIT ? , 10";;
    
    public ViewContentDAO() {}

    //Select Content (Admin/Member)
    public List<ViewContent> selectAllContents(String command)  {
        List<ViewContent> content = new ArrayList<>();
        PreparedStatement prepareStatement;
        //Step 1: Connection

        try(Connection connection = DAL.getConnection(); ){
            //If the role is admin (show All) / member (show Member)
            if(Objects.equals(IdGlobal.Role, "Admin")){ prepareStatement = connection.prepareStatement(SELECT_COUNT_TOTAL_CONTENTS_ADMIN); }
            else{ prepareStatement = connection.prepareStatement(SELECT_COUNT_TOTAL_CONTENTS_MEMBER); }

            ResultSet rs1 = prepareStatement.executeQuery();
            int maxRow = 0;
            if (rs1.next()){ maxRow = rs1.getInt("max"); }

            //check max page min page
            //Nếu content < 10 - MẶC ĐỊNH PageEnd = 10, sẽ có 1 số trường hợp đặt biệt khi những content bị lẻ thì PageEnd != 10
            if(maxRow <= 10) {
                IdGlobal.PageStart = 0;
                IdGlobal.PageEnd = 10;
            }else{
                if(Objects.equals(command, "Next")){
                    //VD: tổng 22 - hiện đang ở trang 1: 12 10 -> trang 2: 2 10 -> trang 3: 0 2
                    //VD: tổng 20 - hiện đang ở trang 1: 10 10 -> trang 2: 0 10
                    if(IdGlobal.PageStart > 0){
                        IdGlobal.PageEnd = 10;
                        IdGlobal.PageStart -= 10;
                        //Nếu PageStart bị âm thì gán về 0
                        if(IdGlobal.PageStart < 0){
                            IdGlobal.PageStart = 0;
                            IdGlobal.PageEnd = maxRow % 10;
                        }
                        IdGlobal.PageNumber++;
                    }
                }else if(Objects.equals(command, "Previous")){
                    //VD: tổng 22 - hiện đang ở trang 3: 0 2 -> trang 2: 2 10 -> trang 1: 12 10
                    //VD: tổng 20 - hiện đang ở trang 2: 0 10 -> trang 1: 10 10
                    if(IdGlobal.PageEnd < maxRow){
                        IdGlobal.PageEnd = 10;
                        //Nếu đang = 0 thì phải lấy phần content bị lẻ trước
                        if(IdGlobal.PageStart == 0 && maxRow % 10 != 0){
                            IdGlobal.PageStart = maxRow % 10;
                        }else{
                            IdGlobal.PageStart += 10;
                        }
                        IdGlobal.PageNumber--;
                    }
                }else if(Objects.equals(command, "Delete")){
                    //Sẽ chuyển trang trong 1 trường hợp xóa là Start = 0 và End = 1 (vì lúc này lẻ 1 content, xóa đi sẽ không lẻ nữa)
                    if( IdGlobal.PageStart == 0 && IdGlobal.PageEnd == 1 ){
                        IdGlobal.PageEnd=10;
                        IdGlobal.PageNumber--;
                    }
                    //1 trường hợp xóa là Start = 0 và End != 1 (vì lúc này lẻ hơn 1 content, xóa đi thì số lẻ sẽ giảm đi)
                    else if(IdGlobal.PageStart == 0){
                        IdGlobal.PageEnd--;
                    }
                    //Trường hợp khi vẫn chưa ở trang cuối cùng, giảm phần lẻ ở PageStart
                    else{
                        IdGlobal.PageStart--;
                    }
                }
                //Nếu content > 10 (mặc định lấy những content ở cuối - mới nhất)
                else{
                    IdGlobal.PageEnd = 10;
                    IdGlobal.PageStart = maxRow - 10;
                    IdGlobal.PageNumber = 1;
                }
            }

            ResultSet rs2;
            if(Objects.equals(IdGlobal.Role, "Admin")){
                prepareStatement = connection.prepareStatement(SELECT_SOME_CONTENTS_ADMIN);
                prepareStatement.setInt(1, IdGlobal.PageStart);
                prepareStatement.setInt(2, IdGlobal.PageEnd);
                rs2 = prepareStatement.executeQuery();
                while (rs2.next()) {
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String username = rs2.getString("Username");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, username, createdDate));
                }
            }else{
                prepareStatement = connection.prepareStatement(SELECT_SOME_CONTENTS_MEMBER);
                prepareStatement.setInt(1, IdGlobal.PageStart);
                prepareStatement.setInt(2, IdGlobal.PageEnd);
                rs2 = prepareStatement.executeQuery();
                while (rs2.next()){
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, createdDate));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return content;
    }

    //Delete Content
    public void deleteContent(int id) throws SQLException {
        try(Connection connection = DAL.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CONTENTS_SQL);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ViewContent selectContent(int id){
        ViewContent viewcontent = null;
        try (Connection connection = DAL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String title = rs.getString("Title");
                String brief = rs.getString("Brief");
                String content = rs.getString("Content");
                viewcontent = new ViewContent( title, brief, content, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viewcontent;
    }
    public void updateContent(ViewContent viewContent) throws SQLException {
        try (Connection connection = DAL.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CONTENT_SQL);) {
            statement.setString(1, viewContent.getTitle());
            statement.setString(2, viewContent.getBrief());
            statement.setString(3, viewContent.getContent());
            statement.setString(4, viewContent.getUpdateTime());
            statement.setInt(5, viewContent.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<ViewContent> searchContents(String command)  {
        List<ViewContent> content = new ArrayList<>();

        try(Connection connection = DAL.getConnection(); ){
            PreparedStatement prepareStatement ;

            if(Objects.equals(IdGlobal.Role, "Admin")){
                prepareStatement = connection.prepareStatement(SEARCH_TOTAL_NUMBER_CONTENTS_ADMIN);
            }
            else{
                prepareStatement = connection.prepareStatement(SEARCH_TOTAL_NUMBER_CONTENTS_MEMBER);
            }

            prepareStatement.setString(1,  IdGlobal.searchValue);
            prepareStatement.setString(2,  IdGlobal.searchValue);
            prepareStatement.setString(3,  IdGlobal.searchValue);
            prepareStatement.setString(4,  IdGlobal.searchValue);
            prepareStatement.setString(5,  IdGlobal.searchValue);
            ResultSet rs1 = prepareStatement.executeQuery();
            int maxRow = 0;
            if (rs1.next()) { maxRow = rs1.getInt("max"); }

            //check max page min page
//            if(Objects.equals(command, "Next")){
//                if((IdGlobal.PageLIMIT < (maxRow/10)*10) && (maxRow % 10 != 0)){
//                    IdGlobal.PageLIMIT+=10;
//                }
//            }else if(Objects.equals(command, "Previous")){
//                if(IdGlobal.PageLIMIT > 0){
//                    IdGlobal.PageLIMIT-=10;
//                }
//            }else if(Objects.equals(command, "Delete") && (maxRow % 10 == 0)){
//                if(IdGlobal.PageLIMIT > 0){
//                    IdGlobal.PageLIMIT-=10;
//                }
//                return content;
//            }

           if(Objects.equals(IdGlobal.Role, "Admin")){
                prepareStatement = connection.prepareStatement(SEARCH_CONTENTS_ADMIN);
            }
            else{
                prepareStatement = connection.prepareStatement(SEARCH_CONTENTS_MEMBER);
            }
            prepareStatement.setString(1,  IdGlobal.searchValue);
            prepareStatement.setString(2,  IdGlobal.searchValue);
            prepareStatement.setString(3,  IdGlobal.searchValue);
            prepareStatement.setString(4,  IdGlobal.searchValue);
            prepareStatement.setString(5,  IdGlobal.searchValue);
            prepareStatement.setInt(6, IdGlobal.PageStart);
            ResultSet rs2 = prepareStatement.executeQuery();

            if(Objects.equals(IdGlobal.Role, "Admin")){
                while (rs2.next()){
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String username = rs2.getString("Username");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, username, createdDate));
                }
            }else{
                while (rs2.next()){
                    int id = rs2.getInt("Id");
                    String title = rs2.getString("Title");
                    String brief = rs2.getString("Brief");
                    String createdDate = rs2.getString("CreateDate");
                    content.add(new ViewContent(id, title, brief, createdDate));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return content;
    }
}