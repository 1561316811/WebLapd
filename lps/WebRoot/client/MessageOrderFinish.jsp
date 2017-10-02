<%@ page language="java" pageEncoding="utf-8"
	import="com.cyl.work.*,com.cyl.user.*,java.util.*,java.text.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
		return ;
	}

	List<ServerOrder> lists = ServerOrderService.getInstance().getUserOrderByStatus(new User(idUser), 4, null);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>订单</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/list.css">
<link rel="stylesheet" type="text/css" href="icon-font/iconfont.css">
</head>





<body>
	<div class="top">
		<a href="MessageOrderPaying.jsp?idUser=<%=idUser%>" class="iconfont">&nbsp;&nbsp;&nbsp;<span>订单</span></a>
	</div>

	<div class="buttom">
		<div
			style="position:relative;left:15px; top:10px;font-size:20px;margin:0 0 20px 0;">
			<a href="Message.jsp?idUser=<%=idUser%>">待接收</a> <a
				href="MessageOrderGet.jsp?idUser=<%=idUser%>">已接收</a> <a
				href="MessageOrderPaying.jsp?idUser=<%=idUser%>"
				>待支付</a> <a
				href="MessageOrderFinish.jsp?idUser=<%=idUser%> " 
				style="color:blue;background:#CDC9C9;border-radius: 5px;">已完成</a> <a
				href="MessageOrderInvalid.jsp?idUser=<%=idUser%>">已失效</a>
		</div>
		<%
			for (int i = 0; i < lists.size(); i++) {
				ServerOrder s = lists.get(i);
		%>
		<div class="list" style="background:#F2F2F2;border-radius:15px;">
			<div class="item">
				<a
					href="MessageOrderPayingDetail.jsp?idUser=<%=idUser%>&idOrder=<%=s.getIdOrder()%>"><h4
						class="title"><%=s.getIdRoom()%>房&nbsp;&nbsp;
					</h4>
					<p class="time"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s.getAddTime())%></p>
					<p class="sum income"><%=s.getClockCatagory()%></p>
					<p
						style="color: red;font-size: 1.5em;float:right;padding-right: 5px;padding-bottom: 5px;"><%=s.getPay()%>￥
					</p></a>
			</div>
			<hr />
		</div>
		<%
			}
		%>
	</div>
	<div style="height: 1rem;"></div>
	<footer>
		<a href="Index.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe60b;</i>首页</a>
		<a href="Message.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe75f;<img
				id="msg" src="image/redpoint.png"></img></i>消息</a> <a
			href="Order.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe671;</i>订单</a>
		<a href="Center.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe617;</i>我的</a>
	</footer>
	<script src="js/jquery.js"></script>
	<script>
		$('footer a').click(function() {
			$(this).addClass('on').siblings().removeClass('on');
		});
	</script>
</body>
</html>
