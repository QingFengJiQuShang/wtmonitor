	function gotoUrl (){
			 var name= document.getElementById("name").value;
			 var code= document.getElementById("code").value;
			 var liaisons= document.getElementById("liaisons").value;
			 
			  var url="";
			  if(name!=""){
				  url=url+"&name="+name;
			  }
			  if(liaisons!=""){
				  url=url+"&liaisons="+liaisons;
			  }
			  if(code!=""){
				  url=url+"&code="+code;
			  }
			  
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="maintenanceUnitAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(){
			window.location="jsp/xtgl/maintenanceUnit/addMaintenanceUnit.jsp";
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="maintenanceUnitAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="maintenanceUnitAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
    		  window.location.href="maintenanceUnitAction.do?method=delEntity&id="+id;
         }
		  
		  
		  
		  
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
			}
		})
	 window.location.href="maintenanceUnitAction.do?method=deleteEntity&ids="+ids;
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