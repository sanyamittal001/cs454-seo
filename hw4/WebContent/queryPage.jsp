<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<center>
	<form action="<%=request.getContextPath() %>/QueryTermController" method="post" >
		<table>
			<tr>
				<td>Query :</td>
				<td><input type="text" name="queryTerm" ></td>
			</tr>
			<tr>
				<td>Max page search :</td>
				<td><input type="number" name="maxSearch" ></td>
			</tr>
			<tr>
				<td><input type="submit" name="submitForm" ></td>
			</tr>
		</table>
	</form>
</center>

</body>
</html>