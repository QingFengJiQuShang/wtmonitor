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
function sign(){
	if(window.confirm('您确定要退出登录吗？')){
		window.location.href="<%=path%>/userLogout";	
	}
}
</script>

	
	<body>
	
<iframe width="100%" height="60" id="topFrame" name="topFrame" frameborder=0 scrolling=no src="<%=path%>/jsp/top.jsp" ></iframe>
<iframe width="100%" height="550" id="mainFrame" name="mainFrame" frameborder=0 scrolling=no src="<%=path%>/jsp/main.jsp"  style="margin-top: -4px; margin-bottom: -20px;"></iframe>
<iframe width="100%" height="0" frameborder=0 src="<%=path%>/jsp/bottom.jsp" scrolling="no" style="margin-top: -4px;" ></iframe>

	</body>

</html>
