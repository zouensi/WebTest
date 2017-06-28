<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	/* js方法 */
	function check(pid) {
		var flag = confirm("亲,是否要删除该条商品?");
		if (flag) {
			location.href = "${pageContext.request.contextPath}/ProductServlet?myMethod=delete&pid="
					+ pid;
		}
	}
	/* 下面是jquery方法 */
	$(
			function() {
				/* 全选按钮 */
				$("#ck").click(
						function() {
							/* 获取所有行的checkbox 根据头checkbox的状态进行更改 */
							$("input[name=checkbox]").prop("checked",
									$("#ck").prop("checked"));
						});

				/* 删除所有信息 */
				$("#deleteAll").click(function() {
					var flag = confirm("亲,是否要删除选中的商品");
					if (flag) {
						$("#form").submit();
					}
				});
				$("#findByCondition").click(function() {
					$("#formFind").submit();
				});

			})
</script>
<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%; text-align: center">
		<table border="1">
			<tr>
				<td colspan="9" align="right">
					<form action="${pageContext.request.contextPath}/ProductServlet"
						method="post" id="formFind">
						按商场价格查询<input type="text" id="findByShop_price"
							name="findByShop_price" /> 按商品描述查询<input type="text"
							id="findByPdesc" name="findByPdesc" /> <input type="hidden"
							name="myMethod" value="findByCondition" /> <input type="button"
							value="查询" id="findByCondition" /> <input type="button"
							value="全部删除" id="deleteAll" />
					</form>
				</td>
			</tr>
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
			<form action="${pageContext.request.contextPath}/ProductServlet"
				method="post" id="form">
				<input type="hidden" name="myMethod" value="deleteAll" />
				<c:if test="${not empty requestScope.product}">
					<c:forEach items="${requestScope.product}" var="p" varStatus="v">
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
				</c:if>
			</form>
		</table>
	</div>
</body>
</html>