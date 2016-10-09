$(function() {
	$("#VideoAdd").fileinput({
		showUpload : false,
		showCaption : false,
		browseClass : "btn btn-primary btn-lg",
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
	});

	$.post("advertise_loadListAll", {}, function(data) {
		$("#table").bootstrapTable({
			data : data.data
		});
	});

});

function actionFormatter(value, row, index) {
	return [
			'<a href="javascript:void(0);" data-target="#myModalUpdate" data-toggle="modal" onClick="loadById('
					+ row.id + ')"><span>编辑</span></a>',
			'<a href="javascript:void(0);" data-target="#video" data-toggle="modal" onClick="loadVideo('
					+ row.id + ')" ><span>视频</span></a>',
			'<a onClick="del(' + row.id
					+ ')" href="javascript:void(0);" ><span>删除</span></a>' ]
			.join('   ');
};

function addVideo(id) {
	var formData = new FormData($("#addVideoForm")[0]);
	$.ajax({
		url : "video_add",
		type : 'POST',
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			if (data.status) {
				alert('上传成功!');
				window.location.reload();
			} else {
				alert('文件过大或格式错误!');
			}
		},
		error : function(returndata) {
			alert('系统出错,请联系管理员!');
		}

	});
}

function loadVideo(id) {
	$("#advertiseId").val(id);
	$("#upload").attr("onclick", "addVideo(" + id + ")");
	$.post("video_loadVideo", {
		"parentId" : id
	}, function(data) {
		if (data.status) {
			$("#videoId").val(data.data.id);
			$("video").attr("src", "/Advertisement" + data.data.videoPath);
		} else {
			$("video").attr("src", "");
		}
	});
}

function del(id) {
	if (confirm("您确定要删除吗？")) {
		$.post("advertise_del", {
			"id" : id
		}, function(data) {
			window.location.reload();
		});
	}
	return false;
}

var updateId;
// 按ID获取广告牌
function loadById(id) {
	updateId = id;
	$
			.post(
					"advertise_loadById",
					{
						"id" : id
					},
					function(data) {
						
						$("#nameUpdate").val(data.data.name);
						$("#contactsUpdate").val(data.data.contacts);
						$("#addressUpdate").val(data.data.address);
						$("#aboutAddressUpdate").val(data.data.aboutAddress);
						$("#lookTimeUpdate").val(data.data.lookTime);
						$("#lookLongUpdate").val(data.data.lookLong);
						$("#trafficUpdate").val(data.data.traffic);
						$("#priceUpdate").val(data.data.price);
						$("#ownPriceUpdate").val(data.data.ownPrice);
						$("#proxyPriceUpdate").val(data.data.proxyPrice);
						$("#lonUpdate").val(data.data.lon);
						$("#latUpdate").val(data.data.lat);
						$("#telUpdate").val(data.data.tel);

						if(data.data.rentBeginTime != null && data.data.rentBeginTime != undefined){
							var beginTime = data.data.rentBeginTime.substring(10,
							"T");
							$("#beginTimeUpdate").val(beginTime);
							var endTime = data.data.rentBeginTime
									.substring(10, "T");
							$("#endTimeUpdate").val(endTime);
						}
						
						
						
						// if (data.data.isSellUpdate) {
						// $("#trueSell").attr("checked", "checked");
						// } else {
						// $("#falseSell").attr("checked", "checked");
						// }
						
						if (data.data.isApproval) {
							$("#trueApprovalUpdate").attr("checked", "checked");
						} else {
							$("#falseApprovalUpdate").attr("checked", "checked");
						}
						if (data.data.cost == "高") {
							$("#high").attr("checked", "checked");
						} else if (data.data.cost == "中") {
							$("#mid").attr("checked", "checked");
						} else {
							$("#low").attr("checked", "checked");
						}
						$(".btn-info").on('click', function() {
							delPhoto(data.data.photo.id);
							$('#fileUpdate').fileinput('refresh', {
								initialPreview : []
							});
						});
						$("#fileUpdate")
								.fileinput(
										'refresh',
										{
											initialPreview : [ // 预览图片的设置
											'<img src="/Advertisement'
													+ data.data.photo.path
													+ '" class="file-preview-image" title="123.jpg" alt="123.jpg" style="width:auto;height:160px;">' ]
										});
					});
}

// 按ID删除图片
function delPhoto(id) {
	$.post("advertise_delPhoto", {
		"photoId" : id,
		"advertiseId" : updateId
	}, function(data) {
		alert('删除成功');
	});
}

