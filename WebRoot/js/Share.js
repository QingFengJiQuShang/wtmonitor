
/**
 * 关闭
 */
closeShow=function(){
	window.parent.JqueryDialog.Close();
}
/**
 * 上传页面
 */
upload=function(){
	JqueryDialog.Open('文件上传', 'jsp/comm/upload.jsp', 600, 300);
}
/**
 * 选择地图标注
 */
label=function(id){
	//alert(id);
	JqueryDialog.Open('地图标注', 'jsp/dtjk/elevator/label.jsp?id='+id, 600, 400);
}

/**
 * 选择使用单位
 */
selectUseUnitId=function(id,id1){
	JqueryDialog.Open('使用单位', 'useUnitAction.do?method=query1&id='+id+"&id1="+id1, 1000, 650);
}

/**
 * 选择维保单位
 */
selectMaintenanceUnit=function(id,id1){
	JqueryDialog.Open('维保单位', 'maintenanceUnitAction.do?method=query1&id='+id+"&id1="+id1, 1000, 650);
}

/**
 * 多选 救援单位
 */
selectRescueUnit=function(id,id1){
	JqueryDialog.Open('救援单位', 'rescueUnitAction.do?method=query1&id='+id+"&id1="+id1, 1000, 650);
}
// 删除 已选择的救援单位
closeMultiRescueUnit=function(id){
		$('#'+id+' option:selected').remove();
}
/**
 * 根据单位类型选择单位名称
 */
