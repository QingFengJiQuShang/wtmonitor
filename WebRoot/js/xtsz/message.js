	function gotoUrl (){
		
			 var phone= document.getElementById("phone").value;
			 var begintime= document.getElementById("begintime").value;
			 var endtime= document.getElementById("endtime").value;
			 var flag= document.getElementById("flag").value;
			  var url="";
			  if(phone!=""){
				  url=url+"&phone="+phone;
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
    		  window.location.href="messageAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(elevatorId){
			  window.location="messageAction.do?method=findByAdd&elevatorId="+elevatorId;
			  
		  }
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="messageAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="messageAction.do?method=findById&id="+id+"&flag="+flag;
         }
		   function send(id){
    		 // window.location.href="messageAction.do?method=send&id="+id;
    		  $.ajax({
					     mtype:'post',
			             url: "messageAction.do?method=send",
			             data: {"id":id},
			             dataType: "text",
			             success: function(data){
			                       if(data==0) {
			                    	  query();
			                      }else if(data==30){
			                    	  alert("短信宝密码错误！");
			                      }else if(data==40){
			                    	  alert("短信宝账号不存在！");
			                      }else if(data==41){
			                    	  alert("短信宝余额不足！");
			                      }else if(data==42){
			                    	  alert("短信宝帐号过期！");
			                      }else if(data==43){
			                    	  alert("IP地址限制！");
			                      }else if(data==50){
			                    	  alert("内容含有敏感词！");
			                      }else if(data==51){
			                    	  alert("手机号码不正确！");
			                      }
			               }
			    });
         }
		 //下载  
		  function exp(){
    		  window.location.href="messageAction.do?method=export"+gotoUrl ();
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
