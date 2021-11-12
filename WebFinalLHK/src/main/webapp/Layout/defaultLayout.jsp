<%@ page import="com.unfame.Model.ViewContent" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Layout/MyStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <meta charset="UTF-8">
    <title><tiles:getAsString name="title" /></title>

</head>
<body>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="sidebar" />
    <tiles:insertAttribute name="body" />
</body>
</html>
