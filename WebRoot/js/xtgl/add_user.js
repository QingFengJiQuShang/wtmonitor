$(".manage").click(function() {
	$(this).children("i").toggleClass("jia");
	$(this).next().toggle();
})



 //新增用户时 判断用户名是否存在
		  function skip(){
			var loginname=document.getElementById("logn").value;
			$.ajax({
					     mtype:'post',
			             url: "usersAction.do?method=onlyUser",
			             data: {"loginname":loginname},
			             dataType: "text",
			             success: function(data){
			                       if(data==0) {
			                    	   alert("该用户名已存在，请重新输入！");
			                    	   $("#logn").focus();
			                      }
			               }
			    });
	}
		  
//修改用户时，判断用户名是否存在
		  function skip1(){
			 var loginname1=document.getElementById("logn1").value;
			var loginname=document.getElementById("logn").value;
			if(loginname!=loginname1){
				$.ajax({
					     mtype:'post',
			             url: "usersAction.do?method=onlyUser",
			             data: {"loginname":loginname},
			             dataType: "text",
			             success: function(data){
			                       if(data==0) {
			                    	   alert("该用户名已存在，请重新输入！");
			                    	   $("#logn").focus();
			                      }
			               }
			    });
			}
			
	}
		  
//用户管理
$("input[name=selected]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[name=selected]").length;
	if(yes == selected) {
		$(this).siblings("input[id='controlAll']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[id='controlAll']").prop({
			"checked": false
		});
	}
})
$("#controlAll").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='selected']").prop({
			"checked": true
		});
	} else {
		$("input[id='selected']").prop({
			"checked": false
		});
	}
})
//电梯监控
$("input[id=selectedjk]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[id=selectedjk]").length;
	if(yes == selected) {
		$(this).siblings("input[id='controlAlljk']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[id='controlAlljk']").prop({
			"checked": false
		});
	}
})
$("#controlAlljk").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='selectedjk']").prop({
			"checked": true
		});
	} else {
		$("input[id='selectedjk']").prop({
			"checked": false
		});
	}
})

//故障管理
$("input[id=selectedgz]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[id=selectedgz]").length;
	if(yes == selected) {
		$(this).siblings("input[id='controlAllgz']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[id='controlAllgz']").prop({
			"checked": false
		});
	}
})
$("#controlAllgz").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='selectedgz']").prop({
			"checked": true
		});
	} else {
		$("input[id='selectedgz']").prop({
			"checked": false
		});
	}
})


//统计分析
$("input[id=selectedtj]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[id=selectedtj]").length;
	if(yes == selected) {
		$(this).siblings("input[id='controlAlltj']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[id='controlAlltj']").prop({
			"checked": false
		});
	}
})
$("#controlAlltj").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='selectedtj']").prop({
			"checked": true
		});
	} else {
		$("input[id='selectedtj']").prop({
			"checked": false
		});
	}
})

//系统设置
$("input[id=selectedxt]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[id=selectedxt]").length;
	if(yes == selected) {
		$(this).siblings("input[id='controlAllxt']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[id='controlAllxt']").prop({
			"checked": false
		});
	}
})
$("#controlAllxt").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[id='selectedxt']").prop({
			"checked": true
		});
	} else {
		$("input[id='selectedxt']").prop({
			"checked": false
		});
	}
})