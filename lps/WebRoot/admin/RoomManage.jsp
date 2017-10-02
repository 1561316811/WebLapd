<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	// 	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
	// 		response.sendRedirect("AdminLogIn.jsp");
	// 	}
	// 	response.sendRedirect("RoomDataLoad.jsp");
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>仓库管理</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/index.css" />
<script src="js/jquery.min.js"></script>
<!-- 动态菜单JS -->
<!-- <script type="text/javascript" src="js/menu.js"></script> -->
<script type="text/javascript" src="js/table.js"></script>
<script type="text/javascript" src="js/tablemanage.js"></script>

<style type="text/css">
.error {
	border: 2px;
	border-color: red;
}
</style>



</head>

<body>

	<div class="l-tab-content" style="background:#fff;">
		<div class="tab-content-item">
			<div class="home">
				<!--仓库管理  开始-->
				<div class="rightinfo">
					<div class="tools">
						<ul class="toolbar">
							<li class="click" id="l-room-manage">
							<a href="RoomDataManage.jsp?idAdmin=<%=idAdmin%>&isMngO=1" > <span> <img
										src="img/xg.png"></span> 管理房间
							</a></li>

							<li class="click" id="l-cata-manage"><a
								href="CatagoryDataManage.jsp?idAdmin=<%=idAdmin%>&isMngO=1"> <span>
										<img src="img/xg.png">
								</span> 管理类别
							</a></li>

						</ul>
					</div>
				</div>
				<!-- 类别添加结束 -->

				<!-- 房间添加开始 -->
				<div class="yg-tab" id="d-add-cg">
					<div class="yg-gl">
						<div class="khgl-rg " style="margin-bottom:15px;">
							<a href="#" class="xz">新增</a> <a href="#" class="ui-bill">禁用</a>
							<a href="#" class="ui-bill">启用</a> <a href="#" class="ui-bill">删除</a>
						</div>
						<div class="yg-tab">
							<div class="wrap-li">
								<table class="gridbar" border="0">
									<thead>
										<tr>
											<th><input type="checkbox"></th>
											<th>图</th>
											<th>操作</th>
											<th>编号</th>
											<th>房间号码</th>
											<th>房间类别</th>
											<th>房间楼层</th>
											<th>可容纳客人数</th>
											<th>备注</th>
											<th>状态</th>

										</tr>
										<tr>
											<td><input type="checkbox"></td>
											<td><img class="check-pic" src="img/pic1.png"></td>
											<td><a href="#"><img src="img/xg1.png"></a> <a
												href="#"><img src="img/dele.png"></a></td>
											<td>零售客户</td>
											<td>普通</td>
											<td>21062564789</td>
											<td>北京中智伟业有限公司</td>
											<td>谭科</td>
											<td>谭科</td>
											<td><img src="img/hd.png"></td>
										</tr>
										<tr>
											<td><input type="checkbox"></td>
											<td><img class="check-pic" src="img/pic1.png"></td>
											<td><a href="#"><img src="img/xg1.png"></a> <a
												href="#"><img src="img/dele.png"></a></td>
											<td>零售客户</td>
											<td>普通</td>
											<td>21062564789</td>
											<td>北京中智伟业有限公司</td>
											<td>谭科</td>
											<td>谭科</td>
											<td><img src="img/hd.png"></td>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="tab-btn">
							<div class="cellspacing" style="margin-top: 12px;">
								<div class="cell-zd">
									<p>
										制单人：<span><%=idAdmin%></span>
									</p>
								</div>
								<div class="cell-je">
									<a id="a-add-line" class="bc">添加一行</a> <a href="#" class="bc">操作日志</a>
									<a id="a-submit" class="bc">保存</a> <a href="#" class="bc">打印</a>
									<a id="a-close" class="bc">关闭</a>
								</div>
							</div>
						</div>
					</div>
					<div class="grid">
						<div class="layoutitem" style="width:100%;border:none;">
							<h2 style="margin: 10px 0px;">在此添加房间</h2>
							<form method="post" id="f-room">

								<table class="gridbar bar" border="1">
									<!-- 											<thead> -->
									<tr>
										<th>房间号码</th>
										<th>房间类别</th>
										<th>房间楼层</th>
										<th>可容纳客人数</th>
										<th>备注</th>
									</tr>
									<tr>
										<td><input id="idRoom" name="idRoom" type="text"
											class="cj-bf"></td>
										<td><input id="catagory" name="catagory" type="text"
											class="cj-bf"></td>
										<td><input id="floor" name="floor" type="text"
											class="cj-bf"></td>
										<td><input id="size" name="size" type="text"
											class="cj-bf"></td>
										<td><input id="remark" name="remark" type="text"
											class="cj-bf"></td>
									</tr>

									<!-- 											</thead> -->
								</table>
								<div class="cellspacing" style="margin-top: 12px;">
									<div class="cell-je">
										<a id="a-submit" class="bc">添加</a> <a id="a-close" class="bc">关闭</a>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- 						</div> -->
				</div>
				<!-- 房间添加结束 -->

				<div class="tools">
					<ul class="toolbar-c">
						<li class="click"><a href="tc-ck.html"> <span></span> 全部
						</a></li>
					</ul>
				</div>
				<ul class="imglist">
					<!-- 	<li><span><a href="#"><img src="img/kf01.png"></a></span>
							<h2>总库房</h2>
							<p>
								<a href="tc-ck-xq.html">查看</a>
							</p></li> -->

					<li><span><a href="tc-ck.html"><img
								src="img/kf-add.png"></a></span>
						<h2>添加仓库</h2></li>
				</ul>
			</div>
			<!--仓库管理  结束-->
			<!--弹窗 仓库管理开始-->

		</div>
	</div>
	</div>
	<script type="text/javascript">
	
		$(document).ready(function() {
	
			$("a-del-cg").click(function() {})
	
			//新增按钮
			$("#a-add-cg").click(function() {
				$('#in-c-msg')[0].focus(); //获取焦点
				$('#in-c-msg').hide("fast", function() {
					$('#in-c-msg').show("fast");
				});
			})
	
	
			//管理类别按钮事件
			<%-- $("#l-cata-manage").click(function() {
				var item = $("#con-header");
				if (item.is(":hidden")) {
					$.ajax({
						cache : true,
						type : "POST",
						url : "CatagoryDataSend.jsp?idAdmin=<%=idUser%>",
						data : $('#in-c-msg').serialize(), // 你的formid
						async : false,
						error : function(request) {
							alert("连接失败，请稍后重试");
						},
						success : function(data) {
							alert($(data).text());
							$('#in-c-msg').val(""); //输入框中的值设为空
							$('#in-c-msg')[0].focus(); //获取焦点
						}
					})
				}
	
			}) --%>
			$("#l-cata-manage").click(function() {
				var item = $("#con-header");
				if (item.is(":hidden")) {
					item.animate({
						height : 'show'
					}, "fast");
				} else {
					item.animate({
						height : 'hide'
					}, "fast");
				}
			});
	
	
			//确认提交类别按钮事件
			$(document).keyup(function(e) {
				if ($("#in-c-msg").is(":focus") && e.keyCode == 13) {
					comfirmClick();
				}
			})
	
			$("#l-c-confirm").click(comfirmClick);
	
			//确定添加类别按钮的事件
			function comfirmClick() {
				if ($('#in-c-msg').text != "") {
					$.ajax({
						cache : true,
						type : "POST",
						url : "CatagoryDataLoad.jsp?idAdmin=<%=idAdmin%>",
						data : $('#in-c-msg').serialize(), // 你的formid
						async : false,
						error : function(request) {
							alert("连接失败，请稍后重试");
						},
						success : function(data) {
							// 							showTips(data, $('#in-c-msg'), 2);
							alert($(data).text());
							$('#in-c-msg').val(""); //输入框中的值设为空
							$('#in-c-msg')[0].focus(); //获取焦点
						}
					})
				}
	
			}
	
			//取消添加类别按钮
			$("#l-c-cancel").click(function() {
				$(this).parents("#con-header").animate({
					height : 'hide'
				}, "fast");
			})
	
	
			//提交表单
			$("div #a-submit").click(function() {
	
				$.ajax({
					cache : true,
					type : "POST",
					url : "RoomDataLoad.jsp?idAdmin=<%=idAdmin%>",
					data : $('#f-room').serialize(), // 你的formid
					async : false,
					error : function(request) {
						alert("连接失败，请稍后重试");
					},
					success : function(data) {
						alert($(data).text());
					}
				})
			//    alert("2");
			})
	
		})
	</script>
</body>
</html>
