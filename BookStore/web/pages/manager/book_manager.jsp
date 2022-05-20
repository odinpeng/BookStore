<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%--图书管理界面--%>
	<%--导入静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            /*给删除的a标签绑定单机事件，用于提示是否删除*/
            $("a.deleteClass").click(function () {
                /*confirm为确认提示操作
                * 两个按钮分别返回true和false
                * 返回true执行事件，返回false终止事件执行
                * */
                return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】？");
            });
        });
    </script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/google.gif" height="88" width="365" >
			<span class="wel_word">图书管理系统</span>

		<%@include file="../common/manager_menu.jsp"%>
	</div>
	
	<div id="main" style="background: ghostwhite">


		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<%--遍历request域中保存的book信息--%>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<%--请求manager/bookServlet页面，使用其getBook方法 并获取id 指名为update操作--%>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&
					pageNo=${requestScope.page.pageNo}">修改</a></td>
					<%--请求manager/bookServlet页面，使用其delete方法 并获取id--%>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&
					pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td> <%--指名为add操作--%>
			</tr>
		</table>

		    <div id="page_nav">
			<%--分页操作--%>
			<c:if test="${requestScope.page.pageNo >1}">
				<a href="manager/bookServlet?action=page&pageNo=1">首页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--页码输出--%>
			<c:choose>
				<%--情况一：总页码数<=5，显示范围：1-总页码 --%>
				<c:when test="${requestScope.page.pageTotal<=5}">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${requestScope.page.pageTotal}"/>
				</c:when>

				<%--情况二：总页码>5--%>
				<c:when test="${requestScope.page.pageTotal>5}">
					<c:choose>
						<%--2.1: 当前页码为前三个，页码范围1-5 --%>
						<c:when test="${requestScope.page.pageNo<=3}">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
				        </c:when>

						<%--2.2： 当前页面为后三个，页码范围6-10--%>
						<c:when test="${requestScope.page.pageNo> requestScope.page.pageTotal-3}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
							<c:set var="end" value="${requestScope.page.pageTotal}"/>
						</c:when>

						<%--2.3: 页面为4,5,6,7, 页码范围：当前页码-2 —— 当前页码+2  --%>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNo-2}"/>
							<c:set var="end" value="${requestScope.page.pageNo+2}"/>
						</c:otherwise>

					</c:choose>
				</c:when>
			</c:choose>
			<%--输出分页值--%>
			<c:forEach begin="${begin}" end="${end}" var="i">
				<%--每个显示数字都能够点击，并且跳转到manager/bookServlet--%>
				<%--为当前页码时，不可点击，直接显示【i】--%>
				<c:if test="${requestScope.page.pageNo==i}">
					【${i}】
				</c:if>
				<%--不为当前页码时，可以点击，并跳转到指定索引页码--%>
				<c:if test="${requestScope.page.pageNo!=i}">
					<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>

			<c:if test="${requestScope.page.pageNo< requestScope.page.pageTotal}">
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页

			<input id="searchPageBtn" type="button" value="确定">
			<script>
				$(function () {
					/*跳到指定的页码*/
					$("#searchPageBtn").click(function () {
						var pageNo= $("#pn_input").val();

						var pageTotal=${requestScope.page.pageTotal};
						location.href="${pageScope.basePath}manager/bookServlet?action=page&pageNo="+pageNo;
					});
				})
			</script>
		</div>

	</div>

	<%--导入底部标签--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>