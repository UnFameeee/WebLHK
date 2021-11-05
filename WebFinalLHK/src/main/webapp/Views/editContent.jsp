<%@ page import="java.util.List" %>
<%@ page import="com.unfame.Model.ViewContent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div class="edit-content">
    <form  class="form-content" id="editContent-form"  action="<%=request.getContextPath()%>/update" method="post" >
        <h1>Edit Content</h1>
        <hr>

        <div class="container">
            <div class="edit-content-title">
                <h2>Content Form Elements</h2>
            </div>
            <%

                ViewContent existingContent = (ViewContent)request.getAttribute("content");
            %>
            <div class="input-content">
                <div class="input-field">
                    <label for="">Title<br></label>
                    <input type="text" class="title" name="title" id="input_title" placeholder="Enter the title"
                        value="<%= existingContent.getTitle()%>">
                    <div class="invalid-Message">
                        <span class="form-message"></span>
                    </div>
                </div>
                <div class="input-field">
                    <label for="">Brief<br></label>
                    <textarea id ="input_brief" class="brief" name="brief" rows="4" cols="50"
                              value="<%= existingContent.getBrief()%>"> </textarea>
                    <div class="invalid-Message">
                        <span class="form-message"></span>
                    </div>
                </div>
                <div class="input-field">
                    <label for="">Content<br></label>
                    <textarea id="input_content" class="content" name="content" rows="4" cols="50"
                              value="<%= existingContent.getContent()%>"> </textarea>
                    <div class="invalid-Message">
                        <span class="form-message"></span>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Submit Button">
                    <input type="reset" value="Reset Button">
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/AddContent.js"></script>
</body>