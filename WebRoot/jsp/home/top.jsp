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
	
		<div class="wrap clearfix">
			
			<div class="con fl">
				<ul class="real_time clearfix">
					<li>
						<a href="javascript:void(0);"  >首页</a>
					</li>
				</ul>
				</div>
		</div>
	</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=9EbOkDkbAG1dILns7FEhnPHUBGvIVBW8"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
		<script src="<%=path%>/js/map/map.js" type="text/javascript" charset="utf-8"></script>
		
</html>
