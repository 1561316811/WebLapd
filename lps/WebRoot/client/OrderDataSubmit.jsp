<%@page import="com.cyl.user.UserService"%>
<%@ page language="java" import="java.util.*,com.cyl.work.*"
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
	
	ServerOrder item = new ServerOrder();
	item.setId(UserService.getIdByUserName(idUser));
	item.setIdRoom(idRoom);
	item.setIdOrder(ServerOrderService.createId(item.getId(), item.getIdRoom()));
	item.setClockCatagory(clockCatagory);
	item.setStatus("待系统确定");
	item.setIdUser(idUser);
	
	boolean issuc = ServerOrderService.load(item);
	

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
