<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%; text-align: center">
		<table border="1">
			<tr>
				<th width="60px"><input type="checkbox" name="ck" id="ck">
				</th>
				<th>产品图片</th>
				<th>产品编号</th>
				<th>产品名称</th>
				<th>市场价格</th>
				<th>商场价格</th>
				<th>产品日期</th>
				<th>产品描述</th>
				<th>操作</th>
			</tr>
			<c:if test="${not empty requestScope.proLimit}">
				<c:forEach items="${requestScope.proLimit.list}" var="p" varStatus="v">
					<tr>
						<td><input type="checkbox" name="checkbox" value="${p.pid}">
						</td>
						<td><img width="100px" alt="图片发生问题"
							src="${pageContext.request.contextPath}/${p.pimage } "></td>
						<td>${v.count }</td>
						<td>${p.pname }</td>
						<td>${p.market_price }</td>
						<td>${p.shop_price }</td>
						<td>${p.pdate }</td>
						<td>${p.pdesc }</td>
						<td>
							<!-- 给check方法传参需要带‘’ --> <a href="#" onclick="check('${p.pid}')">删除</a>
							<a
							href="${pageContext.request.contextPath}/ProductServlet?myMethod=find&pid=${p.pid}">修改</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="9" align="center">
						<c:if test="${requestScope.proLimit.pageNumber!=1}">
							<a href="${pageContext.request.contextPath}/ProductServlet?myMethod=findLimit&pageNubmer=1">首页</a>
						&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/ProductServlet?myMethod=findLimit&pageNubmer=${requestScope.proLimit.pageNumber-1}">上一页</a>
						</c:if>
						<c:if test="${requestScope.proLimit.pageNumber!=requestScope.proLimit.totalPage}">
						&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/ProductServlet?myMethod=findLimit&pageNubmer=${requestScope.proLimit.pageNumber+1}">下一页</a>
						&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/ProductServlet?myMethod=findLimit&pageNubmer=${requestScope.proLimit.totalPage}">尾页</a>
						</c:if>
						当前页${requestScope.proLimit.pageNumber}/总页数${requestScope.proLimit.totalPage}
					</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>