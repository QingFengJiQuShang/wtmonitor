<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
			
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

 function sign(){
        	window.parent.parent.sign();
	}
</script>

	
	<body>
	
		<div class="top clearfix">
			<p class="fl">
				<img src="<%=path %>/img/logo.png" />&nbsp;|&nbsp;&nbsp;电梯运行安全物联系统
			</p>
			<p class="fr out" onclick="sign();">
				<img src="<%=path %>/img/sign_out.png" class="manage" alt="" />退出
			</p>
			<p class="fr userName">
				<img src="<%=path %>/img/manage.png" alt="" class="manage" />您好，<%=user.getName() %>
			</p>
		</div>
		
	</body>

</html>
