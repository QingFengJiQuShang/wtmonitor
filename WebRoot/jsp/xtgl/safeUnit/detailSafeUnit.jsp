<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

    <title>保险单位</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/safeUnit.js" type="text/javascript" charset="utf-8"></script>

	</head>

	<body>
		<div class="con">
			<p class="user">保险单位</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">查询保险单位</p>
				<div class="table_con">
					<p class="fill">
						<label for="logn">省&nbsp;:&nbsp;</label>${list.province}
					</p>
					<p class="fill">
						<label for="logn">市&nbsp;:&nbsp;</label>${list.city}
					</p>
					<p class="fill">
						<label for="logn">区&nbsp;:&nbsp;</label>${list.area}
					</p>
					<p class="fill">
					<label for="name">保险单位名称&nbsp;:&nbsp;</label>
						${list.name}
					</p>
					<p class="fill">
						<label for="man">联系人&nbsp;:&nbsp;</label>${list.liaisons }
					</p>
					<p class="fill">
						<label for="phone">联系人电话&nbsp;:&nbsp;</label>${list.phone }
					</p>
					<p class="fill">
						<label for="area">保险单位地址&nbsp;:&nbsp;</label>${list.address }
					</p>
					<p class="or clearfix">
						<%if(Authority.haveRigth(user.getId(),"dwgl_bxdw_update")) {%>
						<input type="button"  value="修改"    onclick="findById('${list.id}','1');" >
						<%} %>
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>

			</div>
		</div>
	</body>

</html>
