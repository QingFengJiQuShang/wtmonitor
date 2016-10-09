//进入二级页面
$(".frame_list li").click(function() {
		alert($(this).text());
		$(this).parents(".frame").hide().siblings(".con_top").hide().siblings(".tab").show();
	})
	//退出二级页面
$(".back>span").click(function() {
		$(this).parents(".tab").hide().siblings(".frame").show().siblings(".con_top").show();
})
		$('.clockpicker').clockpicker();
		$(function() {
			$("#datetimepicker3").on("click", function(e) {
				e.stopPropagation();
				$(this).lqdatetimepicker({
					css: 'datetime-day',
					dateType: 'D',
					selectback: function() {}
				});
			});
			$("#datetimepicker4").on("click", function(e) {
				e.stopPropagation();
				$(this).lqdatetimepicker({
					css: 'datetime-day',
					dateType: 'D',
					selectback: function() {}
				});
			});
		});