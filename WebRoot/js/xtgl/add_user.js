$(".manage").click(function() {
	$(this).children("i").toggleClass("jia");
	$(this).next().toggle()
})

//用户管理
$("input[name=selected]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[name=selected]").length;
	if(yes == selected) {
		$(this).siblings("input[name='controlAll']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[name='controlAll']").prop({
			"checked": false
		});
	}
})
$("#controlAll").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[name='selected']").prop({
			"checked": true
		});
	} else {
		$("input[name='selected']").prop({
			"checked": false
		});
	}
})
//电梯监控
$("input[name=selectedjk]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[name=selectedjk]").length;
	if(yes == selected) {
		$(this).siblings("input[name='controlAlljk']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[name='controlAlljk']").prop({
			"checked": false
		});
	}
})
$("#controlAlljk").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[name='selectedjk']").prop({
			"checked": true
		});
	} else {
		$("input[name='selectedjk']").prop({
			"checked": false
		});
	}
})

//故障管理
$("input[name=selectedgz]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[name=selectedgz]").length;
	if(yes == selected) {
		$(this).siblings("input[name='controlAllgz']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[name='controlAllgz']").prop({
			"checked": false
		});
	}
})
$("#controlAllgz").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[name='selectedgz']").prop({
			"checked": true
		});
	} else {
		$("input[name='selectedgz']").prop({
			"checked": false
		});
	}
})


//统计分析
$("input[name=selectedtj]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[name=selectedtj]").length;
	if(yes == selected) {
		$(this).siblings("input[name='controlAlltj']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[name='controlAlltj']").prop({
			"checked": false
		});
	}
})
$("#controlAlltj").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[name='selectedtj']").prop({
			"checked": true
		});
	} else {
		$("input[name='selectedtj']").prop({
			"checked": false
		});
	}
})

//系统设置
$("input[name=selectedxt]").click(function() {
	var sure = $(this).prop("checked");
	var yes = $(this).siblings("input:checkbox:checked").length;

	var selected = $(this).siblings("input[name=selectedxt]").length;
	if(yes == selected) {
		$(this).siblings("input[name='controlAllxt']").prop({
			"checked": true
		});
	} else {
		$(this).siblings("input[name='controlAllxt']").prop({
			"checked": false
		});
	}
})
$("#controlAllxt").click(function() {
	var sure = $(this).prop("checked");
	if(sure == true) {
		$("input[name='selectedxt']").prop({
			"checked": true
		});
	} else {
		$("input[name='selectedxt']").prop({
			"checked": false
		});
	}
})