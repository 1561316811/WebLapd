<%@page import="com.cyl.basic.*"%>
<%@ page language="java"
	import="java.util.*,com.cyl.work.*,com.cyl.admin.room.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
	}

	List<Room> list = RoomService.getLimitData(0, RoomService.getNum());

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
		<a href="Order.jsp?idUser=<%=idUser%>" class="iconfont">&#xe624;</a>
	</div>

	<div class="d-display">
		<form action="OrderDataSubmit.jsp?idUser=<%=idUser%>" method="post"
			onsubmit="return checkForm();">
			<table style="border-collapse:separate; border-spacing:0px 10px;">
				<thead>

					<tr>
						<th>订单提交</th>
						<th></th>
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
						<td><select name="clockcatagory">
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

					<tr>
						<td><input type="submit" value="提交"></td>
					</tr>
				</thead>
			</table>
		</form>
	</div>

	<script src="js/jquery.js"></script>
	<script>
		$('footer a').click(function() {
			$(this).addClass('on').siblings().removeClass('on');
		});
	</script>
</body>
</html>
