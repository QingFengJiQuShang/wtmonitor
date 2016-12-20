	function gotoUrl (){
			 var elevatorId= document.getElementById("elevatorId").value;
			 var startTime= document.getElementById("startTime").value;
			 var endTime= document.getElementById("endTime").value;

			  var url="";
			  if(elevatorId!=""){
				  url=url+"&elevatorId="+elevatorId;
			  }
			  if(startTime!=""){
				  url=url+"&startTime="+startTime;
			  }
			  if(endTime!=""){
				  url=url+"&endTime="+endTime;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="serviceAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(elevatorId){
			  window.location="serviceAction.do?method=findByAdd&elevatorId="+elevatorId;
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="serviceAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="serviceAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
			  if( confirm("确定删除这条电梯服务费记录？")){
    		  	window.location.href="serviceAction.do?method=delEntity&id="+id+gotoUrl();
    		  }
         }
		  
		 //下载  
		  function exp(){
    		  window.location.href="serviceAction.do?method=export"+gotoUrl ();
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
			 window.location.href="serviceAction.do?method=deleteEntity&ids="+ids;
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
