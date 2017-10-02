<%@page import="com.cyl.user.UserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cyl.sql.*" %>
<%
	String idUser = request.getParameter("idUser"); 
	System.out.println(idUser);
// 	Writer out = response.getWriter();
// 	response.get
	
	response.getWriter().write(UserService.isUserEixst(idUser) == true ? 1 : 0);
	response.getWriter().flush();
%>