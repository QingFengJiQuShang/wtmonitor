	function gotoUrl (){
			 var name= document.getElementById("name").value;
			 var numbers= document.getElementById("numbers").value;
			 var cardNumber= document.getElementById("cardNumber").value;
			 var unitId= document.getElementById("unitId").value;
			 
			  var url="";
			  if(name!=""){
				  url=url+"&name="+name;
			  }
			  if(cardNumber!=""){
				  url=url+"&cardNumber="+cardNumber;
			  }
			  if(numbers!=""){
				  url=url+"&numbers="+numbers;
			  }
			  if(unitId!=""){
				  url=url+"&unitId="+unitId;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="maintenanceUsersAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(unitId){
			  
			    window.location="jsp/xtgl/maintenanceUsers/addMaintenanceUsers.jsp?unitId="+unitId;
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="maintenanceUsersAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="maintenanceUsersAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
    		  window.location.href="maintenanceUsersAction.do?method=delEntity&id="+id+gotoUrl();
         }
		  
		  
		  
		  
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
			}
		})
	 window.location.href="maintenanceUsersAction.do?method=deleteEntity&ids="+ids+gotoUrl();
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
