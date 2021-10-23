<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>

<%
String id = request.getParameter("id");
Connection connection;
PreparedStatement prepStatement;
ResultSet resultset;

Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useSSL=false","teacher","@password");
prepStatement = connection.prepareStatement("delete from records where id = ?");
prepStatement.setString(1, id);
prepStatement.executeUpdate();  

%>

<script>    
	alert("Student Deleted");    
</script>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Delete student</title>
	</head>
<body>

</body>
</html>