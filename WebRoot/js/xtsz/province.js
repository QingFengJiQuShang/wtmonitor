	function gotoUrl (){
			 var title= document.getElementById("title").value;
			 
			  var url="";
			  if(title!=""){
				  url=url+"&title="+title;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="provinceAction.do?method=queryList"+gotoUrl();
		  }
		//添加
		function add(){
			  window.location="jsp/xtsz/position/addProvince.jsp";
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="provinceAction.do?method=queryList&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="provinceAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
			   if( confirm("确定删除这条省记录？")){
    		  		window.location.href="provinceAction.do?method=delEntity&id="+id;
    		  }
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
	 		window.location.href="provinceAction.do?method=deleteEntity&ids="+ids;
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
