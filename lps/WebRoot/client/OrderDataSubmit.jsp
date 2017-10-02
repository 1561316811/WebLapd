<%@page import="com.cyl.basic.OrderStatusService"%>
<%@page import="com.cyl.basic.OrderStatus"%>
<%@page import="com.cyl.user.UserService"%>
<%@ page language="java" import="java.util.*,com.cyl.user.*,com.cyl.work.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
	}
	
	String idRoom = request.getParameter("idRoom");
	String clockCatagory = request.getParameter("clockcatagory");
	
	User u = UserService.getInstance().getUserByIdUser(idUser);
	ServerOrder so = new ServerOrder.Builder(ServerOrderService.getInstance().createId(u.getId(), idRoom))
	//员工编号
	.idUser(idUser)
	//订单的ID
	.id(u.getId())
	
	.idRoom(idRoom)
	.clockCatagory(clockCatagory)
	.status(OrderStatusService.getInstance().getOrderStatusByNum(1).getOrderStatus())
	//修改订单状态
	.status(OrderStatusService.getInstance().getOrderStatusByNum(1).getOrderStatus())
	//设置订单的开始时间
	.startTime(ServerOrderService.getCurTimestamp())
	.build();
	
	boolean issuc = ServerOrderService.getInstance().load(so);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
</head>
<body>
	<div  class="font-size" style="margin: 20px 0px 0px 20px;">
	<%if(issuc){ %>
		<p>保存成功<p>
	<%}else{ %>
		<p>保存失败</p>
	<%} %>
	<a href="Order.jsp?idUser=<%= idUser %>">点击这里返回</a>
	</div>
</body>
</html>
