<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=basePath%>resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=jlki1tGE6TYFSOKnwM7Hnpe8UG3KGwv0"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="<%=basePath%>user/login" method="post">
		账号：<input type="text" name="username" /> 密码：<input type="password"
			name="password"> <input type="submit" value="提交">
	</form>
	
	<div id="container" style="height: 500px; width: 600px"></div>
	<script type="text/javascript">
		var map = new BMap.Map("container"); // 创建地图实例  
		var point = new BMap.Point(116.404, 39.915); // 创建点坐标  
		map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
	</script>
</body>
<script type="text/javascript">
	/* $(function () {
	 var a = [["0-1","0-2"],["1-1","1-2"],["1-2","2-2"]];
	 console.log("123456");
	 console.log('a[0]='+a[0]);
	 console.log('a[1][0]='+a[1][0]);
	 for(var index =0 ;index<3;index++){
	 var tit = a[index][0].toString();
	 console.log('tit='+tit);
	 }
	 }); */
</script>
</html>

