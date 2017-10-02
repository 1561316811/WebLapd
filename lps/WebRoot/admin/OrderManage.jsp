<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idAdmin");
	// 	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
	// 		response.sendRedirect("AdminLogIn.jsp");
	// 	}
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单管理</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/index.css" />
<script src="js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<script type="text/javascript" src="js/menu.js"></script>
</head>

<body>

	<div class="l-tab-content" style="background:#fff;">
		<div class="tab-content-item">
			<div class="home">
				<!--订单管理   开始-->
				<div class="ddgl-box">
					<div class="ddgl-list">
						<div class="dd-fl">进货订单管理</div>
						<div class="dd-rg">
							<div class="rg-01">
								过滤 <select class="dd-sel">
									<option>显示全部</option>
									<option>未审核订单</option>
									<option>审核通过订单</option>
								</select>
							</div>
							<div class="rg-01">
								<select class="dd-sel">
									<option>显示全部</option>
									<option>未审核订单</option>
									<option>审核通过订单</option>
								</select>
							</div>
						</div>
					</div>
					<div class="dd-list-top">
						<div class="dd-cart">
							<div class="column t-checkbox">
								<div class="cart-checkbox">
									<input type="checkbox" class="jdcheckbox"> 全选
								</div>

							</div>
							<div class="column t-goods">商品</div>
							<div class="column tbh">订单编号</div>
							<div class="column t-props"></div>
							<div class="column t-price">单价</div>
							<div class="column t-quantity">数量</div>
							<div class="column t-sum">小计</div>
							<div class="column t-quantity">状态</div>
							<div class="column t-action">操作</div>
							<div class="column t-quantity">备注</div>
						</div>
						<div class="cart-list">
							<div class="cart-item-list">
								<div class="cart-tbody">
									<div class="shop">
										<div class="cart-checkbox">
											<input type="checkbox" class="jdcheckbox">
										</div>
										<span class="shop-title"> <a href="#" class="shop-name">首耀照明旗舰店</a>
										</span>
										<div class="shop-extra">已免运费</div>
									</div>
								</div>

								<div class="item-list">
									<div class="tiem-last">
										<!-- 增加列表数量-->
										<div class="item-form">
											<div class="cell t-checkbox">
												<div class="cart-checkbox">
													<input type="checkbox" class="jdcheckbox">
												</div>
											</div>

											<div class="cell p-goods">
												<div class="goods-item">
													<div class="p-img">
														<a href="#" class="j-aa"><img src="img/kf02.png"></a>
													</div>
													<div class="item-msg">
														<div class="p-name">
															<a href="#">产品介绍产品介绍产品介绍产品介绍产品介绍</a>
														</div>
													</div>
												</div>
											</div>
											<div class="cell tbh">2017052356251</div>
											<div class="cell t-price">￥256</div>
											<div class="cell t-quantity">×2</div>
											<div class="cell t-sum">￥256</div>
											<div class="cell t-quantity">交易完成</div>
											<div class="cell t-action">
												<a href="#"><img src="img/dele.png"></a> <a href="#"><img
													src="img/xg1.png"></a>
											</div>
											<div class="cell t-quantity">无</div>
										</div>
									</div>
								</div>
							</div>

							<!--第二个列表-->
							<div class="cart-item-list">
								<div class="cart-tbody">
									<div class="shop">
										<div class="cart-checkbox">
											<input type="checkbox" class="jdcheckbox">
										</div>
										<span class="shop-title"> <a href="#" class="shop-name">首耀照明旗舰店</a>
										</span>
										<div class="shop-extra">已免运费</div>
									</div>
								</div>

								<div class="item-list">
									<div class="tiem-last">
										<!-- 增加列表数量-->
										<div class="item-form">
											<div class="cell t-checkbox">
												<div class="cart-checkbox">
													<input type="checkbox" class="jdcheckbox">
												</div>
											</div>

											<div class="cell p-goods">
												<div class="goods-item">
													<div class="p-img">
														<a href="#" class="j-aa"><img src="img/kf02.png"></a>
													</div>
													<div class="item-msg">
														<div class="p-name">
															<a href="#">产品介绍产品介绍产品介绍产品介绍产品介绍</a>
														</div>
													</div>
												</div>
											</div>
											<div class="cell tbh">2017052356251</div>
											<div class="cell t-price">￥256</div>
											<div class="cell t-quantity">×2</div>
											<div class="cell t-sum">￥256</div>
											<div class="cell t-quantity">交易完成</div>
											<div class="cell t-action">
												<a href="#"><img src="img/dele.png"></a> <a href="#"><img
													src="img/xg1.png"></a>
											</div>
											<div class="cell t-quantity">无</div>
										</div>
										<div class="item-form">
											<div class="cell t-checkbox">
												<div class="cart-checkbox">
													<input type="checkbox" class="jdcheckbox">
												</div>
											</div>

											<div class="cell p-goods">
												<div class="goods-item">
													<div class="p-img">
														<a href="#" class="j-aa"><img src="img/kf02.png"></a>
													</div>
													<div class="item-msg">
														<div class="p-name">
															<a href="#">产品介绍产品介绍产品介绍产品介绍产品介绍</a>
														</div>
													</div>
												</div>
											</div>

											<div class="cell tbh">2017052356251</div>

											<div class="cell t-price">￥256</div>

											<div class="cell t-quantity">×2</div>

											<div class="cell t-sum">￥256</div>
											<div class="cell t-quantity">交易完成</div>
											<div class="cell t-action">
												<a href="#"><img src="img/dele.png"></a> <a href="#"><img
													src="img/xg1.png"></a>
											</div>
											<div class="cell t-quantity">无</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--订单管理   结束-->
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>

</body>
</html>
