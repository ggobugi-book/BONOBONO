<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
   String userid = request.getParameter("userid");
   String title = request.getParameter("title");
   String page1 = request.getParameter("page");
	
   connectDB.updatePage(userid,title,page1);
   
   out.println("1");
%>
</body>
</html>