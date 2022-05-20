<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%--导入静态包含base标签,css样式,jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			/*给删除按钮绑定单机事件*/
			$(".deleteItem").click(function () {
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？");
			});
		});

		$(function () {
			/*给清空购物车按钮绑定单机事件*/
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗？");
			});
		});

		$(function () {
			/*给修改商品数量绑定焦点事件*/
			$(".updateCount").change(function () {
				var name = $(this).parent().parent().find("td:first").text();
				var count =this.value;
				var id=$(this).attr("bookId");
				if (confirm("你确定要修改商品【"+name+"】的值为："+count+"吗？")){
				/*修改数据*/
					location.href="cartServlet?action=updateCount&count="+count+"&id="+id;
				}else{
				/*返回为原来数据*/
					this.value=this.defaultValue;
				}
			});
		});

	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/google.gif" height="88" width="345">
			<span class="wel_word">购物车</span>

			<%--导入成功后导航栏--%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main" style="background: ghostwhite">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<%--购物车为空--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5" >
						<a href="index.jsp" style="color: red"> 您的购物车为空！快去逛逛！</a>
					</td>
				</tr>
			</c:if>

			<%--购物车非空--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<%--遍历购物车中的内容--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input type="text" style="width: 25px" class="updateCount"
							bookId="${entry.value.id}"	   value="${entry.value.count}"/>
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


		</table>
		<%--购物车非空时才输出此内容--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--导入底部标签--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>