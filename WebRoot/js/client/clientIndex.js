
$(function() {
	var cityPicker = new IIInsomniaCityPicker({
		data : data,
		target : 'cityBtn',
		valType : 'k-v',
		hideCityInput : {
			name : 'city',
			id : 'city'
		},
		hideProvinceInput : {
			name : 'province',
			id : 'province'
		},
		callback : function() {
			serchAdvertise();
			loadZone();
			advertiseNav(1);
		}
	});
	cityPicker.init();
	//advertiseNav(1);
	serchAdvertise();
	// loadZone('3');
});

var pageIndex = 1;
var pageSize = 8;

function serchAdvertise() {
	html = '';
	var text = $(".bar").val();
	var areaName = $("#cityBtn").val();
	var isApproval = false;
	if($("#checkbox-1-1").is(":checked")){
		isApproval = true;
	}
	var cost = null;
	if($("#checkbox-1-2").is(":checked")){
		cost = "高";
	}
	var free = false;
	if($("#checkbox-1-3").is(":checked")){
		free = true;
	}
	var nearFree = false;
	if($("#checkbox-1-4").is(":checked")){
		nearFree = true;
	}
	$.post("advertise_loadList", {
		"areaName":areaName,
		"text" : text,
		"cost" : cost,
		"isApproval" : isApproval,
		"free" : free,
		"nearFree" : nearFree,
		"pageIndex" : pageIndex,
		"pageSize" : pageSize
	}, function(data) {
		map(areaName, data);
		$.each(data.data.dataList, function(i, o) {
			html += '<li><a href="javascript:void(0)"   onclick="loadById('+o.id+')">' + o.name + '</a></li>';
		});
		$(".nav>li").not(":first").remove();
		$(".nav").append(html);
		if (data.data.hasNextPage) {
			$("#toNextPage").attr("onclick",
					"serchAdvertise(" + (data.data.pageIndex + 1) + ")");
			$("#toNextPage").removeClass("disabled");
			$("#toNextPage").removeClass("disableCss");
			$("#toEnd")
					.attr("onclick", "serchAdvertise(" + (data.data.totalPage) + ")");
			$("#toEnd").removeClass("disabled");
			$("#toEnd").removeClass("disableCss");
		} else {
			$("#toNextPage").addClass("disabled");
			$("#toNextPage").addClass("disableCss");
			$("#toEnd").addClass("disabled");
			$("#toEnd").addClass("disableCss");
		}
		if (data.data.hasPreviousPage) {
			$("#toPreviousPage").attr( 
					"onclick",
					"serchAdvertise(" + (data.data.pageIndex - 1)+")");
			$("#toPreviousPage").removeClass("disabled");
			$("#toPreviousPage").removeClass("disableCss");
			$("#toTop").attr("onclick", "serchAdvertise(1)");
			$("#toTop").removeClass("disabled");
			$("#toTop").removeClass("disableCss");
		} else {
			$("#toPreviousPage").addClass("disabled");
			$("#toPreviousPage").addClass("disableCss");
			$("#toTop").addClass("disabled");
			$("#toTop").addClass("disableCss");
		}
	});

}

function advertiseNav(pageIndex) {
	html = '';
	var name = $("#cityBtn").val();
	$.post("advertise_loadList", {
		"areaName" : name,
		"pageIndex" : pageIndex,
		"pageSize" : pageSize
	}, function(data) {
		$.each(data.data.dataList, function(i, o) {
			html += '<li><a href="javascript:void(0)" onclick="loadById('+o.id+')">' + o.name + '</a></li>';
		});
		$(".nav>li").not(":first").remove();
		$(".nav").append(html);
		if (data.data.hasNextPage) {
			$("#toNextPage").attr("onclick",
					"advertiseNav(" + (data.data.pageIndex + 1) + ")");
			$("#toNextPage").removeClass("disabled");
			$("#toNextPage").removeClass("disableCss");
			$("#toEnd")
					.attr("onclick", "advertiseNav(" + (data.data.totalPage) + ")");
			$("#toEnd").removeClass("disabled");
			$("#toEnd").removeClass("disableCss");
		} else {
			$("#toNextPage").addClass("disabled");
			$("#toNextPage").addClass("disableCss");
			$("#toEnd").addClass("disabled");
			$("#toEnd").addClass("disableCss");
		}
		if (data.data.hasPreviousPage) {
			$("#toPreviousPage").attr( 
					"onclick",
					"advertiseNav(" + (data.data.pageIndex - 1)+")");
			$("#toPreviousPage").removeClass("disabled");
			$("#toPreviousPage").removeClass("disableCss");
			$("#toTop").attr("onclick", "advertiseNav(1)");
			$("#toTop").removeClass("disabled");
			$("#toTop").removeClass("disableCss");
		} else {
			$("#toPreviousPage").addClass("disabled");
			$("#toPreviousPage").addClass("disableCss");
			$("#toTop").addClass("disabled");
			$("#toTop").addClass("disableCss");
		}

	});
}

