<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*, com.cyl.admin.room.*"%>

<%

	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
	String[] idRooms = request.getParameterValues("idRoom");
	
	String pageNum = request.getParameter("pageNum");
	
%>

<%

	
	if(idRooms == null ){
		
	}else{
		for(String n: idRooms){
			Room c = new Room(n);
			RoomService.getInstance().del(c);
		}
		response.sendRedirect("RoomDataManage.jsp?idAdmin=" + idAdmin + "&pageNum=" + pageNum);
	}
	
%>