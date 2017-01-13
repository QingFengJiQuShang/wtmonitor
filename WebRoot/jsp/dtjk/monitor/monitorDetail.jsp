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

	<body>
		<div class="con">
			<div class="wrap">
				<p class="order">电梯监控</p>
				<p class="back"  onclick="history.go(-1); "><img src="<%=path %>/img/back.png" />返回</p>
				<iframe class="img fl" src="<%=path%>/recordAction.do?method=findByMonitor&id=${list.id}&flag=1" id="main"  name="main"  frameborder="0" scrolling="no" marginheight="0" marginwidth="0"  width="100%"  height="400"      style="margin-top: -2px; overflow-x:hidden;  ">
			
				</iframe>
				
				
							<p class="update clearfix">更新时间:<fmt:formatDate value='${records.foundTime}' pattern='yyyy-MM-dd HH:mm:ss'/> <button class="fr"  onclick="findRecord('${list.registerid}');">查看历史记录</button></p>
							<div class="details_info clearfix">
								<h3 class="fl">电梯信息</h3>
								<ul class="clearfix">
									<li class="fl">
										<p>注册代码&nbsp;:&nbsp;${list.registerid}</p>
										<p>电梯识别码&nbsp;:&nbsp;${list.distinguishid}</p>
										<p>使用单位&nbsp;:&nbsp;${list.useUnitId.name}</p>
										<p>安装地址&nbsp;:&nbsp;${list.installPlace}</p>
										<p>SIM卡号&nbsp;:&nbsp;${gateway.sim}</p>
									</li>
									<li class="fl">
										<p>网关编号&nbsp;:&nbsp;${gateway.serialNumber}</p>
										<p>物业单位&nbsp;:&nbsp;${list.propertyUnitId.name}</p>
										<p>负责人&nbsp;:&nbsp;${list.useUnitId.liaisons}</p>
										<p>维保单位&nbsp;:&nbsp;${list.maintenanceUnitId.name}</p>
										<p>维保人&nbsp;:&nbsp;${list.maintenanceUsersId.name}</p>
									</li>
								</ul>
								<h2 class="fr"  onclick="findElevator('${list.id}','2');">查看更多资料</h2>
							</div>
						</div>
					</li>
				</ul>				
			</div>
		</div>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/dtjk/lsit_details.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/dtjk/monitor.js" type="text/javascript" charset="utf-8"></script>

</html>
