<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width:100%;text-align: center">
		<a href="${pageContext.request.contextPath}/ProductServlet?myMethod=findAll">查看信息</a>
		<a href="${pageContext.request.contextPath}/jsp/add.jsp">添加信息</a>
	</div>
</body>
</html>