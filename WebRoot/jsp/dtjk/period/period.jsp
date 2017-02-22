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
    
    <title>电梯上报周期</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list_add.css" />
		<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
			<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		    <script src="<%=path%>/js/Share.js" type="text/javascript" charset="utf-8"></script>
	
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		
	</head>

	<body>
	<form id="form" action="<%=path %>/elevatorAction.do?method=updatePeriod" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">上报周期</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改上报周期</p>
				
				<div class="table_con">
					<p class="fill">
						<label for="user">上报周期(秒)&nbsp;:&nbsp;</label>
						<input type="hidden" id="elevatorId"  name="elevator.id"  value="${list.id}" />
						
						<input   id="period"  name="elevator.period"  value="${list.period}" />
						
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
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	
<script type="text/javascript">
     function add(){
		  if(showIsNumber("period","上报周期")){
    		 $('#form').submit();
    	 }
     }
      
</script>
</html>
