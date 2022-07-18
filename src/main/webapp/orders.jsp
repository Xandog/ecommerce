<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ecommerce.connection.DBConnection" %>
<%@ page import="com.ecommerce.model.*" %>

<%
	User auth = (User) request.getSession().getAttribute("auth");
	if(auth != null) {
		request.setAttribute("auth", auth);
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file="includes/head.jsp" %>
		<title>E-Commerce</title>
	</head>
	<body>
		<%@include file="includes/navbar.jsp" %>
		<%@include file="includes/footer.jsp" %>
	</body>
</html>