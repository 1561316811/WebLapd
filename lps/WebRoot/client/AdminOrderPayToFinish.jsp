
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cyl.basic.*"%>
<%@ page language="java"
	import="java.util.*,com.cyl.work.*,com.cyl.admin.room.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idAdmin");
	String idOrder = request.getParameter("idOrder");
	int realPay = Integer.parseInt(request.getParameter("realPay"));
	String payPath = request.getParameter("payPath");
	
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}
	ServerOrder so = new ServerOrder.Builder(idOrder)
					.payPath(payPath)
					.realPay(realPay)
					.status(OrderStatusService.getInstance().getOrderStatus(4))
					.build();
					
	ServerOrderService.getInstance().updateOrder(so);

%>

<script src="js/jquery.js"></script>
<script language="JavaScript1.2" type="text/javascript">

    $(function(){
		location.href="AdminOrderPaying.jsp?idAdmin=<%=idUser%>"
	})
</script>

