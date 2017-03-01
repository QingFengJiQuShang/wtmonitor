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

    <title>短信模板</title>

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
	<form id="form" action="<%=path %>/dictionaryAction.do?method=addTemplate" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">短信模板</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">短信模板</p>
			<div class="table_con">
					<p class="fill">
						<label for="name">模板类型&nbsp;:&nbsp;</label>
						<input type="hidden" id="flag"  name="dictionary.flag"  value="4"/>
						<input type="hidden" id="remarks"  name="dictionary.remarks"  />

					</p>

					<p class="fill">
						<label for="man">模板内容&nbsp;:&nbsp;</label>
						<textarea  id="dictionary"  name="dictionary.dictionary"   rows="3" cols="50"></textarea>
					</p>

					<p class="or clearfix">
						<input type="button"  value="保存"  onclick="add();">
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>
				<div>
						<p>姓名：{name}</p>
						<p>安装地址：{place}</p>
						<p>故障类型：{type}</p>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
    	  var dictionary= document.getElementById("dictionary").value;
    	 if(dictionary.indexOf("{name}") > -1)
		 	$('#form').submit();
     }

     </script>
</html>
