<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>


<%-- base标签。css样式，jquery引入 --%>
<%@include file="/pages/common/head.jsp" %>
<script type="text/javascript">
	
	$(function(){
		$("a.deleteBook").click(function(){
			//在响应事件的function中，有一个this对象，这个this对象是当前响应事件的dom对象
			var name = $(this).parent().parent().find("td:first").html();
			//1.提示用户，确认删除操作
			return confirm("你确定要删除【" + name + " 】记录吗?");
// 			alert(result);
// 			//2.如果用户点击确定 ，就删除
// 			if (result) {
// 				return true;
// 			} else {
// // 			如果用户点击取消，就不删除
// 				return false;
// 			}
// 			// 取消标签的默认行为
// 			return false;
		});
	});

</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

			<!-- manager模块的菜单 -->
			<%@ include file="/pages/common/manager_menu.jsp" %>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>	
			
			<%-- 
				items表示遍历的数据源
				var 表示当前正在遍历到的对象 
			 --%>
			<c:forEach items="${ requestScope.page.items }" var="book">
				<tr>
					<td>${ book.name }</td>
					<td>${ book.price }</td>
					<td>${ book.author }</td>
					<td>${ book.sales }</td>
					<td>${ book.stock }</td>
					<td><a href="manager/bookServlet?action=queryBookById&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">修改</a></td>
					<td><a class="deleteBook" href="manager/bookServlet?action=deleteBook&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">删除</a></td>
				</tr>	
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${ requestScope.page.pageNo }">添加图书</a></td>
			</tr>	
			<tr>
				<td colspan="7">
					
					<%@ include file="/pages/common/page.jsp" %>	
				
					
				</td>
			</tr>	
		</table>
	</div>
	

	<%-- 页脚本的引入 --%>
	<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>