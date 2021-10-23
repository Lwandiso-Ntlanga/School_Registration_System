<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>

<%
if(request.getParameter("submit")!=null)
{
	String id = request.getParameter("id");
	String name = request.getParameter("studentName");
	String course = request.getParameter("course");
	String fee = request.getParameter("fee");
	
	Connection connection;
	PreparedStatement prepStatement;
	ResultSet resultset;
	
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false","teacher","@password");
	prepStatement = connection.prepareStatement("update records set studentName = ?,course =?,fee= ? where id = ?");
	prepStatement.setString(1, name);
	prepStatement.setString(2, course);
	prepStatement.setString(3, fee);
	prepStatement.setString(4, id);
	prepStatement.executeUpdate();  
%>
        
<script>  
	alert("Student Updated");          
</script>

    <%            
    }%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Student Update</title>
	</head>
	
	<body>
		<h1>Student Update</h1>

        <div class="row">
            <div class="col-sm-4">
                <form  method="POST" action="#" >
                    
                <%    
	                Connection connection;
	                PreparedStatement prepStatement;
	                ResultSet resultset;
	
	                Class.forName("com.mysql.jdbc.Driver");
	                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?useSSL=false","teacher","@password");
	                  
	                String id = request.getParameter("id");
	                  
	                prepStatement = connection.prepareStatement("select * from records where id = ?");
	                prepStatement.setString(1, id);
	                resultset = prepStatement.executeQuery();
	                
	               	while(resultset.next())
	                {
                    
                    %>
                    <div alight="left">
                        <label class="form-label">Student Name</label>
                        <input type="text" class="form-control" placeholder="Student Name" value="<%= resultset.getString("studentName")%>" name="sname" id="sname" required >
                     </div>
                        
                    <div alight="left">
                        <label class="form-label">Course</label>
                        <input type="text" class="form-control" placeholder="Course" name="course" value="<%= resultset.getString("course")%>" id="course" required >
                     </div>
                        
                     <div alight="left">
                        <label class="form-label">Fee</label>
                        <input type="text" class="form-control" placeholder="Fee" name="fee" id="fee" value="<%= resultset.getString("fee")%>" required >
                     </div>
                    
                    <% }  %>
  
                    </br>                        
                     <div alight="right">
                         <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                         <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                     </div>  
                        
                     <div align="right">                            
                         <p><a href="index.jsp">Click Back</a></p>
                     </div>  
                </form>
            </div>          
        </div>

	</body>
</html>