<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*, com.cyl.admin.room.*"%>

<%
	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
	// 	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
	// 		response.sendRedirect("AdminLogIn.jsp");
	// 	}
// 	<input class="-hidden-name" type="hidden" name="idRoom">
// 									<input class="-hidden-name" type="hidden" name="catagory">
// 									<input class="-hidden-name" type="hidden" name="floor">
// 									<input class="-hidden-name" type="hidden" name="size">
// 									<input class="-hidden-name" type="hidden" name="remark">
	System.out.println("Connect suss");
	
	boolean isSuc = false;

	Room r = null;

	r = new Room();
	r.setIdRoom(request.getParameter("idRoom"));
	
	r.setCategory(request.getParameter("catagory"));

	try {
		r.setFloor(Integer.parseInt(request.getParameter("floor")));
		r.setSize(Integer.parseInt(request.getParameter("size")));
	} catch (NumberFormatException e) {
		response.getWriter().write("<p>添加失败：" + r.getIdRoom() + "楼层，可容纳客人数只能为数字\n</p>");
		response.getWriter().flush();
		return ;
	}

	r.setRemark(request.getParameter("remark"));
	isSuc = RoomService.loadRoom(r);
	if (isSuc == false) {
		response.getWriter().write("<p>添加失败：" + r.getIdRoom() + "， 该房间已存在，保存失败\n</p>");
		response.getWriter().flush();
		return ;
	}
	response.getWriter().write("<p>添加成功</p>");
	response.getWriter().flush();
%>