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
 		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/map/map.css" />
		</head>
	<script type="text/javascript">
	   function toMain(flag){
		   if(flag==1){
			   window.parent.rightFrame.location="<%=path%>/jsp/home/right.jsp";
		   }
		   if(flag==2){
			   window.parent.rightFrame.location="<%=path%>/jsp/dagl/right.jsp";
		   }
	   }
	   
	    $(document).ready(function() {
                    $(".list  li").click(function() {
                        $(".active").removeClass("active");
                        $(this).addClass("active");
                    });
                });
	   
	</script>
	<body>
	
		<div class="wrap clearfix">
			<ul class="list fl">
				<li class="active">
					<a href="javascript:void(0);"   onclick="toMain('1')">首页</a>
				</li>
				<li>
					<a href="javascript:void(0);"  onclick="toMain('1')">电梯监控</a>
				</li>
				<li>
					<a href="javascript:void(0);" onclick="toMain('1')">故障处理</a>
				</li>
				<li>
					<a href="javascript:void(0);"  onclick="toMain('1')">统计分析</a>
				</li>
				<li>
					<a href="javascript:void(0);"  onclick="toMain('2')">档案管理</a>
				</li>
				<li>
					<a href="javascript:void(0);"  onclick="toMain('1')">系统管理</a>
				</li>
				<li>
					<a href="javascript:void(0);"   onclick="toMain('1')">日志</a>
				</li>
			</ul>
			
		</div>
	</body>

</html>
