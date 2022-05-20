<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>

	<%--导入静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/google.gif" height="88" width="365">
			<span class="wel_word">结算</span>
		<%--导入成功后导航栏--%>
		<div>
			<a href="manager/bookServlet?action=page">图书管理</a>
			<a href="pages/manager/order_manager.jsp">订单管理</a>
			<a href="pages/cart/cart.jsp">返回</a>
		</div>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为: ${sessionScope.orderId}</h1>
		
	
	</div>

	<%--导入底部标签--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>