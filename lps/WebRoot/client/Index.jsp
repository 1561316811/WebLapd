<%@ page language="java" import="java.util.*,com.cyl.work.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String idUser = request.getParameter("idUser");
	if (idUser == null || session.getAttribute(idUser) == null) { //检查如果没有登入，即返回登入界面
		response.sendRedirect("UserLogIn.jsp");
	}

	int size = PageMsg.msgSize; //页面数据条数

	int pageNum; //当前页面序号

	int msgNum; //信息总数量

	String strStart = request.getParameter("pageNum");

	int startNum; //开始的序号

	int sumPage; //总共页数

	if (strStart == null || strStart.equals("")) {
		pageNum = 1;
	} else {
		pageNum = Integer.parseInt(strStart);
	}

	msgNum = WorkRankService.getNum();

	sumPage = msgNum / size + 1;

	if (pageNum > sumPage) {
		pageNum = sumPage;
	} else if (pageNum < 1) {
		pageNum = 1;
	}

	startNum = (pageNum - 1) * size;

	List<WorkRank> list = WorkRankService.getLimitData(startNum, size);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="css/usercenter.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
	<div class="wrap-li">
		<table class="gridbar" style="width: 100%;" border="0">
			<thead>

				<tr>
					<th>编号</th>
					<th>牌号</th>
					<th>排钟</th>
					<th>点钟</th>
				</tr>

				<%
					for (int i = 0; i < list.size(); i++) {
						WorkRank item = list.get(i);
				%>
				<tr>
					<td><%=i + 1%></td>
					<td><%=item.getId()%></td>
					<td><%=item.getRankNum()%></td>
					<td><span style="color:red;"><%=item.getSpotNum()%></span></td>
				</tr>
				<%
					}
				%>
			</thead>
		</table>
	</div>

	<div class="pagin">
		<div class="message">
			共<i class="blue"><%=msgNum%></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageNum%>&nbsp;</i>页
		</div>
		<br/>
		<br/>
		<br/>
		<ul class="paginList">
			<li class="paginItem"><a
				href="Index.jsp?idUser=<%=idUser%>&pageNum=<%=pageNum - 1%>"><span
					class="pagepre"><img src="image/jt1.png"></span></a></li>
			<%
				int curSumPagenum = pageNum / size;
				curSumPagenum *= size;
			%>
			<%
				for (int i = curSumPagenum; i < sumPage; i++) {
			%>
			<li class="paginItem"><a
				href="Index.jsp?idUser=<%=idUser%>&pageNum=<%=i + 1%>"><%=i + 1%></a></li>
			<%
				}
			%>
			<li class="paginItem"><a
				href="Index.jsp?idUser=<%=idUser%>&pageNum=<%=pageNum + 1%>"><span
					class="pagenxt"><img src="image/jt.png"></span></a></li>
		</ul>
	</div>

	<div style="height: 1rem;"></div>
	<footer>
		<a href="Index.jsp?idUser=<%=idUser%>"><i class="iconfont" >&#xe60b;</i>首页</a>
		<a href="Message.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe75f;<img id="msg" src="image/redpoint.png" ></img></i>消息</a>
		<a href="Order.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe671;</i>订单</a>
		<a href="Center.jsp?idUser=<%=idUser%>"><i class="iconfont">&#xe617;</i>我的</a>
	</footer>
	<script src="js/jquery.js"></script>
	<script>
		$('footer a').click(function() {
			$(this).addClass('on').siblings().removeClass('on');
		});
	</script>
</body>
</html>
