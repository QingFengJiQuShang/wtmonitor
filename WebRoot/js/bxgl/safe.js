	function gotoUrl (){
			 var name= document.getElementById("name").value;
			 var type= document.getElementById("type").value;
			 var liaisons= document.getElementById("liaisons").value;
			 
			  var url="";
			  if(name!=""){
				  url=url+"&name="+name;
			  }
			  if(liaisons!=""){
				  url=url+"&liaisons="+liaisons;
			  }
			  if(type!=""&&type!="请选择"){
				  url=url+"&type="+type;
			  }
			  
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="safeAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(elevatorId){
			  window.location="jsp/xtgl/rescueUnit/addRescueUnit.jsp?elevatorId="+elevatorId;
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="safeAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="safeAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
			   if( confirm("确定删除这条电梯保险记录？")){
    		  		window.location.href="safeAction.do?method=delEntity&id="+id;
    		  }
         }
		  
		      //下载  
		  function exp(){
    		  window.location.href="safeAction.do?method=export"+gotoUrl ();
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
	 		window.location.href="safeAction.do?method=deleteEntity&ids="+ids;
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
