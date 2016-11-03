$("title").text("梯运宝");

$(".one").click(function() {
	var display = $(this).next(".next").css("display");
	if(display == "none") {
		$(this).next(".next").show().parent().siblings().children(".next").hide();
		$(this).children("i").addClass("roate").parents(".list-item").siblings().find("i").removeClass("roate")
	} else {
		$(this).next(".next").hide();
		$(this).children("i").removeClass("roate")
	}
})

$(".list-item .one").click(function() {
	if($(this).hasClass("grade")) {
		$(".list-item .one").removeClass("active");
	} else {
		$(this).addClass("active").parents().siblings().children(".one").removeClass("active")
	}
})