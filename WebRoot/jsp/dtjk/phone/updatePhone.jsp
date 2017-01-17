<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.jrsoft.fri.dtjk.entity.DtjkPhone"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
JSONArray array=(JSONArray)request.getAttribute("array");
DtjkPhone list=(DtjkPhone)request.getAttribute("list");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电梯白名单</title>
    
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
	<form id="form" action="<%=path %>/phoneAction.do?method=updateEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">白名单</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改白名单</p>
				
				<div class="table_con">
					<p class="fill">
						<label for="user">电话&nbsp;:&nbsp;</label>
						<input type="hidden" id="elevatorId"  name="ph.id"  value="${list.id}" />
						<input type="hidden" id="elevatorId"  name="ph.elevatorId.id"  value="${list.elevatorId.id}" />
						
						<select id="phone"  name="ph.phone">
								<option value="">请选择</option>
								<%for(int i=0;i<array.size();i++){
									JSONObject obj=(JSONObject)array.get(i);
									%>
									<option value="<%=obj.get("phone") %>"   <%if(list.getPhone().equals(obj.get("phone"))) {%> selected="selected"  <%} %> onclick="belong('<%=obj.get("name")  %>')"><%=obj.get("phone") %></option>
							<%	} %>
						</select>
					</p>
					<p class="fill">
						<label for="user">所属人&nbsp;:&nbsp;</label>
						<input type="text" id="belong"  name="ph.belong"  readonly="readonly"  value="${list.belong}" />
						
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
	<script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
	
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
      
</script>
</html>
