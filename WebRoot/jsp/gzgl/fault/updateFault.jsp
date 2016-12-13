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
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/calendar.css" />
		
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />

		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		
	</head>

	<body>
		<form id="form" action="<%=path %>/faultAction.do?method=updateEntity" method="post" >
		<div class="con">
			<p class="user">当前故障</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改当前故障</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">电梯注册号&nbsp;:&nbsp;</label>
						<input type="hidden"  id="id"  name="fault.id"  value="${list.id}">
						<input type="hidden"   name="fault.elevatorId.id"  value="${list.elevatorId.id}"> 
						<input type="hidden"   name="fault.dutyId.id"  value="${list.dutyId.id}"> 
						
						<input    value="${list.elevatorId.registerid}"  readonly="readonly"> 
						
					</p>
					<p class="fill">
						<label for="logn">电梯安装地址&nbsp;:&nbsp;</label>
						<input    value="${list.elevatorId.installPlace}"  readonly="readonly"> 
					</p>
					<p class="fill">
						<label for="man">故障发生时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   name="happenTime"   value="<fmt:formatDate value="${list.happenTime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"> 
						
					</p>
					<p class="fill">
						<label for="man">接警时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="alarmTime"  name="alarmTime"   value="<fmt:formatDate value="${list.alarmTime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"    onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
					</p>
					<p class="fill">
						<label for="man">接警类型&nbsp;:&nbsp;</label>
						<select name="fault.type" id="type"  >
							<option value="">请选择</option>
							<option <c:if test="${list.type=='人工接警'}">selected="selected" </c:if> value="人工接警">人工接警</option>
							<option <c:if test="${list.type=='自动接警'}">selected="selected" </c:if> value="自动接警">自动接警</option>
						</select>	
					</p>
					<p class="fill">
						<label for="area">故障问题&nbsp;:&nbsp;</label>
						<input type="text" id="faultType"  name="fault.faultType"  value="${list.faultType}"  readonly="readonly"/> 
						
					</p>
					<p class="fill">
						<label for="man">故障信息&nbsp;:&nbsp;</label>
						<textarea  id="fault"  name="fault.fault"   rows="3" cols="30">${list.fault}</textarea>
						
					</p>
					<p class="fill">
						<label for="man">救援到达时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="arriveTime"  name="arriveTime"   value="<fmt:formatDate value="${list.arriveTime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
					</p>
					<p class="fill">
						<label for="man">救援成功时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="successTime"  name="successTime"   value="<fmt:formatDate value="${list.successTime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
					</p>
					<p class="fill">
						<label for="man">困人数量&nbsp;:&nbsp;</label>
						<input  id="numbers"  name="fault.numbers"   value="${list.numbers}" > 
						
					</p>
					<p class="fill">
						<label for="man">施救单位&nbsp;:&nbsp;</label>
							<select multiple="multiple" style="WIDTH: 10em; height: 100px;" name="unitId"  id="unitId"  >
								<c:forEach items="${unitId}" var="unitId" varStatus="s">
									<option value="${unitId.id }" selected="selected">${unitId.name }</option>
								</c:forEach>
								
					    	</select>
					    	<input style="width: 60px;" type="button" id="msdept" value="选择" onClick="selectRescueUnit('handle','unitId')">
					    	<input style="width: 60px;" type="button" id="msuser2" value="移除" onClick="closeMultiRescueUnit('unitId')">
						
					</p>
					<p class="fill">
						<label for="man">处理信息&nbsp;:&nbsp;</label>
						<input  id="handle"  name="fault.handle"   value="${list.handle}" > 
					</p>
					<p class="fill">
						<label for="man">状态&nbsp;:&nbsp;</label>
						<select name="fault.state" id="state">
							<option value="">请选择</option>
							<option <c:if test="${list.state=='处理中'}">selected="selected" </c:if> value="处理中">处理中</option>
							<option <c:if test="${list.state=='已处理'}">selected="selected" </c:if> value="已处理">已处理</option>
							<option <c:if test="${list.state=='误报'}">selected="selected" </c:if> value="误报">误报</option>
						</select>	
					</p>
					<p class="or clearfix">
							<input type="button"  value="保存"   onclick="add();" >
							<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
						</p>
				</div>

			</div>
		</div>
	</body>
	    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/selectUi.js"></script></head>
	    <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
	 	<script src="<%=path%>/js/gzcl/fault.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
    
     </script>
</html>
