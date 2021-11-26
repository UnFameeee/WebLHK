<%@ page import="java.util.List" %>
<%@ page import="com.unfame.model.ViewContent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%
    ViewContent existingContent = (ViewContent)request.getAttribute("content");
    request.setAttribute("eContent",existingContent);
%>

<body>
    <div class="add-content">
        <c:if test ="${eContent !=null}">
            <form  class="form-content" id="editContent-form"  action="<%=request.getContextPath()%>/update?Id=<%=existingContent.getId()%>" method="post" >
                <h1>Edit Content</h1>
        </c:if>
                <c:if test ="${eContent ==null}">
                <form  class="form-content" id="addContent-form"  action="<%=request.getContextPath()%>/add" method="post" >
                    <h1>Add Content</h1>
                </c:if>
            <hr>
            <div class="container">
                <div class="add-content-title">
                    <h2>Content Form Elements</h2>
                </div>
                <div class="input-content">
                    <div class="input-field">
                        <label for="">Title<br></label>
                        <c:if test ="${eContent !=null}">
                        <input type="text" class="title" name="title" id="input_title" placeholder="Enter the title"
                               value="<%= existingContent.getTitle()%>">
                        </c:if>
                        <c:if test ="${eContent ==null}">
                        <input type="text" class="title" name="title" id="input_title" placeholder="Enter the title" >
                        </c:if>
                        <div class="invalid-Message">
                            <span class="form-message"></span>
                        </div>
                    </div>
                    <div class="input-field">
                        <label for="">Brief<br></label>
                        <c:if test ="${eContent !=null}">
                             <textarea id ="input_brief" class="brief" name="brief" rows="4" cols="50"> <%= existingContent.getBrief()%></textarea>
                        </c:if>
                        <c:if test ="${eContent ==null}">
                        <textarea id ="input_brief" class="brief" name="brief" rows="4" cols="50" > </textarea>
                        </c:if>
                        <div class="invalid-Message">
                            <span class="form-message"></span>
                        </div>
                    </div>
                    <div class="input-field">
                        <label for="">Content<br></label>
                        <c:if test ="${eContent !=null}">
                            <textarea id="input_content" class="content" name="content" rows="4" cols="50"
                            > <%= existingContent.getContent()%></textarea>
                        </c:if>
                        <c:if test ="${eContent ==null}">
                        <textarea id="input_content" class="content" name="content" rows="4" cols="50" > </textarea>
                        </c:if>
                        <div class="invalid-Message">
                            <span class="form-message"></span>
                        </div>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Submit Button">
                        <c:if test ="${eContent !=null}">
                            <input type="button" value="Reset Button" id="reset-btn">
                        </c:if>
                        <c:if test ="${eContent ==null}">
                            <input type="reset" value="Reset Button">
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/AddContent.js"></script>
</body>