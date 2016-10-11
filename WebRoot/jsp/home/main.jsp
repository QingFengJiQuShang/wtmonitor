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
				
				<div class="sech">
					<form action="" method="post">
						<p class="">
							<label class="fl">
								<input type="text" placeholder="请输入">
								<span class="clean"></span>
							</label>
							<button class="fl"></button>
						</p>
					</form>
					<div id="allmap"></div>
					<div class="over">
						<ul class="list-num clearfix">
							<li class="list-item fl">
								<p>在线电梯数量</p>
								<p>10</p>
							</li>
							<li class="list-item fl">
								<p>离线数量</p>
								<p>5</p>
							</li>
							<li class="list-item fl">
								<p>故障数量</p>
								<p>10</p>
							</li>
							<li class="list-item fl">
								<p>维保过期电梯数量</p>
								<p>10</p>
							</li>
							<li class="list-item fl">
								<p>未年检电梯数量</p>
								<p>10</p>
							</li>
							<li class="list-item fl">
								<p>值班人</p>
								<p>10</p>
							</li>
						</ul>
					</div>

				</div>
			</div>
		</div>
		</div>
	</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=9EbOkDkbAG1dILns7FEhnPHUBGvIVBW8"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
		<script src="<%=path%>/js/map/map.js" type="text/javascript" charset="utf-8"></script>
		
</html>
