<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>网关信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
			<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
			<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		  
	</head>

	<body>
	<form id="form" action="<%=path %>/gatewayAction.do?method=addEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">网关信息</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">新增网关信息</p>
				<div class="table_con">
					
					<p class="fill">
						<label for="name">网关序列号&nbsp;:&nbsp;</label>
						<input type="hidden"  name="gateway.elevatorId"  value="${elevatorId}">
						<input type="text" id="serialNumber"  name="gateway.serialNumber"  />
					</p>
					<p class="fill">
						<label for="logn">网关类型&nbsp;:&nbsp;</label>
						<select  name="gateway.type"  id="type">
							<option value="">请选择</option>
							<option value="视频版">视频版</option>
							<option value="语音版">语音版</option>
						</select>
					</p>
					<p class="fill">
						<label for="man">硬件版本&nbsp;:&nbsp;</label>
						<input type="text" id="hardware"  name="gateway.hardware"  />
					</p>
					<p class="fill">
						<label for="phone">软件版本&nbsp;:&nbsp;</label>
						<input type="text" id="software"  name="gateway.software"  />
					</p>
					<p class="fill">
						<label for="area">SIM卡号&nbsp;:&nbsp;</label>
						<input type="text" id="sim"  name="gateway.sim"  />
					</p>
					<p class="fill">
						<label for="area">上报周期&nbsp;:&nbsp;</label>
						<input type="text" id="report"  name="gateway.report"  />
					</p>
					<p class="fill">
						<label for="area">总楼层&nbsp;:&nbsp;</label>
						<input type="text" id="floor"  name="gateway.floor"  />
					</p>
					<p class="fill">
						<label for="area">地上楼层&nbsp;:&nbsp;</label>
						<input type="text" id="upper"  name="gateway.upper"  />
					</p>
					<p class="fill">
						<label for="area">地下楼层&nbsp;:&nbsp;</label>
						<input type="text" id="lower"  name="gateway.lower"  />
					</p>
					<p class="fill">
						<label for="area">设定速度&nbsp;:&nbsp;</label>
						<input type="text" id="speed"  name="gateway.speed"  />
					</p>
					<p class="fill">
						<label for="area">平层间距&nbsp;:&nbsp;</label>
						<input type="text" id="spacing"  name="gateway.spacing"  />
					</p>
					<p class="fill">
						<label for="area">联网方式&nbsp;:&nbsp;</label>
						<select  name="gateway.networking"  id="networking">
							<option value="">请选择</option>
							<option value="GPRS">GPRS</option>
							<option value="WIFI">WIFI</option>
						</select>
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
		 	$('#form').submit();
     }
     
     </script>
</html>
