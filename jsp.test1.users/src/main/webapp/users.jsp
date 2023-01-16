<%@page import="service.UserService"%>
<%@page import="test.TestUsersSingleton"%>
<%@page import="pojo.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	String utenteLoggato = "" + session.getAttribute("userLogged");
%>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>List users</title>
	</head>
	<body>
		<form action="./DeleteUserServlet" method="post">
			<table>
				<% ArrayList<User> lista = null;
				   lista = UserService.getIstance().selectAllUsers();
				   for (User item : lista) {
				    	%>
			    	
			    	<tr>
			    	<td><input type="radio" name="selectedUserId" value="<%out.print(item.getId());%>"/></td>
			    	<td><%= item.getId() %></td>
	 		    	<td><%= item.getEmail() %></td>
	 		    	<td><%= item.getPassword() %></td>
	 		    	<td><%= item.getFirstname() %></td>
	 		    	</tr>
	 		    	
			    	<%
				    }
				%>
			</table>
			<input type="submit" name="selectedUserId" value="Cancella"/>
		</form>
	</body>
</html>