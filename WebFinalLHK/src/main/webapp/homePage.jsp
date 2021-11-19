<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
    <title></title>
</head>
<body>
    <tiles:insertDefinition name="defaultLayout"/>
    <%
        RequestDispatcher dispatcher=request.getRequestDispatcher("Login_Page.tiles");
        dispatcher.forward(request,response);
    %>
</body>
</html>