function loadById(id) {
	$.post("advertise_loadById", {
		"id" : id
	}, function(data) {

		var photoPath = data.data.photo==null?"":"/Advertisement"+data.data.photo.path;
		var point = new BMap.Point(data.data.lon, data.data.lat);
		var marker = new BMap.Marker(point);  // 创建标注
		mp.clearOverlays(marker);
		mp.addOverlay(marker);
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		mp.centerAndZoom(point, 13);
		var isApproval;
		if(data.data.isApproval){
			isApproval = '(已审批)';
		}else{
			isApproval="";
		}
		var sContent =
			"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+data.data.name+""+isApproval+"" + 
			' <a href="javascript:void(0);" onclick="showVideo('+data.data.id+')"  style="float:right;margin:4px">视频</a></h4>'+
			"<img style='float:right;margin:4px' id='imgDemo' src='/Advertisement"+photoPath+"' width='139' height='104' title=''/>" + 
			"<div style='width:450px; '>"+
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>地址:"+data.data.address+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>联系人:"+data.data.contacts+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>电话:"+data.data.tel+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>位置简介:"+data.data.aboutAddress+"</p>" + 
			//"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>关注度:"+data.data.hot+"点击量</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>媒体价:"+data.data.price+"元;  性价比:"+data.data.cost+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>可视时长:"+data.data.lookTime+"秒; 可视路线长:"+data.data.lookLong+"米</p>" + 
			"</div>";

		var infoWindow = new BMap.InfoWindow(sContent);

		marker.addEventListener("click", function(){          
			   this.openInfoWindow(infoWindow);
			   //图片加载完毕重绘infowindow
			   document.getElementById('imgDemo').onload = function (){
				   infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
			   };
			});
	});
}

