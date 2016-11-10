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
    
    <title>维保记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list_add.css" />
		
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
		
	</head>

	<body>
		<div class="con">
			<p class="user">维保记录</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">查看维保记录</p>
				
				<div class="table_con">
					<p class="fill">
						<label for="user">电梯注册号&nbsp;:&nbsp;</label>${list.elevatorId.registerid}
						
					</p>
					<p class="fill">
						<label for="code">识别码&nbsp;:&nbsp;</label>${list.elevatorId.distinguishid}

					</p>
					<p class="fill">
						<label for="unit">电梯使用单位&nbsp;:&nbsp;</label>${list.useUnitId.name }
					</p>
					<p class="fill">
						<label for="place">电梯安装地点&nbsp;:&nbsp;</label>${list.elevatorId.installPlace }
					</p>
					<p class="fill">
						<label for="datetimepicker3">维保日期&nbsp;:&nbsp;</label><fmt:formatDate value='${list.time}' pattern='yyyy-MM-dd'/>
					</p>
					<p class="fill">
						<label for="wb_unit">维保单位&nbsp;:&nbsp;</label>${list.unitId.name}
					</p>
					<p class="fill">
						<label for="wb_man">维保人&nbsp;:&nbsp;</label>${list.userId.name}
					</p>
					<p class="fill">
						<label for="wb_con">维保内容&nbsp;:&nbsp;</label>${list.content}
					</p>
					<p class="fill">
						<label for="beizhu">备注&nbsp;:&nbsp;</label>${list.remarks}
					</p>
					<p class="or clearfix">
						<input type="button"  value="修改"   onclick="findById('${list.id}','1');" >
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/dtjk/maintenanceRecords.js" type="text/javascript" charset="utf-8"></script>
	

</html>
