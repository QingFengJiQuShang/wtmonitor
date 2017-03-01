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

    <title>单包流量</title>

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
		    <script src="<%=path%>/js/Share.js" type="text/javascript" charset="utf-8"></script>

	</head>

	<body>
	<form id="form" action="<%=path %>/dictionaryAction.do?method=addEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">单包流量</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">单包流量</p>
				<div class="table_con">

					<p class="fill">
						<label for="man">单包流量大小（K）&nbsp;:&nbsp;</label>
						<input type="hidden"  name="dictionary.id"  id="id"  value="${list.id}">
						<input type="hidden"  name="dictionary.flag"  id="flag"  value="${list.flag}">
						<input type="hidden"  name="dictionary.remarks"  id="remarks"  value="${list.remarks}">
            <%if(Authority.haveRigth(user.getId(),"xtsz_bjkz_update")) {%>
            <input type="text" id="dictionary"  name="dictionary.dictionary"  value="${list.dictionary}" />
            <%}else{%>
              ${list.dictionary}
            <%}%>
          </p>
					<p class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"xtsz_bjkz_update")) {%>
  						<input type="button"  value="保存"  onclick="add();">
            <%}%>
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
    	 if(showIsNumber("dictionary","单包流量")){
    		 $('#form').submit();
    	 }

     }

     </script>
</html>
