<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/date.js">
	
</script>
<style type="text/css">
</style>
</head>
<body>
	<div style="width: 100%; text-align: center;">
		<form action="${pageContext.request.contextPath }/ProductServlet" method="get">
			<input type="hidden" name="myMethod" value="insert">
			<table align="center">
				<tr>
					<td>
						商品名称
					</td>
					<td>
						<input type="text" name="pname"/>
					</td>
				</tr>
				<tr>
					<td>
						市场价格
					</td>
					<td>
						<input type="text" name="market_price"/>
					</td>
				</tr>
				<tr>
					<td>
						商场价格
					</td>
					<td>
						<input type="text" name="shop_price"/>
					</td>
				</tr>
				<tr>
					<td>
						商品日期
					</td>
					<td>
						<input type="text" readonly="readonly" name="pdate" onclick="SetDate(this,'yyyy-MM-dd')"/>
					</td>
				</tr>
				<tr>
					<td>
						商品描述
					</td>
					<td>
						<input type="text" name="pdesc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="提交"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>