<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cyl.basic.*"%>
<%@ page language="java"
	import="java.util.*,com.cyl.work.*,com.cyl.admin.room.*,com.cyl.user.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idAdmin");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}
	List<User> listUid = UserService.getUserId();

	List<Room> list = RoomService.getLimitData(0, RoomService.getNum());
	//钟点类型
	List<ClockCatagory> listcc = ClockCatagoryService.getLimitData(0, ClockCatagoryService.getNum());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="icon-font/iconfont.css">
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
</head>


<body>

	<div>
		<a href="AdminOrder.jsp?idAdmin=<%=idUser%>" class="iconfont">&#xe624;</a>
	</div>

	<div class="d-display">
		<form action="AdminOrderDataSubmit.jsp?idAdmin=<%=idUser%>" method="post"
			onsubmit="return checkForm();">
			<table style="border-collapse:separate; border-spacing:0px 10px;">
			<caption><b style="font-size: 1.5em">订单提交</b></caption>
				<thead>
					<tr>
						<td>钟点类型</td>
						<td><select id="cc" name="clockcatagory">
								<%
									for (int i = 0; i < listcc.size(); i++) {
										ClockCatagory cc = listcc.get(i);
										if (cc.getName().equals("点钟")) {
								%>
								<option selected="selected" value="<%=cc.getName()%>"><%=cc.getName()%></option>
								<%
									} else {
								%>
								<option value="<%=cc.getName()%>"><%=cc.getName()%></option>
								<%
									}
								%>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr id="id">
						<td>牌号</td>
						<td style="width: 100px;"><select name="id" id="valueId">
								<%
									for (int i = 0; i < listUid.size(); i++) {
										User u = listUid.get(i);
										if(u.getId() != 0){
								%>
								<option value="<%=u.getId()%>"><%=u.getId()%></option>
								<%
									}
									}
								%>
						</select></td>
					</tr>

					<tr>
						<td>所在房间</td>
						<td style="width: 100px;"><select name="idRoom">
								<%
									for (int i = 0; i < list.size(); i++) {
										Room r = list.get(i);
								%>
								<option value="<%=r.getIdRoom()%>"><%="房间号：" + r.getIdRoom() + "  类型：" + r.getCategory()%></option>
								<%
									}
								%>
						</select></td>

					</tr>
					
					<tr>
						<td>当前时间</td>
						<td><%=new SimpleDateFormat("yyyy-MM-dd hh:MM").format(new Date())%></td>
					</tr>
					<tr>
						<td><input type="submit" value="提交"></td>
					</tr>
				</thead>
			</table>
		</form>
	</div>

	<script src="js/jquery.js"></script>
	<script>
	$(document).ready(function(){
		$('footer a').click(function() {
			$(this).addClass('on').siblings().removeClass('on');
		});
		
		$('#cc').change(function(){
			if($(this).val() == '排钟'){
				$("#id").hide(500);
			}else{
				$("#id").show(500);
			}		
		})
	})
	
	
	</script>
</body>
</html>
