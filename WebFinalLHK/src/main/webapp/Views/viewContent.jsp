<%@ page import="com.unfame.Model.ViewContent" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <div id="loading"><p>Loading...</p></div>
        <!-- Content start -->
        <div class="view-content">
            <div class="title">
                <h1>View Content</h1>
                <hr>
            </div>

            <div class="view-content-list-title">
                <p>View Content List</p>
            </div>

            <div class="view-content-list-container">
                <table id="View-content-list" class="table" border="1">
                    <thead>
                        <th class="id">#</th>
                        <th class="title">Title</th>
                        <th class="brief">Brief</th>
                        <th class="created-date">Created Date</th>
                        <th class="actions">Actions</th>
                    </thead>
                    <tbody>
                    <% List<ViewContent> listContent = (List<ViewContent>) request.getAttribute("ListContent"); %>
                    <% for (int i =0; i < listContent.size(); ++i) {%>
                    <tr>
                        <td><%= listContent.get(i).getId() %></td>
                        <td><%= listContent.get(i).getTitle() %></td>
                        <td><%= listContent.get(i).getBrief() %></td>
<%--                        <td><%= listContent.get(i).getCreatedDate() %></td>--%>
                        <td><%= listContent.get(i).getDate(listContent.get(i).getCreatedDate()) %> <br> <%= listContent.get(i).getTime(listContent.get(i).getCreatedDate()) %></td>
                        <td>
                            <div class="view-content-btn-container">
                                <form action="<%=request.getContextPath()%>/showEdit?Id=<%=listContent.get(i).getId()%>" method="post">
                                    <input type="submit" class="view-content-btn-edit" value="Edit">
                                </form>
                                <form action="<%=request.getContextPath()%>/delete?Id=<%=listContent.get(i).getId()%>" method="post">
                                    <input type="submit" class="view-content-btn-delete" value="Delete">
                                </form>
                            </div>
                        </td>
                        <%-- td><%= new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lstContentsByPage.get(i).getCreateDate()) %></td>--%>
                        <%-- <td>--%>
                        <%--        <button class = "bg-success px-3"><a class = "text-decoration-none text-light" href = "editContent?id=<%=lstContentsByPage.get(i).getId()%>">Edit</a></button>--%>
                        <%--        <button class = "bg-danger"><a class = "text-decoration-none text-light" href = "deleteContent?id=<%=lstContentsByPage.get(i).getId()%>">Delete</a></button>--%>
                        <%-- </td>--%>
                    </tr>
                    <% }%>
                    </tbody>
                </table>
                <div class="btn-field">
                    <input type="submit" value="Previous">
                    <a href="">1</a>
                    <input type="submit" value="Next">
                </div>
            </div>
        </div>
        <!-- Content end -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/ViewContent.js"></script>

    </body>
</html>