//通过id获取标记对象
function objGetById(id) {
	var itm = null;
	if (document.getElementById) {
		itm = document.getElementById(id);
	} else if (document.all) {
		itm = document.all[id];
	} else if (document.layers) {
		itm = document.layers[id];
	}
	return itm;
}

var errorImage = "<img src='images/note_error.gif' border=0/>";
var okImage = "<img src='images/note_ok.gif' border=0/>";
var xmlHttp = null;
var idUserExist="该用户名已存在";

var RegCheck = {
	value : new Array(),

	isErrorExistIdUser : false,
	
	//两次密码输入检查
	inputValue : function(v, odjId, index) {
		var itm = objGetById(odjId);
		RegCheck.value[index] = v;
		if(index == 0){
			if(RegCheck.checkLength(v, odjId) == false){  //首先检查密码的长度
				return false;
			}else{
				return RegCheck.checkInput(v, odjId);  //再检查密码的输入的字符
			}
		}
		else if (index == 1) {
			if (RegCheck.value[0] != RegCheck.value[1]) {
				itm.style.visibility = "visible";
				itm.style.color = "red";
				itm.innerHTML = RegCheck.error_msg("两次输入的密码不一致")
			} else if (RegCheck.simpleCheckLength(RegCheck.value[0])) { //先检查第一次的符合要求才可以
				itm.style.visibility = "visible";
				itm.style.color = "green";
				itm.innerHTML = RegCheck.sus_msg("密码符合要求")
				return true;
			}
		}
		return false;
	},
	
	checkUserName :function(v, id){
		if(RegCheck.checkInput(v, id) == false){
//			RegCheck.isErrorExistIdUser = true; //存在问题
			return false;
		}else{
			RegCheck.loadUserExist(v);
			return true;
		}
	},
	
	checkIsErrorExistIdUser: function(){
		if(RegCheck.isErrorExistIdUser == true){
			return false;
		}
		return true;
	},

	//简单的检查字符串的长度
	simpleCheckLength : function(v) {
		if (v.length < 5 || v.length > 10) {
			return false;
		}
		return true;
	},
	
	//检查字符串的长度，并且对显示界面进行修改
	checkLength : function(v, objId) {
		var itm = objGetById(objId);
		if (RegCheck.simpleCheckLength(v) == false) {
			itm.style.visibility = "visible";
			itm.style.color = "red";
			itm.innerHTML = RegCheck.error_msg("长度必须大于等于5位小于等于10位"); 
			return false;
		}
		itm.style.visibility = "visible";
		itm.style.color="green";
		itm.innerHTML = RegCheck.sus_msg("符合要求");
		return true;
	},

	//检查输入字符的合法性
	checkInput : function(v, id) {
		var itm = objGetById(id);
		var ch = v.charAt(v.length - 1);

		if (ch == '_'
			|| (ch >= 'a' && ch <= 'z')
			|| (ch <= 'Z' && ch >= 'A' )
			|| (ch >= '0' && ch <= '9')) {
			itm.style.visibility = "visible";
			itm.style.color = "green";
			itm.innerHTML = RegCheck.sus_msg("符合要求"); 
			return true;
		} else {
			itm.style.visibility = "visible";
			itm.style.color = "red";
			itm.innerHTML = RegCheck.error_msg("存在非法字符"); 
			return false;
		}
	},
	//检查邮箱输入的合法性
	checkEmail: function(v, id){
		var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]+/;
		var itm = objGetById(id);
		if(reg.test(v)){
			itm.style.visibility = "visible";
			itm.style.color = "green";
			itm.innerHTML = RegCheck.sus_msg("邮箱格式符合要求"); 
			return true;
		}
		itm.style.visibility = "visible";
		itm.style.color = "red";
		itm.innerHTML = RegCheck.error_msg("邮箱格式不正确，正确如：123456@qq.com"); 
		return false;
	},
	
	//检查密保的输入
	checkAnswer:function(v, id){
		var itm = objGetById(id);
		if(v == null || v == ""){
			itm.style.visibility = "visible";
			itm.style.color = "red";
			itm.innerHTML = RegCheck.error_msg("密保不能为空"); 
			return false;
		}
		itm.style.visibility = "visible";
		itm.style.color = "green";
		itm.innerHTML = RegCheck.sus_msg("密保符合要求"); 
		return true;
	},
	
	//加载用户名是否存在
	loadUserExist:function(v){
		if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}else if(window.ActiveXObject){
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.onreadystatechange = RegCheck.onReadyChange;
		xmlHttp.open("get", "ajax/CheckUserExist.jsp?idUser="+v, true);
		xmlHttp.send();
	
	},
	
	//接受数据时反应
	onReadyChange: function(){
		var msg = null;
		var itm = null;
		if(4 == xmlHttp.readyState && 200 == xmlHttp.status){
			msg = xmlHttp.responseText;
			itm = objGetById("idUserError");
			if(msg == 0){
				RegCheck.isErrorExistIdUser = false; //不存在问题
				itm.style.visibility = "visible";
				itm.style.color = "green";
				itm.innerHTML = RegCheck.sus_msg("符合要求"); 
			}else {
				RegCheck.isErrorExistIdUser = true; //存在问题
				itm.style.visibility = "visible";
				itm.style.color = "red";
				itm.innerHTML = RegCheck.error_msg(idUserExist); 
			}
		}
	},
	
	//错误提示
	error_msg : function(msg) {
		return errorImage + msg;
	},
	
	//正确提示
	sus_msg : function(msg) {
		return okImage + msg;
	}
}