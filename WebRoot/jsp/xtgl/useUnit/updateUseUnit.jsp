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
    
    <title>使用单位</title>
    
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
	<form id="form" action="<%=path %>/useUnitAction.do?method=updateEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">使用单位</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改使用单位</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">使用单位名称&nbsp;:&nbsp;</label>
						<input type="hidden" id="id"  name="unit.id"  value="${list.id}"/>
						<input type="text" id="name"  name="unit.name"  value="${list.name}" />
					</p>
					<p class="fill">
						<label for="logn">使用单位类型&nbsp;:&nbsp;</label>
						<select  name="unit.type"  id="type">
							<option value="">请选择</option>
							<option <c:if test="${list.type=='物业'}">selected="selected" </c:if> value="物业">物业</option>
							<option <c:if test="${list.type=='政府'}">selected="selected" </c:if> value="政府">政府</option>
							<option <c:if test="${list.type=='事业单位'}">selected="selected" </c:if> value="事业单位">事业单位</option>
						</select>
					</p>
					<p class="fill">
						<label for="man">联系人&nbsp;:&nbsp;</label>
						<input type="text" id="liaisons"  name="unit.liaisons"  value="${list.liaisons}"/>
					</p>
					<p class="fill">
						<label for="phone">联系人电话&nbsp;:&nbsp;</label>
						<input type="text" id="phone"  name="unit.phone"  value="${list.phone}"/>
					</p>
					<p class="fill">
						<label for="area">使用单位地址&nbsp;:&nbsp;</label>
						<input type="text" id="address"  name="unit.address"  value="${list.address}" />
					</p>
					<div class="keep clearfix">
						<button class="fl"    onclick="add();">保存</button>
						<button class="fr"   onclick="history.go(-1); ">取消</button>
					</div>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/useUnit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     
     </script>
</html>