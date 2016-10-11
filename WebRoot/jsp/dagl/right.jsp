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
	<iframe width="100%" height="50" id="top" name="top" frameborder=0 scrolling=no src="<%=path%>/jsp/dagl/top.jsp" ></iframe>
	<iframe width="100%" height="550" id="main" name="main" frameborder=0 scrolling=no src="<%=path%>/jsp/dagl/region/regionList.jsp"  style="margin-top: -4px; margin-bottom: -20px;"></iframe>
	
		</body>		
</html>
