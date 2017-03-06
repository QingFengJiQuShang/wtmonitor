<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<form id="form" action="<%=path %>/alarmAction.do?method=updateEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">人工接警</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">新增人工接警</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">电梯注册号&nbsp;:&nbsp;</label>
						<input type="hidden" id="id"  name="alarm.id"  value="${list.id}"/>
						<input type="hidden" id="elevatorId"  name="alarm.elevatorId.id"  value="${list.elevatorId.id}"/>
						<input type="text" id="elevatorId1"  value="${list.elevatorId.registerid}"  readonly="readonly"   onclick="selectElevator('elevatorId','elevatorId1','place')"/>
					</p>
					<p class="fill">
						<label for="name">识别号&nbsp;:&nbsp;</label>
						<input type="text" id="distinguishid"  readonly="readonly"  value="${list.elevatorId.distinguishid}" />
					</p>
					<p class="fill">
						<label for="name">使用单位&nbsp;:&nbsp;</label>
						<input type="text" id="useUnitName"  readonly="readonly"  value="${list.elevatorId.useUnitId.name}" />
					</p>
					<p class="fill">
						<label for="logn">电梯安装地址&nbsp;:&nbsp;</label>
						<input type="text" id="place"  value="${list.elevatorId.installPlace}"   readonly="readonly"    />
					</p>
					<p class="fill">
						<label for="man">接警日期&nbsp;:&nbsp;</label>
						<input type="text" id="time"  name="time"  value="<fmt:formatDate value="${list.time}"  pattern='yyyy-MM-dd'/>"/>
					</p>
					<p class="fill">
						<label for="phone">报警人&nbsp;:&nbsp;</label>
						<input type="text" id="alarmPerson"  name="alarm.alarmPerson"  value="${list.alarmPerson}"/>
					</p>
					<p class="fill">
						<label for="area">报警人电话&nbsp;:&nbsp;</label>
						<input type="text" id="phone"  name="alarm.phone"  value="${list.phone}" />
					</p>
					<p class="fill">
						<label for="area">故障问题&nbsp;:&nbsp;</label>
						<input type="text" id="fault"  name="alarm.fault" value="${list.fault}" />
					</p>
					<p class="fill">
						<label for="area">故障描述&nbsp;:&nbsp;</label>
						<input type="text" id="describe"  name="alarm.describe"  value="${list.describe}"/>
					</p>
					<p class="or clearfix">
						<input type="button"  value="保存"  onclick="add();">
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>

			</div>
		</div>
		</form>
	</body>
	    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/selectUi.js"></script></head>
	    <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     $(function() {
		$("#time").on("click", function(e) {
			e.stopPropagation();
			$(this).lqdatetimepicker({
				css: 'datetime-day',
				dateType: 'D',
				selectback: function() {}
			});
		});
		
	});
     </script>
</html>
