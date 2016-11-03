<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
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
	
		<div class="con">
			<p class="select" >
				<select id="cmbProvince" name="cmbProvince"></select>
				<select id="cmbCity" name="cmbCity"></select>
				<select id="cmbArea" name="cmbArea"  style="display: none;"></select>
			</p>
			<html>


				
			
			
			
			<div id="allmap"></div>
			<ul class="clearfix list_num">
				<li class="fl list-item">
					<p class="name">在线电梯电梯数量</p>
					<p class="num">10</p>
					<p>
						<img src="<%=path %>/img/blue.png" alt="" />	
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">离线数量</p>
					<p class="num">6</p>
					<p>
						<img src="<%=path %>/img/gray.png" alt="" />						
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">故障数量</p>
					<p class="num">5</p>
					<p>
						<img src="<%=path %>/img/red_small.png" alt="" />	
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">维保过期数量</p>
					<p class="num">3</p>
					<p>
						<img src="<%=path %>/img/rose_red.png" alt="" />	
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">未年检电梯数量</p>
					<p class="num">6</p> 
					<p>
						<img src="<%=path %>/img/orange_small.png" alt="" />	
					</p>
				</li> 
				<!-- 
				<li class="fl list-item">
					<p class="name">值班人</p>
					<p class="num">10</p>
					<p>
						<img src="<%=path %>/img/sel.png"/>	
					</p>
				</li> -->
			</ul>
		</div>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/PCASClass.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/ssq.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=9gdzSjQZTjIbIGLxFnAOnxwa"></script>
	<script type="text/javascript">
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
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		addressInit('cmbProvince', 'cmbCity', 'cmbArea');
	
	</script>
</html>
