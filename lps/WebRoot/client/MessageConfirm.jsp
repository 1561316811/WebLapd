<%@page import="com.cyl.basic.*"%>
<%@ page language="java"
	import="java.util.*,com.cyl.work.*,com.cyl.admin.room.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	String idOrder = request.getParameter("idOrder");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
	}

	ServerOrder s = ServerOrderService.getOrderByIdOrder(idOrder);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/list.css">
<link rel="stylesheet" type="text/css" href="icon-font/iconfont.css">
</head>


<body>

	<div class="top">
		<a href="Message.jsp?idUser=<%=idUser%>" class="iconfont">&#xe624;&nbsp;&nbsp;<span>订单详情</span></a>
	</div>

	<div class="buttom">
		<div class="d-display">
			<table>
				<tr>
					<td>房间号码</td>
					<td><%=s.getIdRoom()%></td>
				</tr>
				<tr>
					<td>订单类型</td>
					<td><%=s.getClockCatagory()%></td>
				</tr>
				<tr>
					<td>订单编号</td>
					<td><%=s.getIdOrder()%></td>
				</tr>
				<tr>
					<td>发起时间</td>
					<td><%=s.getAddTime()%></td>
				</tr>
				<tr>
					<td><a class="confirm" onclick="return confirm('确认接受订单');"
					 href="MessageReceive.jsp?idUser=<%=idUser%>&idOrder=<%=idOrder%>">接受订单</a></td>
					<td><a href="MessageRefuse.jsp?idUser=<%=idUser%>&idOrder=<%=idOrder%>" onclick="return confirm('确认拒绝订单');">拒绝订单</a></td>
				</tr>
			</table>

		</div>
	</div>
	<script src="js/jquery.js"></script>
	<script>
		$('footer a').click(function() {
			$(this).addClass('on').siblings().removeClass('on');
		});
		
	</script>
</body>
</html>
