<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookStore</title>
	<%--导入静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	
	<script type="text/javascript">
		$(function () {
			$("button.addToCart").click(function () {
				var bookId=$(this).attr("bookId");
				location.href= "cartServlet?action=addItem&id="+bookId;
			});
		});
	</script>
</head>
<body style>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/google.gif" height="88" width="365">
			<span class="wel_word">BOOK STORE</span>
			<div>
				<%--如果未登录，sessionScope.user内容为空，则显示登录，注册按钮--%>
				<c:if test="${ empty sessionScope.user}">
					<a href="pages/user/login.jsp">[登录]</a> |
					<a href="pages/user/regist.jsp">[注册]</a> &nbsp;&nbsp;
				</c:if>

				<c:if test="${ not empty sessionScope.user}">
					欢迎<span class="um_span">${sessionScope.user.username}</span>
					<%--<a href="../order/order.jsp">[订单]</a>--%>
					<a href="userServlet?action=logout">[注销]</a>
				</c:if>

				<a href="pages/cart/cart.jsp">[购物车]</a>
				<a href="pages/manager/manager.jsp">[后台管理]</a>
			</div>
	</div>
	<div id="main" style="background: ghostwhite">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get" style="margin-left: -43px">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">

				<%--购物车为空时--%>
				<c:if test="${empty sessionScope.cart.items}">
					<span style="margin-right: 30px">请添加商品</span>
					<div style="margin-right: 30px">
						<span style="color: red" >当前购物车为空</span>
					</div>
				</c:if>

				<%--购物车非空时--%>
				<c:if test="${not empty sessionScope.cart.items}">
					<span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
					<div>
						您刚刚将<span style="color: red">${sessionScope.lastItem}</span>加入到了购物车中
					</div>
				</c:if>

			</div>

			<%--遍历页面商品--%>
			<c:forEach items="${requestScope.page.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button bookId="${book.id}" class="addToCart">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<%--分页操作--%>
		<div id="page_nav">
			<%--大于首页，才显示--%>
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="${ requestScope.page.url }&pageNo=1">首页</a>
				<a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--页码输出的开始--%>
			<c:choose>
				<%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
				<c:when test="${ requestScope.page.pageTotal <= 5 }">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${requestScope.page.pageTotal}"/>
				</c:when>
				<%--情况2：总页码大于5的情况--%>
				<c:when test="${requestScope.page.pageTotal > 5}">
					<c:choose>
						<%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
						<c:when test="${requestScope.page.pageNo <= 3}">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
						</c:when>
						<%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
						<c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
							<c:set var="end" value="${requestScope.page.pageTotal}"/>
						</c:when>
						<%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNo-2}"/>
							<c:set var="end" value="${requestScope.page.pageNo+2}"/>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>

			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${i == requestScope.page.pageNo}">
					【${i}】
				</c:if>
				<c:if test="${i != requestScope.page.pageNo}">
					<a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--页码输出的结束--%>

			<%-- 如果已经 是最后一页，则不显示下一页，末页 --%>
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">

			<script type="text/javascript">

				$(function () {
					// 跳到指定的页码
					$("#searchPageBtn").click(function () {

						var pageNo = $("#pn_input").val();

						<%--var pageTotal = ${requestScope.page.pageTotal};--%>
						<%--alert(pageTotal);--%>

						// javaScript语言中提供了一个location地址栏对象
						// 它有一个属性叫href.它可以获取浏览器地址栏中的地址
						// href属性可读，可写
						location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;
					});
				});

			</script>
		</div>
	
	</div>
	<%--导入底部标签--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>