<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Layout/MyStyle.css">--%>
<%--    <title><tiles:getAsString name="title" /></title>--%>
<%--</head>--%>
<body>	
    <form id="login-form" action="<%=request.getContextPath()%>/loginAccount" method="post">   		
        <div class="login-container">
					
            <div class="login-header">
                <label>Please Sign In</label>
            </div>

            <div class="login-content">
                <input class="login-textbox" id="email" name="email" type="text" placeholder="E-mail">
                <div class="error">
                    <span class="error-message"></span>
                </div>
            </div>

            <div class="login-content">
                <input class="login-textbox" id="password" name="password" type="password" placeholder="Password">
                <div class="error">
                    <span class="error-message"></span>
                </div>
            </div>

            <div class="login-content">
                <input class="login-checkbox" type="checkbox" name="remember_me">
                <label>Remember me</label>
            </div>

            <div class="login-content">
                <input id="login-button" name="submit" type="submit" value="Login">
            </div>

            <a class="login-register" href="<%=request.getContextPath()%>/register">
                <span style="color: blue;">Click here to Register</span>
            </a>
			
        </div>
    </form>
</body>
	 
<script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/Login_Register.js"> </script>
<script>
    Validator({
        form:'#login-form',
        error: '.error-message',
        rules: [
            Validator.isEmail('#email'),
            Validator.isRequired('#password'),
            Validator.needLength('#email', 5, 50),
            Validator.needLength('#password', 8, 30),
        ]
    })
    <%=request.getAttribute("Message")%>    
</script>
<%--</html>--%>