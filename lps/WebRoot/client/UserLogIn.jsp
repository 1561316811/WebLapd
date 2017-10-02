<%@page import="com.cyl.work.WorkRank"%>
<%@page import="com.cyl.work.WorkRankService"%>
<%@ page language="java" import="java.util.*,com.cyl.sql.*, java.sql.*"
	pageEncoding="utf-8"%>
<%@ page import="com.cyl.user.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	request.setCharacterEncoding("utf-8");
	boolean isError = false;
	String action = request.getParameter("action");
	if (action != null && action.equals("logIn")) {
	
		String idUser = request.getParameter("idUser");
		String password = request.getParameter("password");
		/*  */
		try {
			UserService.getInstance().checkLogIn(idUser, password);
		} catch (UserInformationErrorException e) {
			isError = true;
			System.out.println(e.getMessage());
		}
		if (isError == false) { //条件成立，登入成功
// 			User user = new User(idUser);
			User user = UserService.getInstance().getUserByIdUser(idUser);
			user.setPassword(password);
			
			session.setAttribute(idUser, user);
			int id = user.getId();  
			//载入上钟排队信息
			WorkRankService.load(new WorkRank(id));
			
			//先默认签到，方便测试
			//登录
// 			System.out.println("logIn : " + UserService.getInstance().logIn(user)); 
			UserService.getInstance().logIn(user);
			//注册
			UserService.getInstance().signIn(user);
			
			
			response.sendRedirect("Center.jsp?idUser=" + user.getIdUser());
			
		}
	}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Animated Form Switching with jQuery</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta name="description"
	content="Expand, contract, animate forms with jQuery wihtout leaving the page" />
<meta name="keywords"
	content="expand, form, css3, jquery, animate, width, height, adapt, unobtrusive javascript" />
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/reg_ajax.js"></script>

</head> 
<body > 
<div class="wrapper">
 <h1>管理系统</h1>
			<h2>&nbsp;</h2>
			<div class="content">
				<div id="form_wrapper" class="form_wrapper">
					<form class="login active" method="post" action="UserLogIn.jsp?action=logIn" onsubmit="return checkForm()"> 
						<input type="hidden" name="action" value="logIn"/>
						<h3>登录</h3>
						<div>
							<label>用户名:</label>
							<input id="idUser" name="idUser" type="text" onblur="checkStuffName(this.value, 'errorIdUser')"/>
							<span class="error" id="errorIdUser" ></span>
						</div>
						<div>
							<label>密码: <a href="FindPassword.jsp" rel="forgot_password" class="forgot linkform">忘记密码</a></label>
							<input type="password" id="password" name="password" onkeyup="checkStuffPassword(this.value, 'errorPassword')"/>
							<%
								if (isError) {
							%>
								<span class="error" id="errorPassword" style="visibility: visible;">用户名或密码错误</span>
							<%
								} else {
							%>
								<span class="error" id="errorPassword" >用户名或密码错误</span>
							<%
								}
							%>
						</div>
						<div class="bottom">
							<div class="remember"><input type="checkbox" />
							  记住我
							</div>
							<input type="submit" value="登录" />
							<a href="Register.jsp" rel="register" class="linkform">没有用户名，点击这里注册</a>
							<div class="clear"></div>
						</div>
					</form>

				</div>
				<div class="clear"></div>
			</div>
		</div>

		<script type="text/javascript">
		
			function checkForm(){
				if(checkStuffName(objGetById("idUser").value, "errorIdUser") == false)
					return false;
				if(checkStuffName(objGetById("password").value, "errorPassword") == false)
					return false;
				return true;
			}
		
			function checkStuffName(v, id) {
				return RegCheck.checkInput(v, id);
			}
			
			function checkStuffPassword(v, id){
				return RegCheck.inputValue(v, id, 0);
			}
		</script>
	
    </body>
</html>