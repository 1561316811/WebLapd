<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*,com.cyl.basic.*"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
	String name = request.getParameter("name");
	
	String pageNum = request.getParameter("pageNum");
	
	if(name == null || name.equals("")){
		
	}else{
		RoomCatagory c = new RoomCatagory(0, name);
		RoomCatagoryService.getInstance().del(c);
		response.sendRedirect("CatagoryDataManage.jsp?idAdmin=" + idAdmin + "&pageNum=" + pageNum);
	}
%>