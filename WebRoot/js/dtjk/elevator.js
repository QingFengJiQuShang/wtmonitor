document.body.scrollTop = document.documentElement.scrollTop = 0;
function gotoUrl (){
			var province= document.getElementById("province").value;
			 var city= document.getElementById("city").value;
			 var area= document.getElementById("area").value;
			 var registerid= document.getElementById("registerid").value;
			 var distinguishid= document.getElementById("distinguishid").value;
			 var useUnitName= document.getElementById("useUnitName").value;
			  var maintenanceUnitName= document.getElementById("maintenanceUnitName").value;
			 var propertyUnitName= document.getElementById("propertyUnitName").value;
			 var makeUnitName= document.getElementById("makeUnitName").value;
			 var installPlace= document.getElementById("installPlace").value;
			  var url="";
			  if(province!=""){
				  url=url+"&province="+province;
			  }
			  if(city!=""){
				  url=url+"&city="+city;
			  }
			  if(area!=""){
				  url=url+"&area="+area;
			  }
			  if(registerid!=""){
				  url=url+"&registerid="+registerid;
			  }
			  if(distinguishid!=""){
				  url=url+"&distinguishid="+distinguishid;
			  }
			  if(useUnitName!=""){
				  url=url+"&useUnitName="+useUnitName;
			  }
			  if(maintenanceUnitName!=""){
				  url=url+"&maintenanceUnitName="+maintenanceUnitName;
			  }
			  if(propertyUnitName!=""){
				  url=url+"&propertyUnitName="+propertyUnitName;
			  }
			   if(makeUnitName!=""){
				  url=url+"&makeUnitName="+makeUnitName;
			  }
			  if(installPlace!=""){
				  url=url+"&installPlace="+installPlace;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="elevatorAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(){
			  window.location="jsp/dtjk/elevator/addElevator.jsp";
			  
		  }
		/**
 * 上传页面
 */
upload=function(){
	JqueryDialog.Open('导入电梯列表信息', 'jsp/dtjk/elevator/upload.jsp', 600, 300);
}
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="elevatorAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="elevatorAction.do?method=findById&id="+id+"&flag="+flag;
         }
		    //编辑 网关
		  function findById1(id){
    		  window.location.href="gatewayAction.do?method=findById&elevatorId="+id;
         }
		//删除
		  function del(id){
			  if( confirm("确定删除这条电梯记录？")){
    		 		 window.location.href="elevatorAction.do?method=delEntity&id="+id;
    		  }
         }
		  
		 //下载  
		  function exp(){
    		  window.location.href="elevatorAction.do?method=export"+gotoUrl ();
         }
		 
		   //选择模糊查询
		function query1(){
			 var id= document.getElementById("id").value;
			 var id1= document.getElementById("id1").value;
			 var id2= document.getElementById("id2").value;
			 var installPlace= document.getElementById("installPlace").value;
			 var registerid= document.getElementById("registerid").value;
			 var useUnitName= document.getElementById("useUnitName").value;

    		  window.location.href="elevatorAction.do?method=query1&id="+id+"&id1="+id1+"&id2="+id2+"&installPlace="+installPlace+"&registerid="+registerid+"&useUnitName="+useUnitName;
		  }
		  //选择列表分页  
		  function fenye1(num){
			 var id= document.getElementById("id").value;
			 var id1= document.getElementById("id1").value;
			 var id2= document.getElementById("id2").value;
			 var installPlace= document.getElementById("installPlace").value;
			 var registerid= document.getElementById("registerid").value;
			 var useUnitName= document.getElementById("useUnitName").value;
    		  window.location.href="elevatorAction.do?method=query1&num="+num+"&id="+id+"&id1="+id1+"&id2="+id2+"&installPlace="+installPlace+"&registerid="+registerid+"&useUnitName="+useUnitName;
         }
		  
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	var len=0;
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
				len++;
			}
		})
		 if(len>0 && confirm("确定删除这 "+len+" 条记录？")){
	 		window.location.href="elevatorAction.do?method=deleteEntity&ids="+ids;
	 	}
})
		  

	//全选
$("tbody").on("click", ".wei", function() {
		$(this).children("i").toggleClass("gou");
		var checked = $(".wei").length;
		var unchecked = $("tbody").find(".gou").length
		if(checked == unchecked) {
			$(".all").children("i").addClass("gou")
		} else {
			$(".all").children("i").removeClass("gou")
		}
	})
	//取消全选
$(".all").on("click", function() {
	var checked = $(".wei").length;
	var unchecked = $("tbody").find(".gou").length
	if(checked == unchecked) {
		$(this).children("i").removeClass("gou")
		$("tbody").find("i").removeClass("gou")
	} else {
		$(this).children("i").addClass("gou")
		$("tbody").find("i").addClass("gou")
	}
})
    
		  	//		批量充值流量
$(".recharge").click(function() {
	var ids="";
	var len=0;
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
				len++;
			}
		})
		 if(len>0 && confirm("确定为这 "+len+" 条电梯记录进行流量充值？")){
	 		window.location.href="jsp/dtjk/manage/recharge.jsp?ids="+ids;
	 	}
})


		  	//		批量添加服务费
$(".batch").click(function() {
	var ids="";
	var len=0;
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
				len++;
			}
		})
		 if(len>0 && confirm("确定为这 "+len+" 部电梯记录添加服务费？")){
	 		window.location.href="jsp/dtjk/service/batchService.jsp?ids="+ids;
	 	}
})