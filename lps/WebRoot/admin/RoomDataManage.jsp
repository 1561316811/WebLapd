<%@page import="com.cyl.admin.catagory.CatagoryService"%>
<%@page import="com.cyl.admin.catagory.Catagory"%>
<%@page import="com.cyl.admin.room.*"%>
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

	msgNum = RoomService.getNum();

	sumPage = msgNum / size + 1;

	if (pageNum > sumPage) {
		pageNum = sumPage;
	} else if (pageNum < 1) {
		pageNum = 1;
	}

	startNum = (pageNum - 1) * size;

	// 	pageNum = startNum / size + 1; //当前页面数

	List<Room> roomList = RoomService.getLimitData(startNum, size);

	List<Catagory> cataList = CatagoryService.getLimitData(0, CatagoryService.getNum());

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
							<li class="click" id="l-room-manage"><a> <span> <img
										src="img/xg.png"></span> 管理房间
							</a></li>

							<li class="click" id="l-cata-manage"><a
								href="CatagoryDataManage.jsp?idAdmin=<%=idAdmin%>&isMngO=1">
									<span> <img src="img/xg.png">
								</span> 管理类别
							</a></li>

						</ul>
					</div>
				</div>
				<!-- 类别添加结束 -->

				<!-- 房间添加开始 -->
				<div class="yg-tab"
					style="margin: 10px 0px;
						background-color:#e9f1ee; 
						padding: 13px; 
						border-radius:3px;
						border-width:5px; 
						border-color: #000000;"
					id="con-header">
					<div class="d-margin-tb">
						<h1>管理房间</h1>
					</div>
					
					<form
						action="RoomDataDelMutiply.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum%>"
						method="post">
						<div class="yg-gl">

							<div class="khgl-rg"
								style="margin-bottom:15px; float: left;clear: both;">
								<a class="xz" id="a-add-rm">新增</a> <a class="ui-bill"
									id="a-del-cg"> <input type="submit"
									style="list-style: none; 
										width: 100%;
										height: 100%;"
									value="删除" /></a>
							</div>

							<div class="yg-tab">
								<div class="wrap-li">
									<table class="gridbar" border="0">
										<thead>
											<tr>

												<th><input type="checkbox" class="in-cb-manage"></th>
												<th>操作</th>
												<th>编号</th>
												<th>房间号码</th>
												<th>房间类别</th>
												<th>房间楼层</th>
												<th>可容纳客人数</th>
												<th>添加时间</th>
												<th>备注</th>

											</tr>

											<%
												for (int i = 0; i < roomList.size(); i++) {
													Room r = roomList.get(i);
											%>

											<tr>
												<td><input name="idRoom" value="<%=r.getIdRoom() %>" type="checkbox"></td>
												<td><a class="a-del"
													href="RoomDataDel.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum%>&idRoom=<%=r.getIdRoom()%>"
													onclick="return aConfirm()"><img src="img/dele.png"></a></td>
												<td><%=i + 1%></td>
												<td><%=r.getIdRoom()%></td>
												<td><%=r.getCategory()%></td>
												<td><%=r.getFloor()%></td>
												<td><%=r.getSize()%></td>
												<td><%=r.getAddTime() %>
												<td><%=r.getRemark()%></td>
											</tr>

											<%
												}
											%>

										</thead>
									</table>
								</div>
							</div>
							<div class="pagin">
								<div class="message">
									共<i class="blue"><%=msgNum%></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageNum%>&nbsp;</i>页
								</div>
								<ul class="paginList">
									<li class="paginItem"><a
										href="RoomDataSend.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum - 1%>"><span
											class="pagepre"><img src="img/jt1.png"></span></a></li>
									<%
										int curSumPagenum = pageNum / size;
										curSumPagenum *= size;
									%>
									<%
										for (int i = curSumPagenum; i < sumPage; i++){
									%>
									<li class="paginItem"><a
										href="RoomDataManage.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=i + 1%>"><%=i + 1%></a></li>
									<%
										}
									%>
									<li class="paginItem"><a
										href="RoomDataManage.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum + 1%>"><span
											class="pagenxt"><img src="img/jt.png"></span></a></li>
								</ul>
							</div>
						</div>
					</form>

					<div class="grid">
						<div class="layoutitem" style="width:100%;border:none;">
							<h2 style="margin: 10px 0px;">在此添加房间</h2>

							<form method="post" id="f-room">

								<table id="t-add-room" class="gridbar bar" border="1">
									<tr>
										<th>房间号码</th>
										<th>房间类别</th>
										<th>房间楼层</th>
										<th>可容纳客人数</th>
										<th>备注</th>
									</tr>
									<tr>
										<td><input class="i-enter" id="idRoom" name="idRoom" type="text"
											class="cj-bf"></td>
										<td><select  class="i-enter" name="catagory" 
											style="height:37px;width:100%; float: left;">
												<%
													for (int i = 0; i < cataList.size(); i++) {
												%>
												<option value="<%=cataList.get(i).getName()%>"><%=cataList.get(i).getName()%></option>
												<%
													}
												%>
										</select></td>
										<td><input class="i-enter" id="floor" name="floor" type="text"
											class="cj-bf"></td>
										<td><input class="i-enter"id="size" name="size" type="text"
											class="cj-bf"></td>
										<td><input class="i-enter"id="remark" name="remark" type="text"
											class="cj-bf"></td>
									</tr>
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
			$("#a-add-rm").click(function() {
				$('#idRoom')[0].focus(); //获取焦点
				$('#t-add-room').hide("fast", function() {
					$('#t-add-room').show("fast");
				});
			})
	
	
			//确认提交类别按钮事件
			$(document).keyup(function(e) {
// 			alert("key");
				if ($(".i-enter").is(":focus") && e.keyCode == 13) {
// 				alert("enter");
					comfirmClick();
				}
			})
			
			
			//取消添加类别按钮
			$("#l-c-cancel").click(function() {
				$(this).parents("#con-header").animate({
					height : 'hide'
				}, "fast");
			})
	
			$("#l-room-manage").click(function() {
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
	
			function comfirmClick(){
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
						location.reload();
					}
				})
			}
	
			//提交表单
			$("div #a-submit").click(function() {
				
				comfirmClick();
				
			})
			
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
