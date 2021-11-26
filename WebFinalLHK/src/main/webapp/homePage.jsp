<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
    <title></title>
</head>
<body>
    <tiles:insertDefinition name="defaultLayout"/>
    <%
    	Cookie[] cookies = request.getCookies();
    	//Kiểm tra cookie 
    	for(Cookie c: cookies){
    		if(c.getName().equals("check")){
    			 RequestDispatcher dispatcher = request.getRequestDispatcher("/view");
    		     dispatcher.forward(request,response);	
    		}    			
    	}

        RequestDispatcher dispatcher = request.getRequestDispatcher("Login_Page.tiles");
        dispatcher.forward(request,response);
    %>
</body>
</html>