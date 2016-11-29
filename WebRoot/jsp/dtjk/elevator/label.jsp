<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id=request.getParameter("id");		
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>智慧安全登监控系统</title>

<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/map/map.css" />
		</head>
	
	<body>
	
			
			<div id="allmap"></div>

	</body>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=9gdzSjQZTjIbIGLxFnAOnxwa"></script>
	<script type="text/javascript">
		// 百度地图API功能
		var map = new BMap.Map("allmap");
		var labe=window.parent.document.getElementById("<%=id%>").value;
		if(labe!=""){
			var lab=labe.split(",");
			map.centerAndZoom(new BMap.Point(lab[0], lab[1]), 14);
			var markers = new BMap.Marker(new BMap.Point(lab[0], lab[1])); //lng为经度,lat为纬度 
			map.enableScrollWheelZoom(true);
			map.addOverlay(markers);
		}else{
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r){
				if(this.getStatus() == BMAP_STATUS_SUCCESS){
					var mk = new BMap.Marker(r.point);
					//map.addOverlay(mk);
					map.panTo(r.point);
					//alert('您的位置：'+r.point.lng+','+r.point.lat);
					
					var point = new BMap.Point(r.point.lng,r.point.lat);
					map.centerAndZoom(point,12);
					var geoc = new BMap.Geocoder();    
					
					geoc.getLocation(point, function(rs){
					var addComp = rs.addressComponents;
					
					var address= addComp.province+addComp.city+addComp.district+addComp.street+addComp.streetNumber;	
					});        
				}
				else {
					alert('failed'+this.getStatus());
				}        
			},{enableHighAccuracy: true})
		}
		
	
		//鼠标 点击定位
		map.addEventListener("click",function(e){
			//alert(e.point.lng + "," + e.point.lat);
			window.parent.document.getElementById("<%=id%>").value=e.point.lng + "," + e.point.lat;
			window.parent.JqueryDialog.Close();
		});
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	</script>
</html>