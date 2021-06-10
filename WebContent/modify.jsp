<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	String name,id,pw,phone1,phone2,phone3,gender;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>
</head>
<body>
<%
//	String id = (String)session.getAttribute("id"); // Object type이라서 String type으로 casting 해줘야한다
	
	id=(String)session.getAttribute("id");
	String query="select * from memberpe where id='" +id+"'";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
	statement=connection.createStatement();
	resultSet=statement.executeQuery(query);
	
	while(resultSet.next()) {
		name=resultSet.getString("name");
		pw=resultSet.getString("pw");
		phone1=resultSet.getString("phone1");
		phone2=resultSet.getString("phone2");
		phone3=resultSet.getString("phone3");
		gender=resultSet.getString("gender");
	}
%>


</body>
</html>


















