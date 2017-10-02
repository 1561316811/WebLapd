<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.cyl.user.*" %>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
	}
	
	User u = UserService.getInstance().getUserByIdUser(idUser);
	
%>

<%
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="icon-font/iconfont.css">
</head>
<body>
	<header>
		<div class="left_icon">
			<a href="Center.jsp?idUser=<%= idUser %>" class="iconfont">&#xe624;</a>
		</div>
	</header>
	
	
	<div class="d-display">
		<ul>
			<li><a href="UserDataModify.jsp?idUser=<%= idUser %>"><input type="button" value="修改资料"></a></li>
			<li>工作牌号:&nbsp<%= u.getId() %>&nbsp号</li>
			<li>生日:&nbsp<%= u.getBirthday() %></li>
			<li>电话号码:&nbsp<%= u.getTelPhone() %></li>
			<li>邮箱:&nbsp<%= u.getEmail() %></li>
			<li>地址:&nbsp<%= u.getAddress() %></li>
			<li>注册时间:&nbsp<%= u.getAddTime() %></li>
			<li>工作状态:&nbsp<%= u.getWorkStatus() %></li>
		</ul>
	</div>

	
	<script src="js/jquery.js"></script>
	<script>
	</script>
</body>
</html>
