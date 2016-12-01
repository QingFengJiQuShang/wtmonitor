<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>维保单位</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
	</head>

	<body>
	<form id="form" action="<%=path %>/maintenanceUnitAction.do?method=addEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">维保单位</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">新增维保单位</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">维保单位名称&nbsp;:&nbsp;</label>
						<input type="text" id="name"  name="maintenanceUnit.name"/>
					</p>
					<p class="fill">
						<label for="man">负责人&nbsp;:&nbsp;</label>
						<input type="text" id="liaisons"  name="maintenanceUnit.liaisons"/>
					</p>
					<p class="fill">
						<label for="phone">负责人电话&nbsp;:&nbsp;</label>
						<input type="text" id="phone"  name="maintenanceUnit.phone"/>
					</p>
					<p class="fill">
						<label for="address">维保单位办公地址&nbsp;:&nbsp;</label>
						<input type="text" id="address"  name="maintenanceUnit.address"/>
					</p>
					<p class="fill">
						<label for="address">公司代码&nbsp;:&nbsp;</label>
						<input type="text" id="code"  name="maintenanceUnit.code"/>
					</p>
					<p class="fill">
						<label for="legal">法人&nbsp;:&nbsp;</label>
						<input type="text" id="legal"  name="maintenanceUnit.corporation"/>
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
