<%@page import="com.cyl.basic.RoomCatagoryService"%>
<%@page import="com.cyl.basic.RoomCatagory"%>
<%@ page language="java" import="java.util.*,com.cyl.sql.*,java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*"%>

<%
	request.setCharacterEncoding("utf-8");

	String idAdmin = request.getParameter("idAdmin");

	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}

	boolean isOpen = false;

	String isMngO = request.getParameter("isMngO");

	if (isMngO == null || isMngO.equals("0")) {
		isOpen = false;
	} else {
		isOpen = true;
	}

	int size = 10; //页面数据条数

	int pageNum; //当前页面序号

	int msgNum; //信息总数量

	String strStart = request.getParameter("pageNum");

	int startNum; //开始的序号

	int sumPage; //总共页数

	if (strStart == null || strStart.equals("")) {
		pageNum = 1;
	} else {
		pageNum = Integer.parseInt(strStart);
	}

	msgNum = RoomCatagoryService.getInstance().getNum();

	sumPage = msgNum / size + 1;

	if (pageNum > sumPage) {
		pageNum = sumPage;
	} else if (pageNum < 1) {
		pageNum = 1;
	}

	startNum = (pageNum - 1) * size;

	// 	pageNum = startNum / size + 1; //当前页面数

	List<RoomCatagory> cataList = RoomCatagoryService.getInstance().getLimitData(startNum, size);

	// 	System.out.println(msgNum);
	// 	isOpen = request.getParameter("isMngO");
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
							<a href="RoomDataManage.jsp?idAdmin=<%=idAdmin%>"> <span> <img
										src="img/xg.png"></span> 管理房间
							</a></li>

							<li class="click" id="l-cata-manage"><a> <span> <img
										src="img/xg.png"></span> 管理类别
							</a></li>

							<li class="click" id="refresh"><a> <span> <img
										src="img/xg.png"></span> 刷新
							</a></li>

						</ul>
					</div>
					<!-- 					<div  style="display: none"> -->
					<!-- 客房管理开始 -->

					<!-- 客房管理结束 -->


					<!-- 类别管理开始 -->
					<div class="con-header"
						style="margin: 10px 0px;
						background-color:#e9f1ee; 
						padding: 13px; 
						border-radius:3px;
						border-width:5px; 
						border-color: #000000;"
						id="con-header">

						<div class="yg-gl">
							<div class="d-margin-tb">
								<h1>管理类别</h1>
							</div>

							<form
								action="CatagoryDataDelMutiply.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum%>"
								method="post">
								<div class="yg-tab"
									style="margin-left: 20px;position: relative;">
									<div class="khgl-rg"
										style="margin-bottom:15px; float: left;clear: both;">
										<a class="xz" id="a-add-cg">新增</a> <a class="ui-bill"
										
											id="a-del-cg"> <input type="submit"
											style="list-style: none; 
										width: 100%;
										height: 100%;"
											value="删除" /></a>
									</div>
									<div class="wrap-li">
										<table id="t-cg-mg" class="gridbar" style="width: 50%;"
											border="0">
											<thead>

												<tr>
													<th width="30px"><input class="in-cb-manage"
														type="checkbox"></th>
													<th width="60px">操作</th>
													<th width="120px">序号</th>
													<th width="240px">类别</th>
												</tr>

												<%
													for (int i = 0; i < cataList.size(); i++) {
												%>
												<tr>
													<td><input name="name" type="checkbox"
														value=<%=cataList.get(i).getName()%>></td>
													<td><a class="a-del"
														href="CatagoryDataDel.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum%>&name=<%=cataList.get(i).getName()%>"
														onclick="return aConfirm()"><img src="img/dele.png"></a></td>
													<td><%=i + 1%></td>
													<td class="modify"><%=cataList.get(i).getName()%></td>
												</tr>
												<%
													}
												%>

											</thead>
										</table>

										<div class="pagin">
											<div class="message">
												共<i class="blue"><%=msgNum%></i>条记录，当前显示第&nbsp;<i
													class="blue"><%=pageNum%>&nbsp;</i>页
											</div>
											<ul class="paginList">
												<li class="paginItem"><a
													href="CatagoryDataSend.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum - 1%>"><span
														class="pagepre"><img src="img/jt1.png"></span></a></li>
												<%
													int curSumPagenum = pageNum / size;
													curSumPagenum *= size;
												%>
												<%
													for (int i = curSumPagenum; i < sumPage; i++) {
												%>
												<li class="paginItem"><a
													href="CatagoryDataManage.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=i + 1%>"><%=i + 1%></a></li>
												<%
													}
												%>
												<li class="paginItem"><a
													href="CatagoryDataManage.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum + 1%>"><span
														class="pagenxt"><img src="img/jt.png"></span></a></li>
											</ul>
										</div>

									</div>
								</div>
							</form>
						</div>


						<div class="d-margin-tb">
							<h2 style="width: auto;">请添加类别：</h2>
							<ul class="ui-inline">
								<li><input name="name" type="text" id="in-c-msg"
									class="ui-kh" placeholder="请输入类别名称"></li>
								<li><a id="l-c-confirm" class="ui-btn11 ui-btn-search ">添加</a></li>
								<li><a id="l-c-cancel"
									class="ui-btn11 ui-btn-search cancel ">关闭</a></li>
							</ul>
						</div>
					</div>
					<!-- 类别管理结束 -->

				</div>
				<!-- 类别添加结束 -->

				<%-- 	<!-- 房间添加开始 -->
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
				<!-- 房间添加结束 --> --%>

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
	
	//判断打开还是关闭类别管理
		$(function(){ 
			var item = $("#d-add-cg");
			
			if(<%=isOpen%>){
				item.animate({
					height : 'show'
				}, "fast");
			}else{
				item.animate({
					height : 'hide'
				}, "fast");
			}
			
		})
	
		$(document).ready(function() {
	
			$("a-del-cg").click(function() {
				
				$(this).parents("table").find(":checkbox")
			
			})
	
			//新增按钮
			$("#a-add-cg").click(function() {
				$('#in-c-msg')[0].focus(); //获取焦点
				$('#in-c-msg').hide("fast", function() {
					$('#in-c-msg').show("fast");
				});
			})
	
			$("#l-cata-manage").click(function() {
				var item = $("#con-header");
				if (item.is(":hidden")) {
					item.animate({
						height : 'show'
					}, "fast");
					<%isOpen = true;%>
				} else {
					item.animate({
						height : 'hide'
					}, "fast");
					<%isOpen = false;%>
				}
			});
	
			$("#refresh").click(function(){
				location.reload();
			})
	
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
						data : $('#in-c-msg').serialize(), //你的formid
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
	
			$("#l-room-manage").click(function() {
				var item = $("#d-add-cg");
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
			
			
			$(".modify").dblclick(function(){
				var text = $(this).text();
				var input = $("<input value='" + text + "'></input>");
				$(this).empty();
				$(this).append(input)
				
				$(input).blur(function(){
					var textInput = $(this).val();
					if(textInput == ""){
						modify(this, text, text);
					}else{
						modify(this, text, textInput);
					}
					$(this).after(textInput);
					$(this).remove();
				});
			})
			
			function modify(obj, oldv, newv){
				$.ajax({
					cache : true,
					type : "POST",
					url : "CatagoryDataModify.jsp?idAdmin=<%=idAdmin%>&oldName="+oldv+"&newName="+newv,
					data : $(obj).serialize(), // 你的formid
					async : false,
					error : function(request) {
						alert("连接失败，请稍后重试");
					},
					success : function(data) {
						alert($(data).text());
					}
				})
				
			}
			
		})
		
		function aConfirm(){
			if(confirm('确定要删除吗?')) 
		    { 
		        return true; 
		    } 
		    return false; 
		}
		
	</script>
</body>
</html>
