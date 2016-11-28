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
	<form id="form"   action="<%=path %>/elevatorAction.do?method=updateFlow"  method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">电梯流量</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改电梯流量</p>
				<div class="table_con">
					<p class="fill">
					<label for="user">电梯注册号&nbsp;:&nbsp;</label>
					<input type="hidden" id="elevatorId"  name="elevator.id"  value="${list.id}" />
					<input  name="elevator.registerid"  id="registerid" value="${list.registerid}" readonly="readonly"/>
				</p>
				<p class="fill">
					<label for="code">识别码&nbsp;:&nbsp;</label>
						<input type="text"  id="distinguishid"  name="elevator.distinguishid"  placeholder="请输入"  value="${list.distinguishid}" />
				</p>
				<p class="fill">
					<label for="unit">电梯使用单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="useUnitId"  name="elevator.useUnitId.id"    value="${list.useUnitId.id}" />
						<input type="text"  id="useUnitId1"   placeholder="请选择"   value="${list.useUnitId.name}"  readonly="readonly" />
					
				</p>
				<p class="fill">
					<label for="start_end">开始时间&nbsp;:&nbsp;</label>
					<input type="text"  class="Wdate"  name="flowStart"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  value="<fmt:formatDate value="${list.flowStart}"  pattern='yyyy-MM-dd'/>" >
				</p>
				<p class="fill">
					<label for="start_end">结束时间&nbsp;:&nbsp;</label>
					<input type="text"  class="Wdate"  	 name="flowEnd"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${list.flowEnd}"  pattern='yyyy-MM-dd'/>" >
				</p>
				<p class="fill">
					<label for="place">总流量额&nbsp;:&nbsp;</label>
					<input type="text" id="flowTotal" name="elevator.flowTotal"  value="${list.flowTotal}" />
				</p>
				<p class="fill">
					<label for="wb_unit">单月使用额&nbsp;:&nbsp;</label>
					<input type="text" id="flowNum" name="elevator.flowNum" value="${list.flowNum}" />
				</p>
				<p class="fill">
					<label for="wb_unit">剩余流量&nbsp;:&nbsp;</label>
					<input type="text" id="flowSurplus" name="elevator.flowSurplus" value="${list.flowSurplus}" />
				</p>
					<div class="keep clearfix">
				<!-- 	<input type="button"  class="fl"  value="保存"   onclick="add();"/> -->	
						<button class="fl"    onclick="add();">保存</button>
						<button class="fr"   onclick="history.go(-1); ">取消</button>
					</div>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     
     </script>
</html>
