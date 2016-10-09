// 百度地图API功能
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(118.10388605, 24.48923061), 14);

var local = new BMap.LocalSearch(map, {
	renderOptions: {
		map: map
	}
});
local.searchInBounds("电梯", map.getBounds());

map.addEventListener("dragend", function() {
	map.clearOverlays();
	local.searchInBounds("电梯", map.getBounds());
});
//清除搜索
$(".clean").click(function() {
	$(this).siblings("input").val("")
})
