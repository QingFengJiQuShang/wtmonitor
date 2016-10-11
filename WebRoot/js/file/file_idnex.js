		//模糊查询
		function query(){
			  var region= document.getElementById("region").value;
			var client= document.getElementById("client").value;
			//alert(region+client);
			  window.location.href="regionAction.do?method=query&reg="+region+"&client="+client;
			  
		  }
		//添加
		function add(){
			  window.location="jsp/dagl/region/addRegion.jsp";
			  
		  }
		//列表分页  
		  function fenye(num){
			  var region= document.getElementById("region").value;
			  var client= document.getElementById("client").value;
			  var url="";
			  if(region!=""){
				  url=url+"&reg="+region;
			  }
			  if(client!=""){
				  url=url+"&client="+client;
			  }
    		  window.location.href="regionAction.do?method=query&num="+num+url;
         }
		  //编辑
		  function findById(id){
    		  window.location.href="regionAction.do?method=findById&id="+id;
         }
		//删除
		  function del(id){
    		  window.location.href="regionAction.do?method=delEntity&id="+id;
         }
		  //下载  
		  function exp(){
			  var region= document.getElementById("region").value;
			  var client= document.getElementById("client").value;
			  var url="";
			  if(region!=""){
				  url=url+"&reg="+region;
			  }
			  if(client!=""){
				  url=url+"&client="+client;
			  }
    		  window.location.href="regionAction.do?method=export"+url;
         }
		  
		  	//		批量删除
$(".delete_batch").click(function() {
	var ids="";
	$("tbody tr").each(function() {
		if($(this).find("em").hasClass("selected")) {
			ids=ids+$(this).find("em").children().val()+",";
		}
	})
	 window.location.href="regionAction.do?method=deleteEntity&ids="+ids;
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
