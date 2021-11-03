<%@ page import="com.unfame.Model.ViewContent" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
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
                        <th class="#">#</th>
                        <th class="title">Title</th>
                        <th class="brief">Brief</th>
                        <th class="created-date">Created Date</th>
                        <th class="actions">Actions</th>
                    </thead>
                    <tbody>
                    <%
                        List<ViewContent> listContent = (List<ViewContent>) request.getAttribute("ListContent");
                        for(int i = 0; i < 2; i++){
                            System.out.println(listContent.get(i).getTitle());
                        }
                    %>


                    <% for (int i =0; i<listContent.size(); ++i) {%>
                        <tr>
                            <td><%= listContent.get(i).getId() %></td>
                            <td><%= listContent.get(i).getTitle() %></td>
                            <td><%= listContent.get(i).getBrief() %></td>
                            <td><%= listContent.get(i).getCreatedDate() %></td>
                            <td><%= listContent.get(i).getCreatedDate() %></td>
<%--                            <td><%= new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lstContentsByPage.get(i).getCreateDate()) %></td>--%>
<%--                            <td>--%>
<%--                                <button class = "bg-success px-3"><a class = "text-decoration-none text-light" href = "editContent?id=<%=lstContentsByPage.get(i).getId()%>">Edit</a></button>--%>
<%--                                <button class = "bg-danger"><a class = "text-decoration-none text-light" href = "deleteContent?id=<%=lstContentsByPage.get(i).getId()%>">Delete</a></button>--%>
<%--                            </td>--%>
                        </tr>
                    <% }%>

<%--                    <c:forEach items= "${listContent}" var="viewContent">--%>
<%--                        <tr>--%>
<%--                            <td><c:out value="${viewContent.getId()}"/></td>--%>
<%--                            <td><c:out value="${viewContent.getTitle()}"/></td>--%>
<%--                            <td><c:out value="${viewContent.getBrief()}"/></td>--%>
<%--                            <td><c:out value="${viewContent.getCreateDate()}"/></td>--%>
<%--                            <td>--%>
<%--                                <a href="edit?Id= <c:out value='${viewContent.Id}' />">Edit</a>--%>
<%--                                <a href="delete?Id= <c:out value='${viewContent.Id}' />">Delete</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Content end -->

        <div id="loading"><p>Loading...</p></div>

        <script>
            const viewContent = document.querySelector(".view-content");
            const loading = document.querySelector("#loading");

            window.addEventListener('load', function(){
                console.log("Load complete");
                showPopup();
            })

            function showPopup(){
                const timeLimit = 5;
                let i = 0;
                const timer = setInterval(function(){
                    i++;
                    if(i === timeLimit){
                        clearInterval(timer);
                        viewContent.classList.add("show");
                        loading.classList.add("hide");
                    }
                }, 1000);
            }
        </script>
    </body>
</html>