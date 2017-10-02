<%@page import="com.cyl.admin.catagory.CatagoryService"%>
<%@page import="com.cyl.admin.catagory.Catagory"%>
<%@page import="com.cyl.admin.room.*"%>
<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.user.*"%>

<%
	request.setCharacterEncoding("utf-8");

	String idAdmin = request.getParameter("idAdmin");

	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}

	boolean isOpen = false;

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

	msgNum = UserService.getNum();

	sumPage = msgNum / size + 1;

	if (pageNum > sumPage) {
		pageNum = sumPage;
	} else if (pageNum < 1) {
		pageNum = 1;
	}

	startNum = (pageNum - 1) * size;

	// 	pageNum = startNum / size + 1; //当前页面数

	List<User> list = UserService.getLimitData(startNum, size);
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

				<!-- 员工管理开始 -->
				<div class="yg-tab"
					style="margin: 10px 0px;
						background-color:#e9f1ee; 
						padding: 13px; 
						border-radius:3px;
						border-width:5px; 
						border-color: #000000;"
					id="con-header">
					<div class="d-margin-tb">
						<h1>管理员工</h1>
					</div>

					<form
						action="StuffDataDelMutiply.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum%>"
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
												<th>工作编号</th>
												<th>账号</th>
												<th>密码</th>
												<th>生日</th>
												<th>电话号码</th>
												<th>邮箱</th>
												<th>地址</th>
												<th>密保问题</th>
												<th>密保答案</th>
												<th>工作状态</th>
												<th>注册时间</th>
											</tr>

											<%
												for (int i = 0; i < list.size(); i++) {
													User u = list.get(i);
											%>

											<tr>
												<td><input name="idUser" value="<%=u.getIdUser()%>"
													type="checkbox"></td>
												<td><a class="a-del"
													href="StuffDataDel.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum%>&idUser=<%=u.getIdUser()%>"
													onclick="return aConfirm()"><img src="img/dele.png"></a></td>
												<td><%=i + 1%></td>
												<td><%=u.getId()%></td>
												<td><%=u.getIdUser()%></td>
												<td><%=u.getPassword()%></td>
												<td><%=u.getBirthday()%></td>
												<td><%=u.getTelPhone()%>
												<td><%=u.getEmail()%></td>
												<td><%=u.getAddress()%></td>
												<td><%=u.getQuestion()%></td>
												<td><%=u.getAnswer()%></td>
												<td><%=u.getWorkStatus()%></td>
												<td><%=u.getAddTime()%></td>
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
										for (int i = curSumPagenum; i < sumPage; i++) {
									%>
									<li class="paginItem"><a
										href="StuffDataManage.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=i + 1%>"><%=i + 1%></a></li> 
									<%
										}
									%>
									<li class="paginItem"><a
										href="StuffDataManage.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum + 1%>"><span
											class="pagenxt"><img src="img/jt.png"></span></a></li>
								</ul>
							</div>
						</div>
					</form>
				</div>
				<!-- 员工管理结束 -->
				
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
	
			function comfirmClick() {
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
	
		function aConfirm() {
			if (confirm('确定要删除吗?')) {
				return true;
			}
			return false;
		}
	</script>
</body>
</html>
