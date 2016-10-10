//进入二级页面
$(".frame_list li").click(function() {
	alert($(this).text());
	$(this).parents(".frame").hide().siblings(".con_top").hide().siblings(".tab").show();
})
//退出二级页面
$(".back span").click(function() {
	$(this).parents(".tab").hide().siblings(".frame").show().siblings(".con_top").show();
})
//二级小页面菜单切换
$(".tab_list ul").find("p").on("click", function() {
	var display = $(this).siblings("ul").css('display');
	if(display == 'none') {
		$(this).siblings("ul").show().parent().siblings().children("ul").hide();
		$(this).children().addClass("up").parents("li").siblings().children("p").children().removeClass("up");
	} else {
		$(this).siblings("ul").hide()
		$(this).children().removeClass("up").parent().find("p").children().removeClass("up")
	}
})