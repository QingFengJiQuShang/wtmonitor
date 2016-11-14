
//批量删除
$(".del").click(function() {
		$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				$(this).parents("tr").remove();
			}
		})
	})
	//单个删除
$(".del_one").click(function() {
		$(this).parents("tr").remove();
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

$(".list-order li").click(function() {
	$(this).addClass("active").siblings().removeClass("active")
})
//电梯数据电梯状态
$(".fenzhi span").click(function() {
	$(this).addClass("gou").parent().siblings().children("span").removeClass("gou")
})