function loadZone() {
	var id = "";
	var html = '';
	$.post("area_loadZone", {
		"id" : id
	}, function(data) {
		$.each(data.data, function(i, o) {
			html += "<li id='" + o.id + "' onclick='advertiseNav(&quot;"
					+ o.name + "&quot;)'>" + o.name + "</li>";
		});
		$(".zone").empty();
		$(".zone").append(html);
	});
}
var mp;
function map(city, data) {
	// 百度地图API功能
	mp = new BMap.Map("map");
	mp.centerAndZoom(city, 13);
	mp.enableScrollWheelZoom();
	// 复杂的自定义覆盖物
//	function ComplexCustomOverlay(point, text, mouseoverText) {
//		this._point = point;
//		this._text = text;
//		this._overText = mouseoverText;
//	}
//	ComplexCustomOverlay.prototype = new BMap.Overlay();
//	ComplexCustomOverlay.prototype.initialize = function(map) {
//		this._map = map;
//		var div = this._div = document.createElement("div");
//		div.style.position = "absolute";
//		div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
//		div.style.backgroundColor = "#EE5D5B";
//		div.style.border = "1px solid #BC3B3A";
//		div.style.color = "white";
//		div.style.height = "25px";
//		div.style.padding = "2px";
//		div.style.lineHeight = "18px";
//		div.style.whiteSpace = "nowrap";
//		div.style.MozUserSelect = "none";
//		div.style.fontSize = "12px";
//		var span = this._span = document.createElement("span");
//		div.appendChild(span);
//		span.appendChild(document.createTextNode(this._text));
//		var that = this;
//
//		var arrow = this._arrow = document.createElement("div");
//		arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
//		arrow.style.position = "absolute";
//		arrow.style.width = "11px";
//		arrow.style.height = "10px";
//		arrow.style.top = "22px";
//		arrow.style.left = "10px";
//		arrow.style.overflow = "hidden";
//		div.appendChild(arrow);
//
//		div.onmouseover = function() {
//			this.style.backgroundColor = "#6BADCA";
//			this.style.borderColor = "#0000ff";
//			this.getElementsByTagName("span")[0].innerHTML = that._overText;
//			arrow.style.backgroundPosition = "0px -20px";
//		};
//
//		div.onmouseout = function() {
//			this.style.backgroundColor = "#EE5D5B";
//			this.style.borderColor = "#BC3B3A";
//			this.getElementsByTagName("span")[0].innerHTML = that._text;
//			arrow.style.backgroundPosition = "0px 0px";
//		};
//
//		mp.getPanes().labelPane.appendChild(div);
//
//		return div;
//	};
//	ComplexCustomOverlay.prototype.draw = function() {
//		var map = this._map;
//		var pixel = map.pointToOverlayPixel(this._point);
//		this._div.style.left = pixel.x - parseInt(this._arrow.style.left)
//				+ "px";
//		this._div.style.top = pixel.y - 30 + "px";
//	};

	$.each(data.data.dataList, function(i, o) {
		var photoPath = o.photo==null?"":"/Advertisement"+o.photo.path;

		var isApproval;
		if(o.isApproval){
			isApproval = '(已审批)';
		}else{
			isApproval="";
		}
		var sContent =
			"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+o.name+""+isApproval+"" + 
			' <a href="javascript:void(0);" onclick="showVideo('+o.id+')"  style="float:right;margin:4px">视频</a></h4>'+
			"<img style='float:right;margin:4px' id='imgDemo' src='"+photoPath+"' width='139' height='104' title=''/>" + 
			"<div style='width:450px; '>"+
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>地址:"+o.address+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>联系人:"+o.contacts+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>电话:"+o.tel+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>位置简介:"+o.aboutAddress+"</p>" + 
			//"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>关注度:"+o.hot+"点击量</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>媒体价:"+o.price+"元;  性价比:"+o.cost+"</p>" + 
			"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>可视时长:"+o.lookTime+"秒; 可视路线长:"+o.lookLong+"米</p>" + 
			"</div>";
		var infoWindow = new BMap.InfoWindow(sContent);
		var point = new BMap.Point(o.lon,o.lat);
		var marker = new BMap.Marker(point);
		mp.addOverlay(marker);
		marker.addEventListener("click", function(){          
			   this.openInfoWindow(infoWindow);
			   //图片加载完毕重绘infowindow
			   document.getElementById('imgDemo').onload = function (){
				   infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
			   };
			});

//		var txt = o.name, mouseoverTxt = o.address;
//		// 将地址解析结果显示在地图上,并调整地图视野
//		var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(o.lon,
//				o.lat), o.name, mouseoverTxt);
//		mp.addOverlay(myCompOverlay);

	});
}

//function showVideo(id){
//	$("#map").hide();
//	$("#video").fadeIn("slow");
//	$.post("video_loadVideo",{"parentId":id},function(data){
//		if(data.status){
//			$("video").attr("src","/Advertisement"+data.data.videoPath);
//		}else{
//			$("video").attr("src","");
//		}
//	});
//}

function showVideo(id){
	$("#map").css("width","40%");
	$("#video").css("width","60%");
	$("video").css("width","100%");
	$.post("video_loadVideo",{"parentId":id},function(data){
	if(data.status){
		$("video").attr("src","/Advertisement"+data.data.videoPath);
	}else{
		$("video").attr("src","");
	}
});
}

function showMap(){
	$("#map").css("width","100%");
	$("#video").css("width","0%");
	$("video").css("width","0%");
}

function chooseCity(){
	
}