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
				<div class="show clearfix">
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
							<li class="list-item  active">电梯信息</li>
							<li class="list-item ">电梯网关信息</li>
							<li class="list-item ">使用单位信息</li>
							<li class="list-item ">维保信息</li>
						</ul>
						<div class="info_con" id="tabCon">
							<!--电梯信息-->
							<div class="block">
								<table border="" cellspacing="" cellpadding="">
									<tbody>
										<tr>
											<td>电梯注册码</td>
											<td>${list.registerid}</td>
										</tr>
										<tr>
											<td>电梯识别码</td>
											<td>${list.distinguishid}</td>
										</tr>
										<tr>
											<td>电梯品牌</td>
											<td>${list.brand}</td>
										</tr>
										<tr>
											<td>注册状态</td>
											<td>${list.registerState}</td>
										</tr>
										<tr>
											<td>总层数</td>
											<td>${list.numbers}</td>
										</tr>
										<tr>
											<td>电梯类型</td>
											<td>${list.type}</td>
										</tr>
										<tr>
											<td>电梯型号</td>
											<td>${list.model}</td>
										</tr>
										<tr>
											<td>电梯状态</td>
											<td>${list.state}</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--电梯网关信息-->
							<div class="">
								<table border="" cellspacing="" cellpadding="">
									<tbody>
										<tr>
											<td>电梯网关类型</td>
											<td>${list.gatewayId.type}</td>
										</tr>
										<tr>
											<td>硬件版本</td>
											<td>${list.gatewayId.hardware}</td>
										</tr>
										<tr>
											<td>软件版本</td>
											<td>${list.gatewayId.software}</td>
										</tr>
										<tr>
											<td>sm卡号</td>
											<td>${list.gatewayId.sim}</td>
										</tr>
										<tr>
											<td>上报周期</td>
											<td>${list.gatewayId.report}</td>
										</tr>
										<tr>
											<td>网关设备序列号</td>
											<td>${list.gatewayId.serialNumber}</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--使用单位信息-->
							<div class="">
								<table border="" cellspacing="" cellpadding="">
									<tbody>
										<tr>
											<td>单位名称</td>
											<td>${list.useUnitId.name}</td>
										</tr>
										<tr>
											<td>单位类型</td>
											<td>${list.useUnitId.type}</td>
										</tr>
										<tr>
											<td>单位负责人</td>
											<td>${list.useUnitId.liaisons}</td>
										</tr>
										<tr>
											<td>负责人电话</td>
											<td>${list.useUnitId.phone}</td>
										</tr>
										<tr>
											<td>地址</td>
											<td>${list.useUnitId.address}</td>
										</tr>
										
									</tbody>
								</table>
							</div>
							<div class="">
								<table border="" cellspacing="" cellpadding="">
									<tbody>
										<tr>
											<td>单位名称</td>
											<td>${list.maintenanceUnitId.name}</td>
										</tr>
										<tr>
											<td>单位负责人</td>
											<td>${list.maintenanceUnitId.liaisons}</td>
										</tr>
										<tr>
											<td>负责人电话</td>
											<td>${list.maintenanceUnitId.phone}</td>
										</tr>
										<tr>
											<td>地址</td>
											<td>${list.maintenanceUnitId.address}</td>
										</tr>
										<tr>
											<td>组织代码</td>
											<td>${list.maintenanceUnitId.code}</td>
										</tr>
										<tr>
											<td>法人</td>
											<td>${list.maintenanceUnitId.corporation}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/dtjk/lsit_details.js" type="text/javascript" charset="utf-8"></script>
</html>
