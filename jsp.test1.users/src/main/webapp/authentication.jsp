<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<%
if(session.getAttribute("userLogged") == null){
	response.sendRedirect("./login.jsp");
}
	String utenteLoggato = "" + session.getAttribute("userLogged");
%>     
	<h2>L'utente loggato è <%out.print(utenteLoggato); %></h2>
</body>
</html>