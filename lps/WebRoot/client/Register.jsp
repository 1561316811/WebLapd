<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@ page import="com.cyl.user.*" %>
<%@ page import="com.cyl.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
request.setCharacterEncoding("utf-8");
String action = request.getParameter("action");
if(action != null && action.equals("register")){

	User user = new User();
	user.setIdUser(request.getParameter("idUser"));
	user.setPassword(request.getParameter("password2"));
	user.setEmail(request.getParameter("email"));
	user.setQuestion(request.getParameter("question"));
// 	System.out.println("request.getParameter('question') : " + request.getParameter("question"));
// 	System.out.print("user.getQuestion() : " + user.getQuestion());
	user.setAnswer(request.getParameter("answer"));
// 	System.out.println("request.getParameter('answer') : " + request.getParameter("answer"));
	UserService.getInstance().add(user);
	response.sendRedirect("UserLogIn.jsp");
}
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <title>Animated Form Switching with jQuery</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
        <meta name="description" content="Expand, contract, animate forms with jQuery wihtout leaving the page" />
        <meta name="keywords" content="expand, form, css3, jquery, animate, width, height, adapt, unobtrusive javascript"/>
		<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
         <script src="js/reg_ajax.js" type="text/javascript"></script>
    </head>
    <body>
		<div class="wrapper">
			<div class="content">
				<div id="form_wrapper" class="form_wrapper">
					<form class="register active" method="post" id="theForm"action="Register.jsp" onsubmit="return formcheck();">
						<div>
								<input type="hidden" name="action" value="register"/>
						</div>
						<h3>注册</h3>
						<div class="column">
							<div>
								<label>用户名:</label>
								<input type="text" id="idUser" name="idUser" onblur="RegCheck.checkUserName(this.value, 'idUserError')"/>
								<span class="error" id="idUserError"></span>
							</div>
							<div>
								<label>密码:</label>
								<input type="password" id="password1" 
															onkeyup="RegCheck.inputValue(this.value, 'password1Error', 0)"/>
								<span class="error" id="password1Error"></span>
							</div>
							<div>
								<label>确认密码:</label>
								<input type="password" id="password2" name="password2" onkeyup="RegCheck.inputValue(this.value, 'password2Error', 1)"/>
								<span class="error" id="password2Error"></span>
							</div>
							<div>
								<label>邮箱:</label>
								<input type="text" id="email" name="email" onblur="RegCheck.checkEmail(this.value,'emailError')"/>
								<span class="error" id="emailError" ></span>
							</div>
							
							<div >
								<label >密保:&nbsp;&nbsp; 
									<select name="question" id="question" >
				            <option value="父亲的姓名？">父亲的姓名？</option>
				            <option value="母亲的姓名？">母亲的姓名？</option>
				            <option value="我的出生地？">我的出生地？</option>
				            <option value="我喜欢的食物？">我喜欢的食物？</option>
				            <option value="我的爱人是？">我的爱人是？</option>
				            <option value="我最喜欢的老师？">我最喜欢的老师？</option>
				            <option value="我的姓名？" selected="selected">我的姓名？</option>
				            <option value="我的职业？">我的职业？</option>
				            <option value="我毕业学校名称？">我毕业学校名称？</option>
				          </select>
			          </label>
							</div>
							
							<div>
								<label>密保答案:</label>
								<input type="text" id="answer" name="answer" onkeyup="RegCheck.checkAnswer(this.value,'errorAnswer')"/>
								<span class="error" id="errorAnswer" ></span>
							</div>
							
						</div>
						<div class="bottom" style="float: left;margin-left: 150px;" >
							<input type="submit" value="注册" />
							<a href="UserLogIn.jsp" rel="login" class="linkform">已有账号，点击这里</a>
							<div class="clear"></div>
						</div>
					</form>
					
					
				</div>
				<div class="clear"></div>
			</div>
		</div>
	
	<script type="text/javascript">
	
	function formcheck(){
	
		if(RegCheck.checkIsErrorExistIdUser() == false){
			return false;
		}
		if(RegCheck.inputValue(objGetById("password1").value, 'password1Error', 0) == false){
			return false;
		}
		if(RegCheck.inputValue(objGetById("password2").value, 'password2Error', 1) == false){
			return false;
		}
		if(RegCheck.checkEmail(objGetById("email").value,'emailError') == false){
			return false;
		}
		if(RegCheck.checkAnswer(objGetById("answer").value,'errorAnswer') == false){
			return false;
		}
		return true;
	}
	
	</script>

    </body>
</html>