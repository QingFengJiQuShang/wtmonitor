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

</script>

	
	<body>
	
<iframe width="100%" height="50" id="topFrame" name="topFrame" frameborder=0 scrolling=no src="<%=path%>/jsp/top.jsp" ></iframe>
<iframe width="100%" height="540" id="mainFrame" name="mainFrame" frameborder=0 scrolling=no src="<%=path%>/jsp/main.jsp" ></iframe>
<iframe width="100%" height="54" frameborder=0 src="<%=path%>/jsp/bottom.jsp" scrolling="no" ></iframe>

	</body>

</html>