// 更新广告牌
function update() {
	var name = $("#nameUpdate").val();
	var contacts = $("#contactsUpdate").val();
	var address = $("#addressUpdate").val();
	var tel = $("#telUpdate").val();
	var aboutAddress = $("#aboutAddressUpdate").val();
	var lookTime = $("#lookTimeUpdate").val();
	if (lookTime == '') {
		lookTime = 0;
	}
	var lookLong = $("#lookLongUpdate").val();
	if (lookLong == '') {
		lookLong = 0;
	}
	var isApproval = $('input[name="isApprovalUpdate"]:checked').val();
	if (isApproval == "1") {
		isApproval = true;
	} else {
		isApproval = false;
	}
	var isSell = $('input[name="isSellUpdate"]:checked').val();
	if (isSell == "1") {
		isSell = true;
	} else {
		isSell = false;
	}
	var traffic = $("#trafficUpdate").val();
	if (traffic == '') {
		traffic = 0;
	}
	var cost = $('input[name="costUpdate"]:checked').val();
	var price = $("#priceUpdate").val();
	if (price == '') {
		price = 0;
	}
	var ownPrice = $("#ownPriceUpdate").val();
	if (ownPrice == '') {
		ownPrice = 0;
	}
	var proxyPrice = $("#proxyPriceUpdate").val();
	if (proxyPrice == '') {
		proxyPrice = 0;
	}
	var lat = $("#latUpdate").val();
	var lon = $("#lonUpdate").val();
	var beginTime = $("#beginTimeUpdate").val();
	var endTime = $("#endTimeUpdate").val();
	if (beginTime == '') {
		beginTime = null;
	}
	if (endTime == '') {
		endTime = null;
	}
	$.post("advertise_update", {
		"id" : updateId,
		"name" : name,
		"address" : address,
		"contacts" : contacts,
		"aboutAddress" : aboutAddress,
		"tel":tel,
		"lookTime" : lookTime,
		"lookLong" : lookLong,
		"isApproval" : isApproval,
		"isSell" : isSell,
		"traffic" : traffic,
		"cost" : cost,
		"price" : price,
		"proxyPrice" : proxyPrice,
		"ownPrice" : ownPrice,
		"lat" : lat,
		"lon" : lon,
		"beginTime" : beginTime,
		"endTime" : endTime
	}, function(data) {
		if (data.status) {
			alert('修改成功!');
			window.location.reload();
		} else {
			alert('修改失败!');
		}
	});
}

// 添加广告牌
function add() {

	var name = $("#nameAdd").val();
	var contacts = $("#contactsAdd").val();
	var address = $("#addressAdd").val();
	var aboutAddress = $("#aboutAddressAdd").val();
	var tel = $("#telAdd").val();
	var lookTime = $("#lookTimeAdd").val();
	if (lookTime == '') {
		lookTime = 0;
	}
	var lookLong = $("#lookLongAdd").val();
	if (lookLong == '') {
		lookLong = 0;
	}
	var isApproval = $('input[name="isApprovalAdd"]:checked').val();
	if (isApproval == "1") {
		isApproval = true;
	} else {
		isApproval = false;
	}
	var isSell = $('input[name="isSellAdd"]:checked').val();
	if (isSell == "1") {
		isSell = true;
	} else {
		isSell = false;
	}
	var traffic = $("#trafficAdd").val();
	if (traffic == '') {
		traffic = 0;
	}
	var cost = $('input[name="costAdd"]:checked').val();
	var price = $("#priceAdd").val();
	if (price == '') {
		price = 0;
	}
	var ownPrice = $("#ownPriceAdd").val();
	if (ownPrice == '') {
		ownPrice = 0;
	}
	var proxyPrice = $("#proxyPriceAdd").val();
	if (proxyPrice == '') {
		proxyPrice = 0;
	}
	var lat = $("#latAdd").val();
	var lon = $("#lonAdd").val();
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	$.post("advertise_add", {
		"name" : name,
		"address" : address,
		"contacts" : contacts,
		"aboutAddress" : aboutAddress,
		"lookTime" : lookTime,
		"lookLong" : lookLong,
		"isApproval" : isApproval,
		"isSell" : isSell,
		"tel":tel,
		"traffic" : traffic,
		"cost" : cost,
		"price" : price,
		"proxyPrice" : proxyPrice,
		"ownPrice" : ownPrice,
		"lat" : lat,
		"lon" : lon,
		"beginTime" : beginTime,
		"endTime" : endTime
	}, function(data) {
		if (data.status) {
			alert('添加成功!');
			window.location.reload();
		} else {
			alert('添加失败!');
		}
	});
}

