/**
 * 
 */
//每一列的名字
var colNames = new Array();

$(document).ready(function() {
	
	//关闭表格按钮
	$("#a-close").click(function(){
		$(this).parents("#d-add-cg").animate({height : 'hide'}, "fast");
	})
	
	/*//添加一行表格
	$("#a-add-line").click(function() {
		var index;

		var item = $("table:visible").children();

		var colNames = $("form").children(".i-hidden-name");

		var rows = $("form").children("#in-rows");
		
		var lasttr = item.children("tr:last"); //找到最后一个tr
		
		var firstTd = lasttr.children("td:first"); //找到最后一个tr中的td
		
		var num = parseInt(firstTd.text());
		
		var tdItem = $("<tr></tr>");

		tdItem.append("<td>" + (num + 1) + "</td>");

		rows.attr("value", num + 1);

		for (index = 0;; index++) {
			if (colNames.eq(index).length > 0) {
				colNames[index] = colNames.eq(index).attr("name");
			} else {
				break;
			}
		}
		for (index = 0; index < colNames.length; index++) {
			tdItem.append("<td><input id='"+ colNames[index] + (num + 1) +"'name='" + colNames[index] + (num + 1) + "' type=\"text\" class=\"cj-bf\"></td>")
		}

		lasttr.after(tdItem);
	})*/

});