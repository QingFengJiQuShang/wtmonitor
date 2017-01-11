<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>区域单位</title>
    
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
		    <script src="<%=path%>/js/region.js" type="text/javascript" charset="utf-8"></script>
	
	</head>

	<body>
	<form id="form" action="<%=path %>/regionUnitAction.do?method=addEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">区域单位</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">新增区域单位</p>
				<div class="table_con">
					<p class="fill">
						<label for="logn">省&nbsp;:&nbsp;</label>
						<select  name="regionUnit.province"  id="province"   >
						<option value="">请选择</option>
						</select>
					</p>
					<p class="fill">
						<label for="logn">市&nbsp;:&nbsp;</label>
						<select  name="regionUnit.city"  id="city" >
						<option value="">请选择</option>
						</select>
					</p>
					<p class="fill">
						<label for="logn">区&nbsp;:&nbsp;</label>
						<select  name="regionUnit.area"  id="area" >
							<option value="">请选择</option>
						</select>
					</p>
					<p class="fill">
						<label for="name">区域单位名称&nbsp;:&nbsp;</label>
						<input type="text" id="name"  name="regionUnit.name"  />
					</p>
					<p class="fill">
						<label for="man">联系人&nbsp;:&nbsp;</label>
						<input type="text" id="liaisons"  name="regionUnit.liaisons"  />
					</p>
					<p class="fill">
						<label for="phone">联系人电话&nbsp;:&nbsp;</label>
						<input type="text" id="phone"  name="regionUnit.phone"  />
					</p>
					<p class="fill">
						<label for="area">区域单位地址&nbsp;:&nbsp;</label>
						<input type="text" id="address"  name="regionUnit.address"  />
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
