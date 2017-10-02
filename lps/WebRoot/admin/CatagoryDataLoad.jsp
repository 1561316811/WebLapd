<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*, com.cyl.admin.catagory.*"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
	String name = request.getParameter("name");
	boolean isSucc = false;

	if(name == null || name.equals("")){
		response.getWriter().write("<p>数据为空\n</p>");
	}
	else{
		Catagory c = new Catagory(name);
		if(CatagoryService.load(c)){
	 response.getWriter().write("<p>保存成功\n</p>");
		}
		else{
	response.getWriter().write("<p>添加失败，"+ c.getName() +"类别已存在\n</p>");
		}
	}
	response.getWriter().flush();
%>