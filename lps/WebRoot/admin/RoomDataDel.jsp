<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.admin.*, com.cyl.admin.room.*"%>

<%

	request.setCharacterEncoding("utf-8");
	
	String idAdmin = request.getParameter("idAdmin");
	
	if (idAdmin == null || session.getAttribute(idAdmin) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("Index.jsp");
	}
	
	String idRoom = request.getParameter("idRoom");
	
	String pageNum = request.getParameter("pageNum");
	
	boolean isSuss = false;
	
	String cont = "删除失败";
	
	if(idRoom == null || idRoom.equals("")){
		isSuss = false;
	}else{
		Room r = new Room(idRoom);
		isSuss = RoomService.getInstance().del(r);
	}
	if(isSuss){
		cont = "删除成功";
	}
	
%>

<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body style="background-color: white;">

<script language="JavaScript1.2" type="text/javascript">

function redirect(URLStr) { location = URLStr; }

</script>

<!-- Place this in the 'body' section -->
<div style="margin-top: 50px;margin-left: 50px;">
	<p color="red"><%= cont %></p>
	<a href="RoomDataManage.jsp?idAdmin=<%=idAdmin%>&pageNum=<%=pageNum%>" >点击此处返回</a>
</div>

</body>	
</html>