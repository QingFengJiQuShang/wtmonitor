		function gotoUrl (){
			   var reg= document.getElementById("reg").value;
			   var distinguish= document.getElementById("distinguish").value;
			  var label= document.getElementById("labels").value;
			  var brand= document.getElementById("brand").value;
			  var type= document.getElementById("type").value;
			  var model= document.getElementById("model").value;
			  var numbers= document.getElementById("numbers").value;
			  var lengths= document.getElementById("lengths").value;
			  
			  var url="";
			  if(reg!=""){
				  url=url+"&reg="+reg;
			  }
			  if(distinguish!=""){
				  url=url+"&distinguish="+distinguish;
			  }
			  if(label!=""){
				  url=url+"&label="+label;
			  }
			  if(brand!=""){
				  url=url+"&brand="+brand;
			  }
			  if(type!=""){
				  url=url+"&type="+type;
			  }
			  if(model!=""){
				  url=url+"&model="+model;
			  }
			  if(numbers!=""){
				  url=url+"&numbers="+numbers;
			  }
			  if(lengths!=""){
				  url=url+"&lengths="+lengths;
			  }
			  
			  return url;
		  }
	   //模糊查询
		function query(){
			 var url=gotoUrl();
    		  window.location.href="elevatorAction.do?method=query"+url;
		  }
		//添加
		function add(){
			  window.location="jsp/dagl/elevator/addElevator.jsp";
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="elevatorAction.do?method=query&num="+num+gotoUrl ();
         }
		  //编辑
		  function findById(id){
    		  window.location.href="elevatorAction.do?method=findById&id="+id;
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
$(".delete_batch").click(function() {
	var ids="";
	$("tbody tr").each(function() {
		if($(this).find("em").hasClass("selected")) {
			ids=ids+$(this).find("em").children().val()+",";
		}
	})
	 window.location.href="elevatorAction.do?method=deleteEntity&ids="+ids;
})
		  
//重置
		  function reset (){
    		   document.getElementById("region").innerHTML = "";
			   document.getElementById("clientId").innerHTML = "";
         }
		  
//		多选
$("tbody").on("click", ".select", function() {
		$(this).children("em").toggleClass("selected");
		var checked = $(".select").length;
		var unchecked = $("tbody").find(".selected").length
		if(checked == unchecked) {
			$(".all em").addClass("selected")
		} else {
			$(".all em").removeClass("selected")
		}
	})
	//		全选
$(".all").on("click", function() {
		var checked = $(".select").length;
		var unchecked = $("tbody").find(".selected").length
		if(checked == unchecked) {
			$(this).children("em").removeClass("selected")
			$("tbody").find("em").removeClass("selected")
		} else {
			$(this).children("em").addClass("selected")
			$("tbody").find("em").addClass("selected")
		}
	})
	
