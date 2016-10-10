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
$(".table tbody").find('tr').on("dblclick",function(){
	$(".screen").hide();
	$(".tab").show();
})
//退出二级页面
$(".back span").click(function() {
	$(".screen").show();
	$(".tab").hide();
})
