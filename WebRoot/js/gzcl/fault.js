	function gotoUrl (){
			 var registerid= document.getElementById("registerid").value;
			 var place= document.getElementById("place").value;
			 var begintime= document.getElementById("begintime").value;
			 var endtime= document.getElementById("endtime").value;
			 
			  var url="";
			  if(registerid!=""){
				  url=url+"&registerid="+registerid;
			  }
			  if(place!=""){
				  url=url+"&place="+place;
			  }
			  if(begintime!=""){
				  url=url+"&begintime="+begintime;
			  }
			   if(endtime!=""){
				  url=url+"&endtime="+endtime;
			  }
			   
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="faultAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(){
			  window.location="jsp/xtgl/useUnit/addUseUnit.jsp";
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="faultAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="faultAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
    		  window.location.href="faultAction.do?method=delEntity&id="+id;
         }
		    //下载  
		  function exp(){
    		  window.location.href="faultAction.do?method=export"+gotoUrl ();
         }
		   //下载  
		  function exp1(){
    		  window.location.href="faultAction.do?method=export1"+gotoUrl ();
         }
		   //模糊查询
		function query1(){
    		  window.location.href="faultAction.do?method=query1"+gotoUrl();
		  }
		//列表分页  
		  function fenye1(num){
    		  window.location.href="faultAction.do?method=query1&num="+num+gotoUrl();
         }
		  
		  
		  
		  
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
			}
		})
	 window.location.href="faultAction.do?method=deleteEntity&ids="+ids;
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
