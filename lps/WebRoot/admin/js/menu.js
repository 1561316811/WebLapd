//定义头菜单menu数组
var headNameMenu = new Array();

//水平控制栏颜色
var cUnfocus = "#4dc2e6";
var cFocus = "#000000";

function ObjHeadNameMenu(name, idL, idR, idV){
	this.name = name;
	this.idL = idL;
	this.idR = idR;
	this.idV = idV;
}

$(document).ready(function() {
	//将首页的添加到数组中
	$("#l-index").click(indexClick);
	
	
	function setFucusColor(objU, obj2F){
		objU.css("background-color",cUnfocus);  
		obj2F.css("background-color",cFocus);  
	}
	
	//设置iframe显示情况
	function iframeVisibelStatus(objIframe){
		objIframe.siblings().hide();
		objIframe.show();
	}
	
	//首页点击
	function indexClick(){
		iframeVisibelStatus($("#l-index-v"));
		setFucusColor($("#l-index").siblings(), $("#l-index"));
	}
	
	
	
//left菜单控制right的显示
	$("li").click(function(){
		//获取iframe的所要加载的文件名
		var fileName = $(this).find('input').val();
		var iDivChild= $("#d-iframe iframe");
		
		
		var liId = $(this).attr("id");
		var headControlName = $(this).find(".fumenu").text();  //获取li里面的p内容text
		var index = 0;
		var headControl = $("#ul-headControl li");  //获取水平控制的对象
		var objHnameMenu = new ObjHeadNameMenu("", "", "", "");
		
		if(fileName == null){
			return ;
		}
		
		for( ; index < headNameMenu.length; index ++){
			if(headNameMenu[index].name == headControlName){  //如果水平控制栏存在则退出循环
				break;
			}
		}
		
		if(index == headNameMenu.length){ //如果条件成立，则添加该标签
			objHnameMenu.name = headControlName;
			objHnameMenu.idL = liId;
			objHnameMenu.idR = liId + "-r";
			objHnameMenu.idV = liId + "-v";
			headNameMenu[headNameMenu.length] = objHnameMenu;
			
			//添加新的ifram
			iDivChild.last().after("<iframe id='" + objHnameMenu.idV + "' src='" + fileName + "'></iframe");
			//设置iframes显示情况
			iframeVisibelStatus($("#" + objHnameMenu.idV));
			
			//添加新的head menu
			headControl.last().after("<li class='l-select' id='" +objHnameMenu.idR + "'>" +
					"<a>" + headControlName + "</a> " +
							"<span class='close'" + "id='" + objHnameMenu.idR +"-s'" +
							"></span></li>");
			
			//将其他的设置颜色
			setFucusColor($("#" + objHnameMenu.idR).siblings(), $("#" + objHnameMenu.idR));
			
			//新生成的标签的点击事件
			$("#" + objHnameMenu.idR).click(function(){
				var idR;
				var i;
				
				setFucusColor($(this).siblings(),$(this));
				
				//iframe的显示发生改变
				idR = $(this).attr("id");
				i = findRIdIndex(idR);
				
				iframeVisibelStatus($("#" + headNameMenu[i].idV));
				
			})
			//为新添加的<span class='close'></span></li>添加click事件
			$(".close").click(cloceClick);
		}else{ //条件不成立 ，显示对应的内容
			
			setFucusColor($("#" + headNameMenu[index].idR).siblings(), $("#" + headNameMenu[index].idR))
			iframeVisibelStatus($("#" + headNameMenu[index].idV));
		}
	
	});
	
//为close标签注册事件
	function cloceClick(){
		var x = 0;
		var parent = $(this).parent();
		var objIframe;
		
		for(; x < headNameMenu.length; x ++){
			if(headNameMenu[x].idR == parent.attr("id")){
				objIframe = $("#" + headNameMenu[x].idV);
				//让下一个iframe显示
				if(objIframe.is(":visible")){
					//其他所有的同胞都是隐藏
					setFucusColor($(parent).prev().siblings(), $(parent).prev())
					
					objIframe.prev().show();
				}
				
				//将其所对应的iframe也同样移除
				objIframe.remove();
				remove(x, headNameMenu);
			}
		}
		parent.remove();
	}

	//找到idR对应的数组下标
	function findRIdIndex(idR){
		var x = 0;
		for(; x < headNameMenu.length; x ++){
			if(headNameMenu[x].idR == idR){
				return x;
			}
		}
		return -1;
	}
	
//移除数组中的某个元素
	function remove(index, arr){
		var x = index;
		if(x > arr.length){
			return false;
		}
		if(arr.length == 1){
			arr.length --;
			return true;
		}
		if(arr.length <= 0)
			return false;
		for(; x < arr.length; x ++){
			arr[x] = arr[x + 1];
		}
		arr.length --;
		return true;
	}
	

	
	
	//绑定元素点击事件
	$(".menu-list ul li").click(function() {
		//判断对象是显示还是隐藏
		if ($(this).children(".list-p").is(":hidden")) {
			//表示隐藏
			if (!$(this).children(".list-p").is(":animated")) {
				$(this).children(".xiala").css({
					'transform' : 'rotate(180deg)'
				});
				//如果当前没有进行动画，则添加新动画
			$(this).children(".list-p").animate({
					height : 'show'
				}, 1000)
					//siblings遍历div1的元素
					.end().siblings().find(".list-p").hide(1000);
			}
		} else {
			//表示显示
			if (!$(this).children(".list-p").is(":animated")) {
				$(this).children(".xiala").css({
					'transform' : 'rotate(360deg)'
				});
				$(this).children(".list-p").animate({
					height : 'hide'
				}, 1000)
					.end().siblings().find(".list-p").hide(1000);
			}
		}
	});

	//阻止事件冒泡，子元素不再继承父元素的点击事件
	$('.list-p').click(function(e) {
		e.stopPropagation();
	});

	//点击子菜单为子菜单添加样式，并移除所有其他子菜单样式
	$(".menu-list ul li .list-p .zcd").click(function() {
		//设置当前菜单为选中状态的样式，并移除同类同级别的其他元素的样式
		$(this).addClass("removes").siblings().removeClass("removes");
		//遍历获取所有父菜单元素
		$(".list-p").each(function() {
			//判断当前的父菜单是否是隐藏状态
			if ($(this).is(":hidden")) {
				//如果是隐藏状态则移除其样式
				$(this).children(".zcd").removeClass("removes");
			}
		});
	});
});