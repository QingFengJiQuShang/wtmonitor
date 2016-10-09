$(function() {
	// 读取导航列表
	$
			.post(
					"lists_loadLists",
					{},
					function(data) {
						$
								.each(
										data.data,
										function(i, o) {
											var html = '';
											var html2 = '';
											if (o.parentId == null) {
												if (o.children) {
													html += '<li><a href="#'
															+ o.filePath
															+ '" class="nav-header collapsed" data-toggle="collapse">'
															+ o.name
															+ '</a><ul class="nav nav-list collapse secondmenu" style="height: 0px;" id="'
															+ o.id
															+ '"></ul></li>';

												} else {
													html += '<li><a href="'
															+ o.filePath
															+ '" target="indexContent" class="nav-header collapsed">'
															+ o.name
															+ '</a></li>';
												}
												$("#main-nav").append(html);
											} else {
												html2 += '<li><a href="'
														+ o.filePath
														+ '" target="indexContent">'
														+ o.name + '</a></li>';
												$("#" + o.parentId).append(
														html2);
											}
										});
						$("#main-nav li").click(function() {
							$("#main-nav li").removeClass("active");
							$(this).addClass("active");
						});
					});
});
