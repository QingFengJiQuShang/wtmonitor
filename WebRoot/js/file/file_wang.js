		function gotoUrl (){
			  var type= document.getElementById("type").value;
			  var net= document.getElementById("net").value;
			  var flow= document.getElementById("flow").value;
			  var communication= document.getElementById("communication").value;
			  var terminal= document.getElementById("terminal").value;
			  var hardware= document.getElementById("hardware").value;
			  var software= document.getElementById("software").value;
			  
			  var url="";
			  if(type!=""){
				  url=url+"&type="+type;
			  }
			  if(net!=""){
				  url=url+"&net="+net;
			  }
			  if(flow!=""){
				  url=url+"&flow="+flow;
			  }
			  if(communication!=""){
				  url=url+"&communication="+communication;
			  }
			  if(terminal!=""){
				  url=url+"&terminal="+terminal;
			  }
			  if(hardware!=""){
				  url=url+"&hardware="+hardware;
			  }
			  if(software!=""){
				  url=url+"&software="+software;
			  }
			  
			  return url;
		  }
	   //模糊查询
		function query(){
			 var url=gotoUrl();
    		  window.location.href="gatewayAction.do?method=query"+url;
		  }
		//添加
		function add(){
			  window.location="jsp/dagl/gateway/addGateway.jsp";
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="gatewayAction.do?method=query&num="+num+gotoUrl ();
         }
		  //编辑
		  function findById(id){
    		  window.location.href="gatewayAction.do?method=findById&id="+id;
         }
		//删除
		  function del(id){
    		  window.location.href="gatewayAction.do?method=delEntity&id="+id;
         }
		  //下载  
		  function exp(){
			 
    		  window.location.href="gatewayAction.do?method=export"+gotoUrl ();
         }
		  
		  
		  
		  	//		批量删除
$(".delete_batch").click(function() {
	var ids="";
	$("tbody tr").each(function() {
		if($(this).find("em").hasClass("selected")) {
			ids=ids+$(this).find("em").children().val()+",";
		}
	})
	 window.location.href="gatewayAction.do?method=deleteEntity&ids="+ids;
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
	
