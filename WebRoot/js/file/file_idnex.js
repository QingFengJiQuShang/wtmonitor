
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
	//		批量删除
$(".delete_batch").click(function() {
	$("tbody tr").each(function() {
		if($(this).find("em").hasClass("selected")) {
			$(this).remove();
		}
	})
})