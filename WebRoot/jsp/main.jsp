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
	
	<body >
			
<iframe width="10%" height="610" id="leftFrame" name="leftFrame" frameborder=0 scrolling=no src="<%=path%>/jsp/left.jsp" ></iframe>
<iframe width="90%" height="610" id="rightFrame" name="rightFrame" frameborder=0 scrolling=no src="<%=path%>/jsp/home/right.jsp"  style="margin-left: -4px; margin-right: -4px;"></iframe>
	
		
	</body>
</html>