function map2(type) {
	var city = '厦门';
	var map = new BMap.Map("map");
	map.centerAndZoom();
	map.enableScrollWheelZoom();
	var local = new BMap.LocalSearch(map, {
		renderOptions : {
			map : map
		}
	});
	local.search(city);
	// 点击获取经度纬度
	var geoc = new BMap.Geocoder();

	map.addEventListener("click", function(e) {
		var pt = e.point;
		geoc.getLocation(pt, function(rs) {
			var addComp = rs.addressComponents;
			alert(addComp.province + "  " + addComp.city + "  "
					+ addComp.district + "  " + addComp.street + " "
					+ addComp.streetNumber + "坐标:" + e.point.lng + " "
					+ e.point.lat);
			if (type == '1') {
				$("#addressUpdate").val(
						addComp.province + addComp.city + addComp.district
								+ addComp.street + addComp.streetNumber);
				$("#lonUpdate").val(e.point.lng);
				$("#latUpdate").val(e.point.lat);
			} else {
				$("#addressAdd").val(
						addComp.province + addComp.city + addComp.district
								+ addComp.street + addComp.streetNumber);
				$("#lonAdd").val(e.point.lng);
				$("#latAdd").val(e.point.lat);
			}
		});
	});
}

//
// function map(){
// city = "厦门";
// var mp = new BMap.Map("map");
// mp.centerAndZoom(city, 13);
// mp.enableScrollWheelZoom();
// // 复杂的自定义覆盖物
// function ComplexCustomOverlay(point, text, mouseoverText){
// this._point = point;
// this._text = text;
// this._overText = mouseoverText;
// }
// ComplexCustomOverlay.prototype = new BMap.Overlay();
// ComplexCustomOverlay.prototype.initialize = function(map){
// this._map = map;
// var div = this._div = document.createElement("div");
// div.style.position = "absolute";
// div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
// div.style.backgroundColor = "#EE5D5B";
// div.style.border = "1px solid #BC3B3A";
// div.style.color = "white";
// div.style.height = "25px";
// div.style.padding = "2px";
// div.style.lineHeight = "18px";
// div.style.whiteSpace = "nowrap";
// div.style.MozUserSelect = "none";
// div.style.fontSize = "12px";
// var span = this._span = document.createElement("span");
// div.appendChild(span);
// span.appendChild(document.createTextNode(this._text));
// var that = this;
//
// var arrow = this._arrow = document.createElement("div");
// arrow.style.background =
// "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png)
// no-repeat";
// arrow.style.position = "absolute";
// arrow.style.width = "11px";
// arrow.style.height = "10px";
// arrow.style.top = "22px";
// arrow.style.left = "10px";
// arrow.style.overflow = "hidden";
// div.appendChild(arrow);
//     
// div.onmouseover = function(){
// this.style.backgroundColor = "#6BADCA";
// this.style.borderColor = "#0000ff";
// this.getElementsByTagName("span")[0].innerHTML = that._overText;
// arrow.style.backgroundPosition = "0px -20px";
// };
//
// div.onmouseout = function(){
// this.style.backgroundColor = "#EE5D5B";
// this.style.borderColor = "#BC3B3A";
// this.getElementsByTagName("span")[0].innerHTML = that._text;
// arrow.style.backgroundPosition = "0px 0px";
// };
//
// mp.getPanes().labelPane.appendChild(div);
//      
// return div;
// };
// ComplexCustomOverlay.prototype.draw = function(){
// var map = this._map;
// var pixel = map.pointToOverlayPixel(this._point);
// this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
// this._div.style.top = pixel.y - 30 + "px";
// };
// var txt = "银湖海岸城", mouseoverTxt = txt + " " + parseInt(Math.random() *
// 1000,10) + "套" ;
//        
// var myGeo = new BMap.Geocoder();
// // 将地址解析结果显示在地图上,并调整地图视野
// myGeo.getPoint("厦门一中", function(point){
// if (point) {
// var myCompOverlay = new ComplexCustomOverlay(point, "银湖海岸城",mouseoverTxt);
// mp.addOverlay(myCompOverlay);
// }
// });
// }

