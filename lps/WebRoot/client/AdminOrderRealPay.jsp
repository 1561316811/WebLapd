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
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("AdminLogIn.jsp");
	}

	 //根据订单号获取时间
	ServerOrder s = ServerOrderService.getInstance()
	.getOrderByIdOrder(idOrder);
	List<PayPath> listpp = PayPathService.getInstance().getLimitData(1, PayPathService.getInstance().getNum());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>订单状态</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/list.css">
<link rel="stylesheet" type="text/css" href="icon-font/iconfont.css">
</head>


<body>

	<div class="top">
		<a href="AdminOrderPaying.jsp?idAdmin=<%=idUser%>" class="iconfont">&#xe624;&nbsp;&nbsp;<span>订单状态</span></a>
	</div>

	<div class="buttom">
		<div class="d-display">
			<form action="AdminOrderPayToFinish.jsp" method="get" onsubmit="return check();">
				<input type="hidden" name="idAdmin" value="<%=idUser%>"> <input
					type="hidden" name="idOrder" value="<%=idOrder%>">
				<table>
					<tr>
						<td>房间号码</td>
						<td><%=s.getIdRoom()%></td>
					</tr>
					<tr>
						<td>订单类型</td>
						<td><%=s.getClockCatagory()%></td>
					</tr>
					<tr>
						<td>订单编号</td>
						<td><%=s.getIdOrder()%></td>
					</tr>
					<tr>
						<td>发起时间</td>
						<td><%=s.getAddTime()%></td>
					</tr>
				</table>
				<hr />




				<table>
					<tr>
						<td style="color: blue">开始时间</td>
						<td><%=new SimpleDateFormat("HH:mm").format(s.getStartTime())%></td>
					</tr>
					<tr>
						<td style="color: blue">结束时间</td>
						<td><%=new SimpleDateFormat("HH:mm").format(s.getEndTime())%></td>
					</tr>
					<tr>
						<td style="color: red">总共时间</td>
						<%
							Date d = s.getEndTime();
							int minu = (d.getHours() + 24 - s.getStartTime().getHours()) % 24 * 60;
							minu = minu + d.getMinutes() - s.getStartTime().getMinutes();
						%>
						<td><%=minu%>&nbsp;分钟&nbsp;&nbsp;</td>
					</tr>
				</table>
				<hr />
				<table>
					<tr>
						<td style="color: red">消费金额</td>
						<td><%=s.getPay() %>￥</td>
					</tr>
					<tr>
						<td style="color: red">实收金额</td>
						<td><input id="pay" name="realPay" type="text">￥</td>
					</tr>
					</table>
					<table>
					<tr>
						<td rowspan="3" style="color: red">支付方式</td>
						<td >
						<% for(int i = 0; i < listpp.size(); i ++){ %>
							<input checked="checked" name="payPath" type="radio" value="<%=listpp.get(i).getPayPath()%>" ><%=listpp.get(i).getPayPath()%></input>
						<%} %>
						</td>
					</tr>
						</table>
				<input id="submit" type="submit" value="提交"
					style="background-color: white;">
			</form>
		</div>
	</div>
	<script src="js/jquery.js"></script>
	<script>
		$(document).ready(function() {
			$('footer a').click(function() {
				$(this).addClass('on').siblings().removeClass('on');
			});
		})
	
		function check() {
			var pattern = /^\d+\.?\d*$/
			if (confirm("确认提交")) {
				if (pattern.exec($("#pay").val())) {
					return true;
				} else {
					alert("提交失败，输入非法");
					return false;
				}
			} else {
				return false;
			}
		}
	</script>
</body>
</html>
