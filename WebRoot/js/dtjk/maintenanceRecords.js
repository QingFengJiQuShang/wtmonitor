	function gotoUrl (){
			 var elevatorId= document.getElementById("elevatorId").value;
			 var time= document.getElementById("time").value;
			 
			 var useUnitId= document.getElementById("useUnitId").value;
			 var useUnitId1= document.getElementById("useUnitId1").value;
			 var maintenanceUnitId= document.getElementById("maintenanceUnitId").value;
			 var maintenanceUnitId1= document.getElementById("maintenanceUnitId1").value;

			  var url="";
			  if(elevatorId!=""){
				  url=url+"&elevatorId="+elevatorId;
			  }
			  if(time!=""){
				  url=url+"&time="+time;
			  }
			  if(useUnitId!=""){
				  url=url+"&useUnitId="+useUnitId;
			  }
			  if(useUnitId1!=""){
				  url=url+"&useUnitId1="+useUnitId1;
			  }
			  if(maintenanceUnitId!=""){
				  url=url+"&maintenanceUnitId="+maintenanceUnitId;
			  }
			  if(maintenanceUnitId1!=""){
				  url=url+"&maintenanceUnitId1="+maintenanceUnitId1;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="recordsAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(elevatorId){
			  window.location="recordsAction.do?method=findByAdd&elevatorId="+elevatorId;
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="recordsAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="recordsAction.do?method=findById&id="+id+"&flag="+flag+gotoUrl();
         }
		//删除
		  function del(id){
    		  window.location.href="recordsAction.do?method=delEntity&id="+id+gotoUrl();
         }
		  
		 //下载  
		  function exp(){
    		  window.location.href="recordsAction.do?method=export"+gotoUrl ();
         }
		  
		  
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
			}
		})
	 window.location.href="recordsAction.do?method=deleteEntity&ids="+ids;
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
