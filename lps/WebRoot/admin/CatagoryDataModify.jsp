<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*,com.cyl.basic.*"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
	String oldName = request.getParameter("oldName");
	String newName = request.getParameter("newName");
	
// 	System.out.println("oldName newName " + oldName + " : " + newName);
	
	boolean isSucc = false;

	if(newName == null || newName.equals("")){
		response.getWriter().write("<p>数据为空\n</p>");
	}
	else{
		RoomCatagory oc = new RoomCatagory(0,oldName.trim());
		RoomCatagory nc = new RoomCatagory(0,newName.trim());
		RoomCatagoryService.getInstance().modify(oc, nc);
		response.getWriter().write("<p>修改成功\n</p>");
	}
	response.getWriter().flush();
%>