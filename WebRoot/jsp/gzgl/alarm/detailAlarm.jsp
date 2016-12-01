<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人工接警</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list_add.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>

		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
		
	</head>

	<body>
		<div class="con">
			<p class="user">人工接警</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">查看人工接警</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">电梯注册号&nbsp;:&nbsp;</label>${list.elevatorId.registerid}
					</p>
					<p class="fill">
						<label for="logn">电梯安装地址&nbsp;:&nbsp;</label>${list.elevatorId.installPlace}
					</p>
					<p class="fill">
						<label for="man">接警日期&nbsp;:&nbsp;</label>
						<fmt:formatDate value="${list.time}"  pattern='yyyy-MM-dd'/>
					</p>
					<p class="fill">
						<label for="phone">报警人&nbsp;:&nbsp;</label>${list.alarmPerson}
					</p>
					<p class="fill">
						<label for="area">报警人电话&nbsp;:&nbsp;</label>${list.phone}
					</p>
					<p class="fill">
						<label for="area">故障问题&nbsp;:&nbsp;</label>${list.fault}
					</p>
					<p class="fill">
						<label for="area">故障描述&nbsp;:&nbsp;</label>${list.describe}
					</p>
					<p class="or clearfix">
							<%if(Authority.haveRigth(user.getId(),"gzgl_update")) {%>
							<input type="button"  value="修改"   onclick="findById('${list.id}','1');" >
							<%} %>
							<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
						</p>
				</div>

			</div>
		</div>
	</body>
	    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/selectUi.js"></script></head>
	    <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
	 	<script src="<%=path%>/js/gzcl/alarm.js" type="text/javascript" charset="utf-8"></script>

</html>
