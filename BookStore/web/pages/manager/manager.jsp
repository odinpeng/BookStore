<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>

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
			<img class="logo_img" alt="" src="static/img/google.gif" height="88" width="365" >
			<span class="wel_word">后台管理系统</span>

		<%@include file="../common/manager_menu.jsp"%>
	</div>
	
	<div id="main" style="background: ghostwhite">
		<h1 style="font-size: 30px">欢迎管理员进入后台管理系统</h1>
	</div>

	<%--导入底部标签--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>