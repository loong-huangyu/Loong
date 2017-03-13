<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<tr><td>id</td><td>分数</td><td>类型</td></tr>
</table>
<table>
<c:forEach items="${pages.pageDatas}" var="math">
<tr>
<td class="hidden-480">${math.id}</td>
<td class="hidden-480">${math.score }</td>
<td class="hidden-480">${math.type }</td>
</tr>
</c:forEach>
</table>

<c:if test="${pages.isHavePrePage == true }">
<a href='<%=basePath%>math/pages?nowPage=${pages.pageIndex-1}&pageSize=5'>上一页</a>
</c:if>
<c:if test="${pages.isHaveNextPage == true}">
<a href="<%=basePath%>math/pages?nowPage=${pages.pageIndex+1}&pageSize=5">下一页</a>
</c:if>

</body>
</html>