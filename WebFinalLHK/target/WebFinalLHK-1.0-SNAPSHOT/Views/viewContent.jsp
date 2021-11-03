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
                    <c:forEach var="content" items="${ListContent}">
                        <tr>
                            <td><c:out value="${content.Id}"/></td>
                            <td><c:out value="${content.Title}"/></td>
                            <td><c:out value="${content.Brief}"/></td>
                            <td><c:out value="${content.CreateDate}"/></td>
                            <td>
                                <a href="edit?Id= <c:out value='${content.id}' />">Edit</a>
                                <a href="delete?Id= <c:out value='${content.id}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
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