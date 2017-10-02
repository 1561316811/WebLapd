<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.cyl.user.*,java.sql.*,java.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
	}
	boolean isSucss = true;
%>
<jsp:useBean id="user" scope="page" class="com.cyl.user.User"></jsp:useBean>

<jsp:setProperty property="id" name="user"/>
<jsp:setProperty property="telPhone" name="user"/>
<jsp:setProperty property="email" name="user"/>
<jsp:setProperty property="address" name="user"/>
<%
	java.util.Date ud = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
	
	java.sql.Date d = new java.sql.Date(ud.getTime());
	
	user.setBirthday(d);
	user.setIdUser(idUser);

	isSucss = UserService.getInstance().updateBasic(user);
	
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
	<div  class="font-size" style="margin: 20px 0px 0px 20px;">
	<a href="UserData.jsp?idUser=<%= idUser %>">点击这里返回</a>
	</div>
</body>
</html>
