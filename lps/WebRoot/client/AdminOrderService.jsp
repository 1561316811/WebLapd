<%@ page language="java" import="java.util.*,com.cyl.work.*,java.text.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idAdmin");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}
	
	List<ServerOrder> lists = ServerOrderService.getTodayOrderService();
	Collections.reverse(lists);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>今日订单</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/list.css">
<link rel="stylesheet" type="text/css" href="icon-font/iconfont.css">
</head>


<body>
	<div class="top">
		<a href="AdminOrder.jsp?idAdmin=<%=idUser%>" class="iconfont">&#xe624;&nbsp;&nbsp;<span>今日订单</span></a>
	</div>

	<div class="buttom">
		<div style="position:relative;left:15px; top:10px;font-size:16px;margin:0 0 20px 0;">
			<a href="AdminOrderAllToday.jsp?idAdmin=<%= idUser %>" 
			>全部订单</a> 
			<a href="AdminOrderGet.jsp?idAdmin=<%= idUser %>" 
			 >待接收</a> 
			<a href="AdminOrderService.jsp?idAdmin=<%= idUser %>" 
			style="color:blue;background:#CDC9C9;border-radius: 5px; ">服务中</a>
			<a href="AdminOrderPaying.jsp?idAdmin=<%= idUser %>">待支付</a>
			<a href="AdminOrderFinish.jsp?idAdmin=<%= idUser %>">已完成</a>
			<a href="AdminOrderInvalid.jsp?idAdmin=<%= idUser %>">已失效</a>
		</div>
		<%
			for (int i = 0; i < lists.size(); i++) {
				ServerOrder s = lists.get(i);
		%>
		<div class="list" style="background:#F2F2F2;border-radius:15px;">
			<div class="item">
				<a href="#">
					<h4 class="title"><%=s.getIdRoom()%>房&nbsp;&nbsp;<%= s.getId() %>号
					</h4>
					<p class="time"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s.getAddTime())%></p>
					<p class="sum income"><%=s.getClockCatagory()%>&nbsp;</p>
					<p
						style="padding-bottom: 5px;">
						<span style="color:	SpringGreen; float: left;"><%= s.getStatus() %></span>
					</p></a>
			</div>
			<hr />
		</div>
		<%
			}
		%>
	</div>
	<script src="js/jquery.js"></script>
	<script>
		$('footer a').click(function() {
			$(this).addClass('on').siblings().removeClass('on');
		});
	</script>
</body>
</html>
