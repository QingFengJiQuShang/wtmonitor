<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

    <title>使用单位</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
		<style type="text/css">
			.list_count {
				width: 90%;
				margin: 50px auto;
			}
			.list_count li {
				width: 14rem;
				height: 14rem;
				margin: 0 1.5%;
				-webkit-border-radius: 50%;
				-moz-border-radius: 50%;
				border-radius: 50%;
			}

			.list_count li p {
				color: #fff;
				height: 100px;
				text-align: center;
			}

			.list_count li p:nth-child(1) {
				font-size: 18px;
				line-height: 180px;
			}

			.list_count li p:nth-child(2) {
				font-size: 35px;
				line-height: 80px;
			}

			.list_count li:nth-child(1),
			.list_count li:nth-child(4) {
				background: #f9b64a;
			}

			.list_count li:nth-child(2) {
				background: #d683f3;
			}

			.list_count li:nth-child(3) {
				background: #6eb7ed;
			}
		</style>

	</head>

	<body>
		<div class="con" id="user">
			<p class="user">保险统计</p>
			<div class="warp">
				<ul class="clearfix list_count">
					<li class="fl">
						<p>保险电梯总数</p>
						<p>1105</p>
					</li>
					<li class="fl">
						<p>投保率</p>
						<p>5</p>
					</li>
					<li class="fl">
						<p>受保理赔率</p>
						<p>1%</p>
					</li>
					<li class="fl">
						<p>单台电梯最高理赔次数</p>
						<p>2</p>
					</li>
				</ul>
		</div>
	</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	  function findById(){
		 	 window.location="<%=path%>/jsp/Insurance/offPaul/offPaulList.jsp";
     }


	</script>

</html>
