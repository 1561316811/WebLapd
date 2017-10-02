<%@page import="com.cyl.user.*"%>
<%@ page language="java" pageEncoding="utf-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	User u = null;
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
// 	System.out.println(request.getRequestURI() + " : " + idUser);
		response.sendRedirect("UserLogIn.jsp");
		return;
	}
	u = UserService.getInstance().getUserByIdUser(idUser);
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
	<header>
		<div class="user">
			<i class="iconfont">&#xe623;</i>
		</div>

		<div class="userinfo">
			<p>
				牌号:&nbsp<em font-color="red"><%= u.getId() %></em>&nbsp号
			</p>
		</div>

	</header>

	<section>
		<a href="javascript:;"><i class="iconfont">&#xe60b;</i>待支付</a> <a
			href="javascript:;"><i class="iconfont"
			style="background: #e25b5b;">&#xe617;</i>代发货</a> <a href="javascript:;"><i
			class="iconfont" style="background: #62d07a;">&#xe671;</i>待收货</a> <a
			href="javascript:;"><i class="iconfont"
			style="background: #d6c46a;">&#xe717;</i>待评价</a> <a href="javascript:;"><i
			class="iconfont" style="background: #ca6abe;">&#xe75f;</i>待维权</a>
	</section>

	<aside>
		<ul>
			<li><a href="javscript:;" class="sp1"><i class="iconfont">&#xe60b;</i>全部订单<i
					class="iconfont" style="float: right;font-size: .3rem;">&#xe602;</i></a></li>
			<li style="margin-top: .2rem;"><a href="javscript:;" class="sp2"><i
					class="iconfont">&#xe617;</i>分销中心<i class="iconfont"
					style="float: right;font-size: .3rem;">&#xe602;</i></a></li>
			<li style="margin-top: .2rem;"><a href="javscript:;" class="sp3"><i
					class="iconfont">&#xe717;</i>我的优惠券<i class="iconfont"
					style="float: right;font-size: .3rem;">&#xe602;</i></a></li>
			<li><a href="javscript:;" class="sp4"><i class="iconfont">&#xe671;</i>我的红包<i
					class="iconfont" style="float: right;font-size: .3rem;">&#xe602;</i></a></li>
			<li><a href="javscript:;" class="sp5"><i class="iconfont">&#xe60b;</i>我的会员卡<i
					class="iconfont" style="float: right;font-size: .30rem;">&#xe602;</i></a></li>
			<li style="margin-top: .2rem;"><a href="UserData.jsp?idUser=<%= idUser %>" class="sp6">
				<i class="iconfont">&#xe717;</i>我的资料<i class="iconfont"
					style="float: right;font-size: .3rem;">&#xe602;</i></a></li>
			<li><a href="javscript:;" class="sp7"><i class="iconfont">&#xe60b;</i>我的收藏<i
					class="iconfont" style="float: right;font-size: .30rem;">&#xe602;</i></a></li>
			<li><a href="javscript:;" class="sp8"><i class="iconfont">&#xe75f;</i>我的购物车<i
					class="iconfont" style="float: right;font-size: .30rem;">&#xe602;</i></a></li>
		</ul>
	</aside>
	<div style="height: 1rem;"></div>
	<footer>
		<a href="Index.jsp?idUser=<%= idUser %>"><i class="iconfont">&#xe60b;</i>首页</a> <a
			href="Message.jsp?idUser=<%= idUser %>"><i class="iconfont">&#xe75f;<img id="msg" src="image/redpoint.png" ></img></i>消息</a> <a
			href="Order.jsp?idUser=<%= idUser %>"><i class="iconfont">&#xe671;</i>订单</a> <a
			href="Center.jsp?idUser=<%= idUser %>"><i class="iconfont">&#xe617;</i>我的</a>
	</footer>
	<script src="js/jquery.js"></script>
	<script>
		$('footer a').click(function(){
				$(this).addClass('on').siblings().removeClass('on');
			});
	</script>
</body>
</html>
