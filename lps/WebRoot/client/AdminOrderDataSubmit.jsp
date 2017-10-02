<%@page import="com.cyl.basic.OrderStatusService"%>
<%@page import="org.omg.PortableServer.IdUniquenessPolicy"%>
<%@page import="com.cyl.user.UserService"%>
<%@ page language="java" import="java.util.*,com.cyl.work.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*, com.cyl.user.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idAdmin");
	
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}
	
// System.out.println("request.getParameter(id) : " + request.getParameter("id"));
	String idUserName = request.getParameter("idUser");
	
// System.out.println("idUserName : " + idUserName);
	User u = null;
	if(idUserName != null)
		u = UserService.getInstance().getUserByIdUser(idUserName);
	else
		return;
	
	String idRoom = request.getParameter("idRoom");  //房间号
	String clockCatagory = request.getParameter("clockcatagory");  //钟点类型
	
	ServerOrderService sos = ServerOrderService.getInstance();
	
	ServerOrder item = new ServerOrder();
	item.setIdUser(u.getIdUser());
	item.setId(u.getId());
	item.setIdRoom(idRoom);
	item.setIdOrder(sos.createId(u.getId(), idRoom));
	
	item.setStatus(OrderStatusService.getInstance().getOrderStatusByNum(1).getOrderStatus());
	item.setClockCatagory(clockCatagory);
	item.setAddTime(ServerOrderService.getCurTimestamp());
	
	boolean issuc = sos.load(item);

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