selectTypeUnitId1=function(id,id1,flag){
	 var flag= document.getElementById(flag).value;
	if(flag=='1'){
			JqueryDialog.Open('使用单位', 'useUnitAction.do?method=query2&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='2'){
			JqueryDialog.Open('物业单位', 'propertyUnitAction.do?method=query2&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='3'){
			JqueryDialog.Open('维保单位', 'maintenanceUnitAction.do?method=query2&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='5'){
			JqueryDialog.Open('救援单位', 'rescueUnitAction.do?method=query2&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else{
		alert("请先选择单位类型");
	}
}

/**
 * 选择电梯信息
 */
selectElevator=function(id,id1,id2,id3,id4){
	JqueryDialog.Open('电梯列表', 'elevatorAction.do?method=query1&id='+id+"&id1="+id1+"&id2="+id2+"&id3="+id3+"&id4="+id4, 1000, 650);
}
/**
 * 维保记录选择电梯信息
 */
selectElevator1=function(id,id1,id2,id3,id4,id5,id6,id7){
	JqueryDialog.Open('电梯列表', 'elevatorAction.do?method=query2&id='+id+"&id1="+id1+"&id2="+id2+"&id3="+id3+"&id4="+id4+"&id5="+id5+"&id6="+id6+"&id7="+id7, 1000, 650);
}

/**
 * 选择制造单位
 */
selectMakeUnitId=function(id,id1){
	JqueryDialog.Open('制造单位', 'makeUnitAction.do?method=query1&id='+id+"&id1="+id1, 1000, 650);
}


/**
 * 选择物业单位
 */
selectPropertyUnitId=function(id,id1){
	JqueryDialog.Open('物业单位', 'propertyUnitAction.do?method=query1&id='+id+"&id1="+id1, 1000, 650);
}
/**
 * 选择保险单位
 */
selectsafeUnitId=function(id,id1){
	JqueryDialog.Open('保险单位', 'safeUnitAction.do?method=query1&id='+id+"&id1="+id1, 1000, 650);
}
/**
 * 选择区域单位
 */
selectRegionUnitId=function(id,id1){
	JqueryDialog.Open('区域单位', 'regionUnitAction.do?method=query1&id='+id+"&id1="+id1, 1000, 650);
}
/**
 * 根据单位类型选择单位名称
 */
selectTypeUnitId=function(id,id1,flag){
	 var flag= document.getElementById(flag).value;
	if(flag=='1'){
			JqueryDialog.Open('使用单位', 'useUnitAction.do?method=query1&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='2'){
			JqueryDialog.Open('物业单位', 'propertyUnitAction.do?method=query1&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='3'){
			JqueryDialog.Open('维保单位', 'maintenanceUnitAction.do?method=query1&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='4'){
			JqueryDialog.Open('制造单位', 'makeUnitAction.do?method=query1&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='5'){
			JqueryDialog.Open('救援单位', 'rescueUnitAction.do?method=query2&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else if(flag=='6'){
			JqueryDialog.Open('保险单位', 'safeUnitAction.do?method=query1&id='+id+"&id1="+id1+"&flag"+flag, 1000, 650);
	}else{
		alert("请先选择单位类型");
	}
}


/**
 * 选择维保人员
 */
selectMaintenanceUsers=function(id,id1){
 			var unitId= document.getElementById("maintenanceUnitId").value;
 			if(unitId==""){
 				alert("请先选择维保单位");	
			  }else{
				   JqueryDialog.Open('维保人员', 'maintenanceUsersAction.do?method=query1&id='+id+"&id1="+id1+"&unitId="+unitId, 1000, 650);
			  }
}

/**
 * 选择维保人员  维保纪录
 */
selectMaintenanceUsers1=function(id,id1){
 			var unitId= document.getElementById("unitId").value;
				   JqueryDialog.Open('维保人员', 'maintenanceUsersAction.do?method=query1&id='+id+"&id1="+id1+"&unitId="+unitId, 1000, 650);
		
}
/**
 * 选择维保人员  维保纪录
 * id1 维保人id
 * id2 维保人姓名
 * id3 手机
 * id4 维保卡号
 */
selectMaintenanceUsers2=function(id,id1,id2,id3){
 			var unitId= document.getElementById("unitId").value;
				   JqueryDialog.Open('维保人员', 'maintenanceUsersAction.do?method=query2&id='+id+"&id1="+id1+"&id2="+id2+"&id3="+id3+"&unitId="+unitId, 1100, 650);
		
}


/** 判断是否为空 **/
showIsNull=function(id,title){
	var ids = document.getElementById(id);
	if(ids==null){
		return;
	}
	if(trim(ids.value)==''){
		alert(title+"不能为空！");
		//ids.focus();
		return false;
	}
	return true;
}
/** 判断是否为空 name**/
showIsNameNull=function(name,title){
	var flag=false;
	var objArray=document.getElementsByName(name);
	if(objArray==null){
		return;
	}
	for(i=0;i<objArray.length;i++){
		var obj=objArray[i];
		var str=obj.value;
		if ( str == "" ) {
			flag=true;
		}
		else{
			var regu = "^[ ]+$";
			var re = new RegExp(regu);
			flag=re.test(str);
		}
		if(flag){
			alert(title+"不能为空");
			obj.focus();
			return false;
		}
		
	}
	return true;
}
/** 判断是否为空 name 选项卡中只读的**/
showIsNameNull1=function(name,title){
	var flag=false;
	var objArray=document.getElementsByName(name);
	if(objArray==null){
		return;
	}
	if ( objArray.length<1) {
		flag=true;
	}
	if(flag){
		alert(title+"不能为空");
		return false;
	}
	return true;
}
trim=function(str){   
    return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
}
/** 判断是否为数字 name**/
showIsNameNumber=function(objName,text){ 
	var objArray=document.getElementsByName(objName);
	var re = /^\d+(\.\d+)?$/;
	for(i=0;i<objArray.length;i++){
		var obj=objArray[i];
		var str=obj.value;
		if(re.test(trim(str))==false){
		//if(isNaN(keyan.trim(str))){
			alert("请正确输入"+text+"！");
			//obj.focus();
			return false;
		}

	}
	return true;
}
/** 判断是否为数字 **/
showIsNumber=function(id,title){
	var ids = document.getElementById(id);
	var re = /^\d+(\.\d+)?$/;
	if(re.test(trim(ids.value))==false){
		alert("请正确输入"+title+"！");
		//ids.focus();
		return false;
	}
	return true;
}
/** 判断座机格式 **/
showIsTelephone=function(id,title){
	var ids = document.getElementById(id);
	var re = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,8}$/;
	
	if(re.test(ids.value)==false){
		alert("请正确输入"+title+"！");
		ids.focus();
		return false;
	}
	return true;
}
/** 判断手机格式 **/
showIsPhone=function(id,title){
	var ids = document.getElementById(id);
	var re = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
	if(re.test(ids.value)==false){
		alert("请正确输入"+title+"！");
		ids.focus();
		return false;
	}
	return true;
}

/** 判断手机格式  手机或者座机 **/
showIsPhoneOrTelephone=function(id,title){
	var ids = document.getElementById(id);
	var re = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
	var re1 = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,8}$/;
	if(re.test(ids.value)==true||re1.test(ids.value)==true){
		return true;
	}
		alert("请正确输入"+title+"！");
		ids.focus();
		return false;
	
}

/** 判断数字大小 **/
judgeNumberSize=function(id,id1,title){
	var id = document.getElementById(id);
	var id1 = document.getElementById(id1);
	if(parseInt(id.value)<=parseInt(id1.value)){
		return true;
	}else{
		alert("请正确输入"+title+"！");
		return false;
	}
}


/** 开始时间结束时间比较 **/
showRelDate=function(beginDate,endDate,title){
	var endDates2 = document.getElementById(endDate);
	var beginDates = document.getElementById(beginDate).value;
	var endDates = document.getElementById(endDate).value;
	
	beginDates=beginDates.substring(0,10);
	endDates=endDates.substring(0,10); 
	
	var arr=beginDates.split("-");
  	var beginDate=new Date(arr[0],arr[1],arr[2]);
  	var arr1=endDates.split("-");
  	var endDate=new Date(arr1[0],arr1[1],arr1[2]);
  	
	if(beginDate>endDate){
		alert(title);
		endDates2.value="";
		return false;
	}
	return true;
}
/** 开始时间结束时间比较(name) **/
showNameRelDate=function(beginDate,endDate,title){
	var beginDates = document.getElementsByName(beginDate);
	var endDates = document.getElementsByName(endDate);
	for(i=0;i<beginDates.length;i++){
		var arr=beginDates[i].value.split("-");
	  	var beginDate=new Date(arr[0],arr[1],arr[2]);
	  	var arr1=endDates[i].value.split("-");
	  	var endDate=new Date(arr1[0],arr1[1],arr1[2]);
	  	
		if(beginDate>endDate){
			alert(title);
			//endDates[i].focus();
			return false;
		}
	}
	return true;
}

/** 重置modDialog高度 **/
reSetHeight=function(){
	var height = $(window.document).height();
	$('#main',window.parent.document).height(height+20);
}
/** 重置modDialog高度 **/
reSetHeight1=function(){
	var height = $(window.document).height();
	$('#main',window.parent.document).height(height-20);
}
/** 重置modDialog高度 **/
reSetHeight2=function(){
	var height = $(window.document).height();
	$('#main',window.parent.document).height(height+50);
}
/** 重置modDialog高度 **/
reSetHeight3=function(){
	var height = $(window.document).height();
	$('#main',window.parent.document).height(height+50);
}

//将阿拉伯数字转换成中国大写
Arabia_to_Chinese=function (Num){
if(isNaN(Num)){ //验证输入的字符是否为数字
 alert("请检查小写金额是否正确");
 return;
}
//字符处理完毕后开始转换，采用前后两部分分别转换
part = String(Num).split(".");
newchar = "";
//小数点前进行转化
for(i=part[0].length-1;i>=0;i--){
 if(part[0].length > 10){
  alert("位数过大，无法计算");
  return "";
 }//若数量超过拾亿单位，提示
 tmpnewchar = "";
 perchar = part[0].charAt(i);
 switch(perchar){
  case "0": tmpnewchar="零" + tmpnewchar ;break;
  case "1": tmpnewchar="壹" + tmpnewchar ;break;
  case "2": tmpnewchar="贰" + tmpnewchar ;break;
  case "3": tmpnewchar="叁" + tmpnewchar ;break;
  case "4": tmpnewchar="肆" + tmpnewchar ;break;
  case "5": tmpnewchar="伍" + tmpnewchar ;break;
  case "6": tmpnewchar="陆" + tmpnewchar ;break;
  case "7": tmpnewchar="柒" + tmpnewchar ;break;
  case "8": tmpnewchar="捌" + tmpnewchar ;break;
  case "9": tmpnewchar="玖" + tmpnewchar ;break;
 }
 switch(part[0].length-i-1){
  case 0: tmpnewchar = tmpnewchar +"元" ;break;
  case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
  case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
  case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
  case 4: tmpnewchar= tmpnewchar +"万" ;break;
  case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
  case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
  case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
  case 8: tmpnewchar= tmpnewchar +"亿" ;break;
  case 9: tmpnewchar= tmpnewchar +"拾" ;break;
 }
 newchar = tmpnewchar + newchar;
}
//小数点之后进行转化
if(Num.indexOf(".")!=-1)
{
 if(part[1].length > 2){
  alert("小数点之后只能保留两位,系统将自动截断");
  part[1] = part[1].substr(0,2);
 }
 for(i=0;i<part[1].length;i++){
  tmpnewchar = "";
  perchar = part[1].charAt(i);
  switch(perchar){
   case "0": tmpnewchar="零" + tmpnewchar ;break;
   case "1": tmpnewchar="壹" + tmpnewchar ;break;
   case "2": tmpnewchar="贰" + tmpnewchar ;break;
   case "3": tmpnewchar="叁" + tmpnewchar ;break;
   case "4": tmpnewchar="肆" + tmpnewchar ;break;
   case "5": tmpnewchar="伍" + tmpnewchar ;break;
   case "6": tmpnewchar="陆" + tmpnewchar ;break;
   case "7": tmpnewchar="柒" + tmpnewchar ;break;
   case "8": tmpnewchar="捌" + tmpnewchar ;break;
   case "9": tmpnewchar="玖" + tmpnewchar ;break;
  }
  if(i==0)tmpnewchar =tmpnewchar + "角";
  if(i==1)tmpnewchar = tmpnewchar + "分";
  newchar = newchar + tmpnewchar;
 }
}
//替换所有无用汉字
while(newchar.search("零零") != -1)
 newchar = newchar.replace("零零", "零");
newchar = newchar.replace("零亿", "亿");
newchar = newchar.replace("亿万", "亿");
newchar = newchar.replace("零万", "万");
newchar = newchar.replace("零元", "元");
newchar = newchar.replace("零角", "");
newchar = newchar.replace("零分", "");
if (newchar.charAt(newchar.length-1) == "元" || newchar.charAt(newchar.length-1) == "角")
    newchar = newchar+"整";
return newchar;
}
