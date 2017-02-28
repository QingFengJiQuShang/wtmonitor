<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>电梯流量</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />

	</head>

	<body>
		<div class="con">
				<p class="user">电梯服务费</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
					<p class="add">电梯服务费登记</p>
				<div class="table_con">
					<p class="fill">
					<label for="user">电梯注册号&nbsp;:&nbsp;</label>
					<input type="hidden" id="elevatorId"  name="service.elevatorId.id"  value="${list.id}" />
						${list.elevatorId.registerid}
				</p>
				<p class="fill">
					<label for="code">识别码&nbsp;:&nbsp;</label>
					${list.elevatorId.distinguishid}
				</p>
				<p class="fill">
					<label for="unit">使用单位&nbsp;:&nbsp;</label>
					${list.elevatorId.useUnitId.name}
				</p>
				<p class="fill">
					<label for="unit">物业单位&nbsp;:&nbsp;</label>
					${list.elevatorId.propertyUnitId.name}
				</p>
				<p class="fill">
					<label for="unit">维保单位&nbsp;:&nbsp;</label>
					${list.elevatorId.maintenanceUnitId.name}
				</p>
				<p class="fill">
					<label for="start_end">服务开始时间&nbsp;:&nbsp;</label>
					<fmt:formatDate value='${list.startTime}' pattern='yyyy-MM-dd'/>
				</p>
				<p class="fill">
					<label for="start_end">服务结束时间&nbsp;:&nbsp;</label>
					<fmt:formatDate value='${list.endTime}' pattern='yyyy-MM-dd'/>
				</p>
				<p class="fill">
					<label for="place">服务金额&nbsp;:&nbsp;</label>
					${list.money}
				</p>

				<p class="fill">
					<label for="wb_unit">服务类型&nbsp;:&nbsp;</label>
					${list.type}
				</p>
					<p class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"dtjk_fwf_update")) {%>
						<input type="button"  value="修改"   onclick="findById('${list.id}','1');" >
              <%}%>
            <input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>

			</div>
		</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/dtjk/service.js" type="text/javascript" charset="utf-8"></script>

</html>
