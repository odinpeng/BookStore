<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<%--书籍增加与修改操作--%>
	<%--导入静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/google.gif" height="88" width="365" >
			<span class="wel_word">编辑图书</span>
			<%@include file="../common/manager_menu.jsp"%>
		</div>
		
		<div id="main" style="background: ghostwhite">

			<form action="manager/bookServlet" method="get"> <%--此处post请求会乱码--%>
				<%--提交时获取当前pageNo,提交成功后再次跳转到此pageNo页面--%>
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<%--通过判断request域中是否包含有图书修改信息的对象，如果有则为update，没有为add--%>
				<input type="hidden" name="action" value="${empty requestScope.book ? "add":"update" }" />
				<%--update方法需要传入id，本网页中并未传入id,所以需要用影藏域传入id--%>
				<input type="hidden" name="id" value="${requestScope.book.id}" />
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%--导入底部标签--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>