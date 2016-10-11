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
 		<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" href="<%=path %>/css/fault/fault_index.css" type="text/css"></link></head>
	<script type="text/javascript">
	   function toMain(flag){
		   if(flag==1){
			   window.parent.main.location="<%=path%>/regionAction.do?method=query";
		   }
		   if(flag==2){
			   window.parent.main.location="<%=path%>/jsp/dagl/region/addRegion.jsp";
		   }
	   }
	   $(document).ready(function() {
                    $(".real_time   li").click(function() {
                        $(".active").removeClass("active");
                        $(this).addClass("active");
                    });
                });
	</script>
	<body>
	
		<div class="wrap clearfix">
			
			<div class="con fl">
				<ul class="real_time  clearfix"  >
					<li class="active"  style="width: 15%;float: left;"     onclick="toMain('1')">
						<a href="javascript:void(0);"  >区域信息</a>
					</li>
					<li style="width: 15%;float: left;"     onclick="toMain('2')">
						<a href="javascript:void(0);"  >电梯信息</a>
					</li>
					<li style="width: 15%;float: left;"     onclick="toMain('2')">
						<a href="javascript:void(0);"  >电梯网关信息</a>
					</li>
					<li style="width: 15%;float: left;"   onclick="toMain('2')">
						<a href="javascript:void(0);"  >使用单位信息</a>
					</li>
					<li style="width: 15%;float: left;"   onclick="toMain('2')">
						<a href="javascript:void(0);"  >维保单位信息</a>
					</li>
				</ul>
				</div>
		</div>
	</body>
		</html>
