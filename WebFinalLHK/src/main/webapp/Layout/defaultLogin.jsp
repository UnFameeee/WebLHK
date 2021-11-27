<%@ page import="com.unfame.global.IdGlobal" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Layout/MyStyle.css">
    <meta charset="ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
</head>
<body>
    <tiles:insertAttribute name="body" />
</body>
</html>
