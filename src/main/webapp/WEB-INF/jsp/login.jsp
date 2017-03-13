<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<%=basePath%>resources/sdk/dist/strophe.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/sdk/dist/websdk-1.1.2.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/demo/javascript/dist/webim.config.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/jquery-1.9.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	login
	</br> ${username}
	</br> ${password}
<button onclick="demo()">跳转到demo</button>
<button onclick="page()">跳转到Page</button>
    <section id='main' class='w100'>
        <article id='demo'></article>
        <article id='components'></article>
    </section>
    



 <!--demo javascript-->
	<script type="text/javascript" src="<%=basePath%>resources/demo/javascript/dist/demo.js"></script>
</body>
<script type="text/javascript">

function demo() {
	 location.href='<%=basePath%>demo'; 
};
function page() {
	 location.href='<%=basePath%>math/page'; 
};
</script>
</html>