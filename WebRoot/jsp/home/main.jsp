<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//List<String> list=(List<String>)request.getAttribute("list");
	String  list=(String)request.getAttribute("str");
	String  list1=(String)request.getAttribute("str1");
	String  list2=(String)request.getAttribute("str2");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>智慧安全登监控系统</title>
<style type="text/css">
  div {font-size: 16px;}
</style>
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


				
			
			
			
			<div id="allmap"  ></div>
			<ul class="clearfix list_num">
				<li class="fl list-item" >
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
	var point = new BMap.Point(116.404, 39.915);
	map.centerAndZoom(point, 15);
	
	
	var pointArray = new Array();
	var j=0
	//正常电梯
	var str=<%=list.toString()%>;	
	for(var i=0;i<str.length;i++){
		var icons = "<%=path %>/img/green1.png"; //这个是你要显示坐标的图片的相对路径 
		var markers = new BMap.Marker(new BMap.Point(str[i][0], str[i][1])); //lng为经度,lat为纬度 
		var icon = new BMap.Icon(icons, new BMap.Size(20, 40)); //显示图标大小 
		markers.setIcon(icon);//设置标签的图标为自定义图标
		map.addOverlay(markers);    //增加点
		pointArray[j] = new BMap.Point(str[i][0], str[i][1]);
		markers.addEventListener("click",attribute);
		j++;
	}
	
	//故障电梯
	var str1=<%=list1.toString()%>;	
	for(var i=0;i<str1.length;i++){
		var icons = "<%=path %>/img/red.png"; //这个是你要显示坐标的图片的相对路径 
		var markers = new BMap.Marker(new BMap.Point(str1[i][0], str1[i][1])); //lng为经度,lat为纬度 
		var icon = new BMap.Icon(icons, new BMap.Size(20, 40)); //显示图标大小 
		markers.setIcon(icon);//设置标签的图标为自定义图标
		map.addOverlay(markers);    //增加点
		pointArray[j] = new BMap.Point(str1[i][0], str1[i][1]);
		markers.addEventListener("click",attribute);
		j++;
	}
	//离线电梯
	var str2=<%=list2.toString()%>;	
	for(var i=0;i<str2.length;i++){
		var icons = "<%=path %>/img/orange.png"; //这个是你要显示坐标的图片的相对路径 
		var markers = new BMap.Marker(new BMap.Point(str2[i][0], str2[i][1])); //lng为经度,lat为纬度 
		var icon = new BMap.Icon(icons, new BMap.Size(20, 40)); //显示图标大小 
		markers.setIcon(icon);//设置标签的图标为自定义图标
		map.addOverlay(markers);    //增加点
		pointArray[j] = new BMap.Point(str2[i][0], str2[i][1]);
		markers.addEventListener("click",attribute);
		j++;
	}
	//让所有点在视野范围内
	
	map.setViewport(pointArray);
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	
	
	
	//点击地图标注，弹出电梯信息
	function attribute(e){
		var p = e.target;
		//alert("当前位置是" + p.getPosition().lng + "," + p.getPosition().lat);    
		var point = new BMap.Point(p.getPosition().lng,p.getPosition().lat);
		var marker = new BMap.Marker(point);  // 创建标注
		//map.addOverlay(marker);              // 将标注添加到地图中
		map.centerAndZoom(point, 15);
		var label=p.getPosition().lng + "," + p.getPosition().lat;
		var opts = {
		  width : 200,     // 信息窗口宽度
		  height: 150,     // 信息窗口高度
		  title : "<p style='font-size:20px;text-align: center;'>电梯信息</p>" , // 信息窗口标题
		  enableMessage:false,//设置允许信息窗发送短息
		}
		var txt="";
		$.ajax({
		     mtype:'post',
             url: "<%=path%>/indexAction.do?method=findById",
             data: "label="+label,
             success: function(data){
			         txt+="&nbsp;&nbsp;&nbsp;注册号：<span style='color:#00F' onclick='findById("+data.id+");'> "+data.registerid+"</span><br/>";
			         txt+="&nbsp;&nbsp;&nbsp;识别码："+data.distinguishid+"<br/>";
			         txt+="电梯品牌："+data.brand+"<br/>";
			         txt+="电梯型号："+data.model+"<br/>";
			         txt+="使用单位："+data.useUnitName+"<br/>";
			         txt+="安装地点："+data.installPlace+"<br/>";
			         var infoWindow = new BMap.InfoWindow(txt, opts);  // 创建信息窗口对象 
					map.openInfoWindow(infoWindow,point); //开启信息窗口
             }
   		 });
		
	}	
	  //编辑
		  function findById(id){
    		  window.location.href="<%=path %>/recordAction.do?method=findByMonitor&id="+id;
         }
	</script>
</html>
