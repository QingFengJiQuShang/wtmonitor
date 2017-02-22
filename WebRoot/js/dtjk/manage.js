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
			 var yearlyState= document.getElementById("yearlyState").value;
			 var serviceState= document.getElementById("serviceState").value;
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
			  if(yearlyState!=""){
				  url=url+"&yearlyState="+yearlyState;
			  }
			  if(serviceState!=""){
				  url=url+"&serviceState="+serviceState;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="elevatorAction.do?method=queryManage"+gotoUrl();
		  }
		
		//列表分页  
		  function fenye(num){
    		  window.location.href="elevatorAction.do?method=queryManage&num="+num+gotoUrl();
         }
		  
		   //编辑
		  function findById(id){
			 // alert(id);
    		  window.location.href="recordAction.do?method=query&elevatorId="+id;
         }
		    

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
		 if(len==0 ){
	 		alert("请选择");
	 	}
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
		  