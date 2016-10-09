$(".add_user").click(function() {
	$(".user_list").hide();
	$(".add_info").show();
})
$(".delete").click(function() {
	$(this).parents("tr").remove();
})