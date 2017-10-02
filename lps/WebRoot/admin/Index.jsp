<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idAdmin = request.getParameter("idAdmin");
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/index.css" />
<script src="js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript" src="js/menu.js"></script>
</head>

<body>
	<div class="container">
		<div class="cont-top">
			<div class="companyname">首耀照明</div>
			<div class="cont-top-middle">
				<ul>
					<li><a href="#">
							<div class="top-middle">
								<img src="img/icon.png">
							</div>
							<div class="top-txt">购物单</div>
					</a></li>

					<li><a href="#">
							<div class="top-middle">
								<img src="img/icon1.png">
							</div>
							<div class="top-txt">收款单</div>
					</a></li>

					<li><a href="#">
							<div class="top-middle">
								<img src="img/icon1.png">
							</div>
							<div class="top-txt">收款单</div>
					</a></li>

					<li><a href="#">
							<div class="top-middle">
								<img src="img/icon1.png">
							</div>
							<div class="top-txt">收款单</div>
					</a></li>

					<li><a href="#">
							<div class="top-middle">
								<img src="img/icon1.png">
							</div>
							<div class="top-txt">收款单</div>
					</a></li>

					<li><a href="#">
							<div class="top-middle">
								<img src="img/icon1.png">
							</div>
							<div class="top-txt">收款单</div>
					</a></li>

				</ul>
			</div>
			<div class="cont-top-rg">
				<ul class="advanced-menu">
					<li>
						<div class="top-search">
							<div class="t-s-l">
								<span class="left-line"></span> <input type="text" value="搜库存"
									class="search-type" disabled> <span
									class="triangle-bottom"></span>
							</div>
							<div class="t-s-l">
								<input type="text" placeholder="请输入商品编码或名称" class="search-dt"
									autocomplete="off"> <span class="right-line"></span> <span
									class="right-icon"><img src="img/search-icon.png"></span>
							</div>
						</div>
					</li>
					<li class="default" style="position:relative;"><span
						class="user"><img src="img/user.png"></span> <a href="#"
						class="special">小丫头 <i><img src="img/xl.png"></i></a>
						<div class="drop-down-wrap"
							style="width:180px;left:15px;display: none;">
							<div class="drop-down">
								<span class="triangle-border"></span> <span class="triangle-bg"></span>
								<ul class="del-ul">
									<li><a href="#" class="hot">首耀照明</a></li>
									<li><a href="#">小丫头</a></li>
									<li><a href="#">操作日志</a></li>
								</ul>
							</div>
						</div></li>
				</ul>
				<ul class="advanced-menu">
					<li><a href="#"><img src="img/top-icon1.png"></a></li>
					<li><a href="#"><img src="img/top-icon2.png"></a></li>
					<li><a href="#"><img src="img/top-icon3.png"></a></li>
					<li><a href="#"><img src="img/top-icon4.png"></a></li>
					<li><a href="#"><img src="img/top-icon5.png"></a></li>
				</ul>
			</div>
		</div>
		<div class="left-menu" style="height:949px;">
			<div class="menu-list">
				<ul>
					<li class="menu-list-01" id="l-room">
						<div>
							<p class="fumenu" >客房管理</p>
							<input type="hidden" value="RoomManage.jsp?idAdmin=<%=idAdmin%>" />
							<img class="xiala" src="img/xiala.png" />
						</div>
						<div class="list-p">
							<p class="zcd" id="zcd5">所有客房</p>
							<p class="zcd" id="zcd6">工作客房</p>
							<p class="zcd" id="zcd7">空闲客房</p>
						</div>
					</li>

					<li class="menu-list-02" id="l-product">
						<div class="transform">
							<p class="fumenu">产品管理</p>
							<input type="hidden"
								value="ProductManage.jsp?idAdmin=<%=idAdmin%>" /> <img
								class="xiala" src="img/xiala.png" />
						</div>
						<div class="list-p">
							<p class="zcd" id="zcd5">产品库存</p>
							<p class="zcd" id="zcd6">产品成本</p>
							<p class="zcd" id="zcd7">产品属性管理</p>
						</div>
					</li>

					<li class="menu-list-01" id = "l-order"><div>
							<p class="fumenu">订单管理</p>
							<input type="hidden" value="OrderManage.jsp?idAdmin=<%=idAdmin%>" />
							<img class="xiala" src="img/xiala.png" />
						</div>
						<div class="list-p">
							<p class="zcd" id="zcd8">下单账户</p>
							<p class="zcd" id="zcd9">所属员工</p>
							<p class="zcd" id="zcd10">订单成本</p>
							<p class="zcd" id="zcd1">订单成本费用</p>
							<p class="zcd" id="zcd12">订单总支付金额</p>
							<p class="zcd" id="zcd3">订单商品</p>
							<p class="zcd" id="zcd14">手动增加订单</p>
						</div></li>

					<li class="menu-list-02" id="l-finance"><div>
							<p class="fumenu">财务管理</p>
							<input type="hidden" value="FinaceManage.jsp?idAdmin=<%=idAdmin%>" />
							<img class="xiala" src="img/xiala.png" />
						</div>
						<div class="list-p">
							<p class="zcd" id="zcd15">记录出款和入款</p>
							<p class="zcd" id="zcd16">计算一段时间的利润</p>
						</div></li>

					<li class="menu-list-01" id="l-custom"><div>
							<p class="fumenu">客户管理</p>
							<input type="hidden" value="CustomManage.jsp?idAdmin=<%=idAdmin%>" />
							<img class="xiala" src="img/xiala.png" />
						</div>
						<div class="list-p">
							<p class="zcd" id="zcd17">客户基本信息</p>
							<p class="zcd" id="zcd18">客户VIP登记管理</p>
							<p class="zcd" id="zcd19">客户信息管理</p>
							<p class="zcd" id="zcd20">客户订单管理</p>
							<p class="zcd" id="zcd21">转移客户</p>
						</div></li>

					<li class="menu-list-02" id="l-stuff"><div>
							<p class="fumenu">员工管理</p>
							<input type="hidden" value="StuffDataManage.jsp?idAdmin=<%=idAdmin%>" />
							<img class="xiala" src="img/xiala.png" />
						</div>
						<div class="list-p">
							<p class="zcd" id="zcd22">员工基本信息</p>
							<p class="zcd" id="zcd23">员工权限</p>
							<p class="zcd" id="zcd24">开通新客户</p>
							<p class="zcd" id="zcd25">销售额管理</p>
						</div></li>
				</ul>
			</div>
		</div>
		<div class="right-menu">
			<div class="main-hd">
				<div class="page-teb" style="height:887px;">
					<div class="l-tab-links">
						<ul style="left:0;" id="ul-headControl" >
							<li class="l-select" id="l-index" style = "background-color:#000000"><a >首页&nbsp&nbsp&nbsp</a>
							</li>
<!-- 							<li class="l-select" id="l-roomr"><a>客房管理</a> <span class="close"></span></li> -->
						</ul>
					</div>
					<div id="d-iframe" style="width: 100%;height: 100%">
						<iframe src="IndexPage.jsp" id="l-index-v"> </iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
