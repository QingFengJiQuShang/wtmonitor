$("title").text("梯运宝");
$(".list-item p").click(function() {
	$(".list-item").find("p").removeClass("active")
	if($(this).hasClass("one")) {
		$(this).addClass("active");
		var display = $(this).next(".next").css("display");
		if(display == "none") {
			$(this).next(".next").show().parent().siblings().children(".next").hide();
			$(this).children("i").addClass("roate").parents(".list-item").siblings().find("i").removeClass("roate")
		} else {
			$(this).next(".next").hide();
			$(this).children("i").removeClass("roate")
		}
	} else if($(this).hasClass("two")) {
		$(this).addClass("active");
	}
	if($(this).parent().hasClass("jscroll") && document.body.offsetWidth < 1366 && display == "none") {
		$(".box").show();
	} else {
		$(".box").hide();
	}
})
$(".jscroll .next").click(function() {
	var dis2 = $(this).css("display");
	if(document.body.offsetWidth < 1366 && dis2 == "block") {
		$(".box").show();
	} else {
		$(".box").hide();
	}
})
