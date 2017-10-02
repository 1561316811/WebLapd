<%@page import="com.cyl.admin.room.RoomService"%>
<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*, com.cyl.admin.room.*"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
%>

<jsp:useBean id="roomn" scope="page" class="com.cyl.admin.room.Room"></jsp:useBean>
<jsp:setProperty property="idRoom" name="room"/>
<jsp:setProperty property="catagory" name="room"/>
<jsp:setProperty property="floor" name="room"/>
<jsp:setProperty property="size" name="room"/>
<jsp:setProperty property="remark" name="room"/>

<% 
	Room roomo = new Room(request.getParameter("roomo"));
	
	RoomService.getInstance().modify(roomo, roomn);
	response.getWriter().write("<p>修改成功\n</p>");
		
	response.getWriter().flush();
%>