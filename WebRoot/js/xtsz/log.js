	function gotoUrl (){
			 var username= document.getElementById("username").value;
			 var begintime= document.getElementById("begintime").value;
			 var endtime= document.getElementById("endtime").value;
			var flag= document.getElementById("flag").value;
			  var url="";
			  if(username!=""){
				  url=url+"&username="+username;
			  }
			  if(begintime!=""){
				  url=url+"&begintime="+begintime;
			  }
			if(endtime!=""){
				  url=url+"&endtime="+endtime;
			  }
			if(flag!=""){
				  url=url+"&flag="+flag;
			  }
			
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="logAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(elevatorId){
			  window.location="logAction.do?method=findByAdd&elevatorId="+elevatorId;
			  
		  }
		//删除
		del=function(flag){
			JqueryDialog.Open('删除日志', 'jsp/xtsz/log/deteleLog.jsp?flag='+flag, 900, 350);
		}
			/**
	 * 关闭
	 */
	closeShow=function(){
		window.parent.JqueryDialog.Close();
	}
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="logAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id){
    		  window.location.href="logAction.do?method=findById&id="+id;
         }
		  
		 //下载  
		  function exp(){
    		  window.location.href="logAction.do?method=export"+gotoUrl ();
         }
		  	  

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
