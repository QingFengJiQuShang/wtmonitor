	function gotoUrl (){
			 var registerid= document.getElementById("registerid").value;
			 var distinguishid= document.getElementById("distinguishid").value;
			 var useUnitName= document.getElementById("useUnitName").value;
			  var brand= document.getElementById("brand").value;
			 var numbers= document.getElementById("numbers").value;
			  var url="";
			  if(registerid!=""){
				  url=url+"&registerid="+registerid;
			  }
			  if(distinguishid!=""){
				  url=url+"&distinguishid="+distinguishid;
			  }
			  if(useUnitName!=""){
				  url=url+"&useUnitName="+useUnitName;
			  }
			  if(brand!=""){
				  url=url+"&brand="+brand;
			  }
			  if(numbers!=""){
				  url=url+"&numbers="+numbers;
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
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="elevatorAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="elevatorAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
    		  window.location.href="elevatorAction.do?method=delEntity&id="+id;
         }
		  
		 //下载  
		  function exp(){
    		  window.location.href="elevatorAction.do?method=export"+gotoUrl ();
         }
		  
		  
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
			}
		})
	 window.location.href="elevatorAction.do?method=deleteEntity&ids="+ids;
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
