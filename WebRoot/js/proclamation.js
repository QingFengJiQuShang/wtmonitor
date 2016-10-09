$(function(){
	var id = Request("id");
	$.post("proclamation_loadById",{"id":id},function(data){
		$("#title").text(data.data[0].title);
		$("#msg").text(data.data[0].title);
		$("#createdBy").text(data.data[0].createdBy);
		$("#createdOn").text(data.data[0].createdOn);
		
	});
});

function Request(strName){  
	var strHref = location.href;   
	var intPos = strHref.indexOf("?");  
	var strRight = strHref.substr(intPos + 1);  
	var arrTmp = strRight.split("&");  
	for(var i = 0; i < arrTmp.length; i++) {  
	var arrTemp = arrTmp[i].split("=");  
	if(arrTemp[0].toUpperCase() == strName.toUpperCase()) return arrTemp[1];  
	}  
	return "";  
	}  
	