<%@ page language="java" import="java.util.*,com.cyl.work.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idAdmin");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
</head>


<body>

	<div class="wrap-li">
		<ul>
			<li><a href="AdminOrderSend.jsp?idAdmin=<%=idUser%>"
				style="border: 1px; font-size: 20px;">发起订单</a></li>
				<li><a href="AdminOrderAllToday.jsp?idAdmin=<%=idUser%>"
				style="border: 1px; font-size: 20px;">查看最近订单</a></li>
		</ul>
	</div>

	<div style="height: 1rem;"></div>
<!-- 	<footer> -->
<%-- 		<a href="Index.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe60b;</i>首页 --%>
<%-- 		</a> <a href="Message.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe75f;</i>消息</a> --%>
<%-- 		<a href="Order.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe671;</i>订单</a> --%>
<%-- 		<a href="Center.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe617;</i>我的</a> --%>
<!-- 	</footer> -->
	<script src="js/jquery.js"></script>
	<script>
		$('footer a').click(function() {
			$(this).addClass('on').siblings().removeClass('on');
		});
	</script>
</body>
</html>
