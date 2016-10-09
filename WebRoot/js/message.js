	function showHuifuContent(id) {
		$("#" + id).toggle();
	};
	function hideHuifuContent(id) {
		var content = $("#text-" + id).val();
		var parentId = id;
		alert(content);
		$.post("message_addmsg", {
			"content" : content,
			"parentId" : parentId,
			"isChildren" : true
		}, function(data) {
			if (data.status) {
				alert("回复成功");
				window.location.reload();
				$("#huifu").css("display", "none");
			}
		});
	}

	function showLiuyan() {
		$("#liuyan").toggle();
	}
	function hideLiuyan() {
		var content = $("#liuyan-text").val();
		$.post("message_addmsg", {
			"content" : content,
			"parentId" : 0,
			"isChildren" : false
		}, function(data) {
			if (data.status) {
				alert("留言成功");
				window.location.reload();
				$("#liuyan").css("display", "none");
			}
		});
	}

	function loadMsgList(pageIndex) {
	$("#msgContent").empty();
		var pageSize = 2;

		$
				.ajax({
					data : {
						isChildren : false,
						pageIndex : pageIndex,
						pageSize : pageSize
					},
					async : false,
					url : "message_fartherList",
					type : "POST",
					dataType : 'json',
					success : function(data) {
						$
								.each(
										data.data.dataList,
										function(i, o) {
											var createdBy = o.createdBy;
											var id = o.id;
											var html = '<div>';
											html += '<div class="msg-header">'
													+ o.createdBy + '</div>';
											html += '<div class="msg">' + o.msg
													+ '</div>';
											html += '<div class="msg-content">'
													+ '<span class="msg-time">'
													+ o.createdOn
													+ '</span>'
													+ '<a href="javascript:void(0)" onclick="showHuifuContent('
													+ o.id
													+ ')"><span> 回 复 </span> </a></div></div>';
											html += '<div id="'+o.id+'" class="huifu">'
													+ '<div>'
													+ '<textarea id="text-'+o.id+'" style="width:600px;height:100px;"></textarea>'
													+ '</div>'
													+ '<div>'
													+ '<button class="btn btn-success" onclick="hideHuifuContent('
													+ o.id
													+ ')"> 回 复 </button>'
													+ '</div></div>';

											$
													.ajax({
														data : {
															parentId : id,
															isChildren : true
														},
														async : false,
														url : "message_childList",
														type : "POST",
														dataType : 'json',
														success : function(data) {
															$
																	.each(
																			data.data,
																			function(
																					i,
																					o) {

																				html += '<div>';
																				html += '<div class="msg-header">'
																						+ o.createdBy
																						+ '</div>';
																				html += '<div class="msg">'
																						+ '@'
																						+ createdBy
																						+ '  '
																						+ o.msg
																						+ '</div>';
																				html += '<div class="msg-content">'
																						+ '<span class="msg-time">'
																						+ o.createdOn
																						+ '</span>'
																						+ '</div></div>';
																			});
														}
													});
											$("#msgContent").append(html);
											if (data.data.hasNextPage) {
												$("#toNextPage")
														.attr(
																"onclick",
																"loadMsgList("
																		+ (data.data.pageIndex + 1)
																		+ ")");
												$("#toNextPage").removeClass(
														"disabled");
												$("#toNextPage").removeClass(
														"disableCss");
												$("#toEnd")
														.attr(
																"onclick",
																"loadMsgList("
																		+ (data.data.totalPage)
																		+ ")");
												$("#toEnd").removeClass(
														"disabled");
												$("#toEnd").removeClass(
														"disableCss");
											} else {
												$("#toNextPage").addClass(
														"disabled");
												$("#toNextPage").addClass(
														"disableCss");
												$("#toEnd")
														.addClass("disabled");
												$("#toEnd").addClass(
														"disableCss");
											}
											if (data.data.hasPreviousPage) {
												$("#toPreviousPage")
														.attr(
																"onclick",
																"loadMsgList("
																		+ (data.data.pageIndex - 1)
																		+ ")");
												$("#toPreviousPage")
														.removeClass("disabled");
												$("#toPreviousPage")
														.removeClass(
																"disableCss");
												$("#toTop").attr("onclick",
														"loadMsgList(1)");
												$("#toTop").removeClass(
														"disabled");
												$("#toTop").removeClass(
														"disableCss");
											} else {
												$("#toPreviousPage").addClass(
														"disabled");
												$("#toPreviousPage").addClass(
														"disableCss");
												$("#toTop")
														.addClass("disabled");
												$("#toTop").addClass(
														"disableCss");
											}
										});

					}
				});
	}
	$(function() {
		loadMsgList(1);
	});