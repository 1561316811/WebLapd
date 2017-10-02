<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.cyl.user.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
	}

	User u = UserService.getByID(idUser);
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="icon-font/iconfont.css">
</head>
<body>
	<header>

		<div class="left_icon">
			<a href="UserData.jsp?idUser=<%=idUser%>" class="iconfont">&#xe624;</a>
		</div>
	</header>

	<div class="d-display">
		<form action="UserDataModifySubmit.jsp?idUser=<%=idUser%>"
			method="post" onsubmit="return checkForm();">
			<table>
				<thead>
					<tr>
						<th>修改资料</th>
					</tr>

					<tr>
						<td>工作牌号:</td>
						<td><input name="id" value="<%=u.getId()%>"
							placeholder="<%=u.getId()%>">&nbsp号</td>
					</tr>
					<tr>
						<td>生日:</td>
						<td><input name="birthday" value="<%=u.getBirthday()%>"
							placeholder="<%=u.getBirthday()%> 格式如2017-7-8"></td>
					</tr>
					<tr>
						<td>电话号码:</td>
						<td><input name="telPhone" value="<%=u.getTelPhone()%>"
							placeholder="<%=u.getTelPhone()%>"></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input name="address" value="<%=u.getAddress()%>"
							placeholder="<%=u.getAddress()%>"></td>
					</tr>
					<tr>
						<td><input type="submit" value="保存"></td>
					</tr>
				</thead>
			</table>
		</form>
	</div>

	<script src="js/jquery.js"></script>
	<script>
		function checkForm() {
		}
	</script>
</body>
</html>
