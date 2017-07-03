<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>


<%-- base标签。css样式，jquery引入 --%>
<%@include file="/pages/common/head.jsp" %>


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
是否是添加操作：${ empty requestScope.book }

		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<!-- manager模块的菜单 -->
			<%@ include file="/pages/common/manager_menu.jsp" %>

		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="post">
<%-- 				当前页码：${ param.pageNo } --%>
				<input type="hidden" name="pageNo" value="${ param.pageNo }" />
				<c:choose>
					<%-- 当empty requestScope.book 为true的时候，说明是添加图书操作 --%>
					<c:when test="${ empty requestScope.book }">
						<input type="hidden" name="action" value="addBook"/>
					</c:when>
					<%-- 当empty requestScope.book 为false的时候，说明是修改图书操作 --%>
					<c:otherwise>
						<input type="hidden" name="action" value="updateBook"/>
						<input type="hidden" name="id" value="${book.id }"/>
					</c:otherwise>
				</c:choose>
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
						<td><input name="name" type="text" value="${ requestScope.book.name }"/></td>
						<td><input name="price" type="text" value="${ requestScope.book.price }"/></td>
						<td><input name="author" type="text" value="${ requestScope.book.author }"/></td>
						<td><input name="sales" type="text" value="${ requestScope.book.sales }"/></td>
						<td><input name="stock" type="text" value="${ requestScope.book.stock }"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		

	<%-- 页脚本的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>