<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="">
			<a href="queryPage.jsp">HOME</a>
			<table border="1">
				<tr>
					<th>Document</th>
					<th>URL</th>
				</tr>

			<c:forEach items="${sessionScope.resultList}" var="i">
				<c:set var="a" value="${i}" />
				<c:set var="b" value="${fn:replace(a,'-', ':')}" />
				<c:set var="c" value="${fn:replace(b,',', '/')}" />
				<c:set var="d1" value="${fn:replace(c,'.txt', '')}" />
				
				<c:set var="indexHttp" value="${fn:indexOf(d1, 'http:')}" />
				<c:set var="indexlength" value="${fn:length(d1)}" />
				
				<c:set var="string2" value="${fn:substring(d1,indexHttp ,indexlength)}" />
				<c:set var="d" value="${fn:split(c, 'http:')}" />
				
				<c:set var="e" value="${fn:split(d, '.txt')}" />
				
				<tr>
					<td>${i}</td>
					 <td><a href="${string2}" target="_blank">${string2}</a></td>
				</tr>
			</c:forEach>
			</table>


		</form>
	</center>
</body>
</html>