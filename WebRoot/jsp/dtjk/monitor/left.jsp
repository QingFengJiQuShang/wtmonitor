<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电梯监控</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/dtjk/list_details.css" />
	</head>
<script type="text/javascript">
		 function myrefresh(){
			window.location.reload();
		}
		setTimeout('myrefresh()',10000); //指定10秒刷新一次s
</script>
	<body>
			<c:if test="${!empty records }">
				<div class="show clearfix" style="width: 100%;">
				
				
				<c:if test="${records.door=='关门'}">
						<p class="img fl">
				</c:if>
				<c:if test="${records.door=='开门'}">
						<p class="img fl" style="background-image: url(<%=path %>/img/dianti_close.png);">
				</c:if>
					
						<!--显示楼层-->
						<span class="lou">
						   <c:if test="${records.direction=='上'}">
								<i class="up"></i>	
							</c:if>
							<c:if test="${records.direction=='下'}">
								<i class="down"></i>	
							</c:if>
							
							${records.floor}
						</span>
						<!--显示上下-->
						
						<c:if test="${records.direction=='上'}">
								<span class="or dianti_up"></span>
							</c:if>
							<c:if test="${records.direction=='下'}">
								<span class="or dianti_down"></span>
							</c:if>
						<span class="state no"></span>
						<span class="state yes"></span>
					</p>
					<div class="ifno fr">
						<ul class="list_dainti clearfix" id="tabTit">
							<li class="list-item "></li>
							<li class="list-item "></li>
							<li class="list-item "></li>
						</ul>
				</div>
		</div>
		</c:if>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/dtjk/lsit_details.js" type="text/javascript" charset="utf-8"></script>
</html>
