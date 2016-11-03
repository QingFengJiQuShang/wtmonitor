<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitId=request.getParameter("unitId");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>维保人员</title>
    
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
		<div class="con">
			<p class="user">维保单位</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">查询维保人员</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">维保人名称&nbsp;:&nbsp;</label>${list.name }
					</p>
					<p class="fill">
						<label for="phone">维保人电话&nbsp;:&nbsp;</label>${list.phone }
					</p>
					<p class="fill">
						<label for="man">维保证编号&nbsp;:&nbsp;</label>${list.numbers }
					</p>
					
					<p class="fill">
						<label for="address">维保证有效期&nbsp;:&nbsp;</label>${list.validity }
					</p>
					<p class="fill">
						<label for="address">维保卡号&nbsp;:&nbsp;</label>${list.cardNumber }
					</p>
					<div class="keep clearfix">
						<button class="fl"  onclick="findById('${list.id}','1');">修改</button>
						<button class="fr"   onclick="history.go(-1); ">取消</button>
					</div>
				</div>

			</div>
		</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/maintenanceUsers.js" type="text/javascript" charset="utf-8"></script>

</html>
