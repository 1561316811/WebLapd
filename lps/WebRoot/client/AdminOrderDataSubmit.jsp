10<%@page import="com.cyl.user.UserService"%>
<%@ page language="java" import="java.util.*,com.cyl.work.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idAdmin");
	
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}
	
// System.out.println("request.getParameter(id) : " + request.getParameter("id"));
	String idStr = request.getParameter("id");
	int id = 0;
System.out.println("idStr : " + idStr);
	if(idStr == null){
// System.out.println( request.getContextPath() + "idStr = 0;  id = " + id);
		id = UserService.getIdByWorkRank(idUser);
	}else{
		id = Integer.parseInt(request.getParameter("id"));  //牌号
	}
// System.out.println( request.getContextPath() + " id = " + id);
	String idRoom = request.getParameter("idRoom");  //房间号
	String clockCatagory = request.getParameter("clockcatagory");  //钟点类型
	
	ServerOrder item = new ServerOrder();
	item.setIdUser(UserService.getUserNameById(id));
	item.setIdRoom(idRoom);
	item.setIdOrder(ServerOrderService.createId(item.getId(), item.getIdRoom()));
	item.setClockCatagory(clockCatagory);
	item.setStatus(OrderStatus.getStatus("os1"));
	item.setId(id);
	
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
		<p>提交成功<p>
	<%}else{ %>
		<p>提交失败</p>
	<%} %>
	<a href="AdminOrder.jsp?idAdmin=<%= idUser %>">点击这里返回</a>
	</div>
</body>
</html>
