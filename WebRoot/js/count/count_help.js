$(".select").click(function() {
	var a = $(this).attr("data");
	if(a == 50) {
		$(this).attr("data", "60");
		$(this).find("i").css({
			"backgroundImage": 'url(../../img/up1.png)'
		});
	} else {
		$(this).attr("data", "50")
		$(this).find("i").css({
			"backgroundImage": 'url(../../img/down2.png)'
		});
	}
})
