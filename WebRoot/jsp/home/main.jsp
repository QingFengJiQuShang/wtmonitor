<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		
		</head>
	
	<body>
	
		<div class="con"  id="con">
			<p class="select " >
				区域：<select id="province" name="province"  onclick="theLocation('province');"></select>
				<select id="city" name="city"	 onclick="theLocation('city');"></select>
				<select id="area" name="area"  onclick="theLocation('area');" ></select>
				地址：<input type="text" id="suggestId" size="20" value="" style="width:150px;height: 30px;" />
				
				注册号：<input type="text" id="registerid"  oninput="byId();"  size="20" value="" style="width:150px;height: 30px;" />
			</p>
			<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
			
			<div id="allmap"  ></div>
			
	    <ul class="clearfix list_num">
				<li class="fl list-item" >
					<p class="name">在线电梯电梯数量</p>
					<p class="num">${index.normalNum}</p>
					<p>
						<img src="<%=path %>/img/green_small.png" alt="" />	
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">离线数量</p>
					<p class="num">${index.offLineNum}</p>
					<p>
						<img src="<%=path %>/img/gray.png" alt="" />						
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">故障数量</p>
					<p class="num">${index.faultNum}</p>
					<p>
						<img src="<%=path %>/img/red_small.png" alt="" />	
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">维保数量</p>
					<p class="num">${index.maintenanceNum1}</p>
					<p>
						<img src="<%=path %>/img/black_small.png" alt="" />	
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">维保过期数量</p>
					<p class="num">${index.maintenanceNum}</p>
					<p>
						<img src="<%=path %>/img/orange_small.png" alt="" />	
					</p>
				</li>
				<li class="fl list-item">
					<p class="name">未年检电梯数量</p>
					<p class="num">${index.yearlyNum}</p> 
					<p>
						<img src="<%=path %>/img/rose_red.png" alt="" />	
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
			<div class="table_con" >
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th class="all">
									<i></i>
								</th>
							<th>序列</th>
								<th>电梯注册号</th>
								<th>识别码</th>
								<th>使用单位</th>
								<th>安装地址</th>
								<th>故障时间</th>
								<th>故障类型</th>
								<th>故障状态</th>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""><input type="hidden" value="${list.id}" /></i>
									</td>
									<td>${s.index + 1 }</td>
									<td><a href="<%=path %>/faultAction.do?method=findById&id=${list.id}&flag=2"   style="color: blue; ">${list.registerid}</a></td>
									<td>${list.distinguishid }</td>
									<td>${list.useUnitName }</td>
									<td>${list.place }</td>
									<td><fmt:formatDate value="${list.happenTime }"  pattern='yyyy-MM-dd HH:mm:ss'/></td>
									<td>${list.faultType}</td>
									<td>${list.state }</td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
		</div>
	
		
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/PCASClass.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/ssq.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=9gdzSjQZTjIbIGLxFnAOnxwa"></script>
	
	<script type="text/javascript">
	addressInit('province', 'city', 'area','${province}','${city}','请选择');
		// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.404, 39.915);
	map.centerAndZoom(point, 12); 
	
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
	
	//根据城市定位
	function theLocation(id){
			var city = document.getElementById(id).value;
			if(city != ""){
				map.centerAndZoom(city,11);      // 用城市名设置地图中心点
			}
		}
	
	//输入框提示
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}
	
	
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		//map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	
	
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
			         txt+="维保单位："+data.maintenanceUnitName+"<br/>";
			         txt+="安装地点："+data.installPlace+"<br/>";
			         var infoWindow = new BMap.InfoWindow(txt, opts);  // 创建信息窗口对象 
					map.openInfoWindow(infoWindow,point); //开启信息窗口
             }
   		 });
		
	}	
	  //编辑
		  function findById(id,flag){
    		  window.location.href="<%=path %>/recordAction.do?method=findByMonitor&id="+id+"&flag=0";
         }
	  
	  //根据注册号查询
		  function byId(id,flag){
		  		var registerid = document.getElementById("registerid").value;
		         $.ajax({
				     mtype:'post',
		             url: "<%=path%>/indexAction.do?method=onlyRegisterid",
		             data: "registerid="+registerid,
		             success: function(rs){
		            	 if(rs!=null){
		            		var label= rs.label.split(",");
							var point = new BMap.Point(label[0], label[1]);
							map.centerAndZoom(point, 15); 
		            	 }
					        
		             }
		   		 });
	  
	  }
	  
	  
	  //区域
	  	addressInit('province', 'city', 'area');
	  
	  var height = $(window.document).height();
	  //alert(height);
		$('#allmap').css({
			"height":height*0.82
		});
		var height = $(window.document).height();
	</script>

</html>
