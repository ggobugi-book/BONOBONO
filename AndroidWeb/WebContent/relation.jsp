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
	
	String title = request.getParameter("title");
	String page1 = request.getParameter("page");
	
   String returns = connectDB.relation();

   
   System.out.println(returns);
   // 안드로이드로 전송
   out.println(returns);
   %>
</body>

</html>