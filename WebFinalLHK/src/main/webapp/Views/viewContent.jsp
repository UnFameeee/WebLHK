<%@ page import="com.unfame.model.ViewContent" %>
<%@ page import="java.util.List" %>
<%@ page import="com.unfame.global.IdGlobal" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
                        <% if(Objects.equals(IdGlobal.Role, "Admin")){ %>
                        <th class="username">Username</th>
                        <% } %>
                        <th class="created-date">Created Date</th>
                        <th class="actions">Actions</th>
                    </thead>
                    <tbody>
                    <% List<ViewContent> listContent = (List<ViewContent>) request.getAttribute("ListContent"); %>
                    <% for (int i = listContent.size() - 1; i >= 0; --i) {%>
                    <tr>
                        <td class="id"><%= listContent.get(i).getId() %></td>
                        <td class="title"><%= listContent.get(i).getTitle() %></td>
                        <td class="brief"><%= listContent.get(i).getBrief() %></td>
                        <% if(Objects.equals(IdGlobal.Role, "Admin")){ %>
                        <td class="username"><%= listContent.get(i).getUsername() %></td>
                        <% } %>
                        <td class="created-date"><%= listContent.get(i).getDate(listContent.get(i).getCreatedDate()) %> <br> <%= listContent.get(i).getTime(listContent.get(i).getCreatedDate()) %></td>
                        <td class="actions">
                            <div class="view-content-btn-container">
                                <form action="<%=request.getContextPath()%>/showEdit?Id=<%=listContent.get(i).getId()%>" method="post">
                                    <input type="submit" class="view-content-btn-edit" value="Edit">
                                </form>
                                <form action="<%=request.getContextPath()%>/delete?Id=<%=listContent.get(i).getId()%>" method="post">
                                    <input type="submit" class="view-content-btn-delete" value="Delete">
                                </form>

                            </div>
                        </td>
                    </tr>
                    <% }%>
                    </tbody>
                </table>
                <div class="btn-field">
                    <% if(IdGlobal.searchForm == false){ %>
                        <form action="<%=request.getContextPath()%>/view?<% request.getParameter("previous"); %>">
                            <input type="submit" name="previous" value="Previous">
                        </form>
                            <a><%= (IdGlobal.PageLIMIT/10 + 1) %></a>
                        <form action="<%=request.getContextPath()%>/view?<% request.getParameter("next"); %>">
                            <input type="submit" name="next" value="Next">
                        </form>
                    <% }%>
                    <%if(IdGlobal.searchForm == true) {%>
                        <form action="<%=request.getContextPath()%>/search?<% request.getParameter("previous"); %>">
                            <input type="hidden" class="search-input" name="search" value="<%=IdGlobal.searchValue%>">
                            <input type="submit" name="previous" value="Previous" id="search-previous">
                        </form>
                          <a><%= (IdGlobal.PageLIMIT/10 + 1) %></a>
                        <form action="<%=request.getContextPath()%>/search?<% request.getParameter("next"); %>">
                            <input type="hidden" class="search-input" name="search" value="<%=IdGlobal.searchValue%>">
                            <input type="submit" name="next" value="Next" id="search-next">
                        </form>
                    <% }%>
                </div>
            </div>
        </div>
        <!-- Content end -->

        <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/ViewContent.js"></script>
    </body>
</html>