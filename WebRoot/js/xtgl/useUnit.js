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
    		  window.location.href="useUnitAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(){
			  window.location="jsp/xtgl/useUnit/addUseUnit.jsp";
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="useUnitAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="useUnitAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
    		  window.location.href="useUnitAction.do?method=delEntity&id="+id;
         }
		  
		  
		  
		  
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
			}
		})
	 window.location.href="useUnitAction.do?method=deleteEntity&ids="+ids;
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
