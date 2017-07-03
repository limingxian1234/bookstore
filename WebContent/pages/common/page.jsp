<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 分页的开始 --%>
<div id="page_nav">
	<a href="${ requestScope.page.url }&pageNo=1">首页</a>
	<c:if test="${ requestScope.page.pageNo > 1}">
		<a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageNo - 1 }">上一页</a>
	</c:if>
	<c:choose>
		<%-- 页码的显示分为两种情况，第一种是总页码数小于等于5的情况，只需要遍历1到总页码数即可 --%>
		<c:when test="${ requestScope.page.pageTotal <= 5 }">
			<%-- 我们可以把begin和end的值，保存到域对象中 --%>
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="${ requestScope.page.pageTotal }"></c:set>
		</c:when>
		<%-- 第二种情况，总页码数大于5的情况 --%>
		<c:otherwise>
			<c:choose>
				<%-- 第一种小情况，当前页码为前面的三个。那么 显示的页码永远都是1到5 --%>
				<c:when test="${ requestScope.page.pageNo <= 3 }">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="5"></c:set>
				</c:when>
				<%-- 第二种小情况，当前页码为最后的三个。那么 页码永远 都是 【最大页码-4】 到 【最大页码】 --%>
				<c:when test="${ requestScope.page.pageNo >= requestScope.page.pageTotal - 2}">
					<c:set var="begin" value="${ requestScope.page.pageTotal - 4 }"></c:set>
					<c:set var="end" value="${ requestScope.page.pageTotal }"></c:set>
				</c:when>
				<%-- 第三种小情况，当前页码为其他的页码。这个时候，显示的页码范围是 【当前页码-2】到【当前页码+2】 --%>
				<c:otherwise>
					<c:set var="begin" value="${ requestScope.page.pageNo - 2 }"></c:set>
					<c:set var="end" value="${ requestScope.page.pageNo + 2 }"></c:set>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	
	<%
		System.out.println( "begin = " + pageContext.getAttribute("begin") );
		System.out.println( "end = " + pageContext.getAttribute("end") );
	%>

	<c:forEach begin="${ begin }" end="${ end }" var="i">
		<c:if test="${ i == requestScope.page.pageNo }">
			【${ i }】
		</c:if>
		<c:if test="${ i != requestScope.page.pageNo }">
			<a href="${ requestScope.page.url }&pageNo=${ i }">${ i }</a>
		</c:if>
	</c:forEach>

	
	
	<c:if test="${ requestScope.page.pageNo < requestScope.page.pageTotal}">
		<a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageNo + 1 }">下一页</a>
	</c:if>
	<a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageTotal }">末页</a>
	共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录 到第<input value="${ requestScope.page.pageNo }" name="pn" id="pn_input"/>页
	<input type="button" id="seachBtn" value="确定">
</div>


<script type="text/javascript">
	// 页面加载完成之后
	$(function(){
		//给搜索的按钮绑定单击事件
		$("#seachBtn").click(function(){
			//1.获取页码输入框中的页码值。
			var pageNo = $("#pn_input").val();
// 								alert(pageNo);
			//2.向服务器发起请求，manager/bookServlet?action=page&pageNo=页码值
			//在js中提供了一个对象 Locatoin，Location对象当中有一个属性。叫href。它可以获取、设置当前浏览器地址栏的地址
// 								alert(location.href);
			location.href = "${ requestScope.page.url }&pageNo="+pageNo;
		});
	});

</script>

<%-- 分页结束 --%>