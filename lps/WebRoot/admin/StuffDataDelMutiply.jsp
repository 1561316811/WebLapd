<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.user.*, com.cyl.admin.room.*"%>

<%

	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
	String[] id = request.getParameterValues("idUser");
	
	String pageNum = request.getParameter("pageNum");
	
%>

<%

	
	if(id == null ){
		
	}else{
		for(String n: id){
			UserService.getInstance().del(n);
		}
		response.sendRedirect("StuffDataManage.jsp?idAdmin=" + idAdmin + "&pageNum=" + pageNum);
	}
	
%>