<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
	</head>

	<body>
	<form id="form" action="<%=path %>/usersAction.do?method=updateEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">用户</p>
			<p class="back"> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改用户</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">用户名&nbsp;:&nbsp;</label>
						<input type="hidden" id="name" name="users.id"  value="${list.id}"  />	
						<input type="text" id="name" name="users.name"  value="${list.name}"  />
					</p>
					<p class="fill">
						<label for="logn">登录名&nbsp;:&nbsp;</label>
						<input type="hidden" id="logn1"   value="${list.loginname}"   />
						<input type="text" id="logn"  name="users.loginname"    value="${list.loginname}"  onblur="skip1();" />
					</p>
					<p class="fill">
						<label for="password">密码&nbsp;:&nbsp;</label>
						<input type="password" id="password"    name="users.password"    value="${list.password}" />
					</p>
					<p class="fill">
						<label for="unit">手机&nbsp;:&nbsp;</label>
						<input type="text" id="phone"    name="users.phone"    value="${list.phone}" />
					</p>
					<p class="fill">
						<label for="unit">公司单位&nbsp;:&nbsp;</label>
						<input type="text" id="unit"    name="users.unit"    value="${list.unit}" />
					</p>
					<p class="fill">
						<label for="area">区域&nbsp;:&nbsp;</label>
						<select id="cmbProvince" name="users.province"  style="width: 95px;"  ></select>&nbsp;&nbsp;
						<select id="cmbCity" name="users.city" style="width: 95px;"></select>&nbsp;&nbsp;
						<select id="cmbArea"   style="display: none;"></select>&nbsp;&nbsp;
					</p>
					<div class="fill">
						<label for="power" class="fl">权限管理&nbsp;:&nbsp;</label>
						<div class="choose">
							<div class="">
								<p class="manage">用户权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<span class="fl wei">选择浏览</span>
									<span class="fl wei">修改</span>
									<span class="fl wei">删除</span>
									<span class="fl wei">增加</span>
									<span class="fl all">全选</span>
								</p>
							</div>
							<div class="">
								<p class="manage">故障管理权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<span class="fl wei">选择浏览1</span>
									<span class="fl wei">修改</span>
									<span class="fl wei">删除</span>
									<span class="fl wei">增加</span>
									<span class="fl all">全选</span>
								</p>
							</div>
							<div class="">
								<p class="manage">统计分析权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<span class="fl wei">选择浏览1</span>
									<span class="fl wei">修改</span>
									<span class="fl wei">删除</span>
									<span class="fl wei">增加</span>
									<span class="fl all">全选</span>
								</p>
							</div>
							<div class="">
								<p class="manage">电梯监控权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<span class="fl wei">选择浏览1</span>
									<span class="fl wei">修改</span>
									<span class="fl wei">删除</span>
									<span class="fl wei">增加</span>
									<span class="fl all">全选</span>
								</p>
							</div>		
							<div class="">
								<p class="manage">系统设置权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<span class="fl wei">选择浏览1</span>
									<span class="fl wei">修改</span>
									<span class="fl wei">删除</span>
									<span class="fl wei">增加</span>
									<span class="fl all">全选</span>
								</p>
							</div>
							<div class="">
								<p class="manage">系统帮助权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<span class="fl wei">选择浏览1</span>
									<span class="fl wei">修改</span>
									<span class="fl wei">删除</span>
									<span class="fl wei">增加</span>
									<span class="fl all">全选</span>
								</p>
							</div>
						</div>

					</div>
					<div class="keep clearfix">
						<button class="fl"  onclick="add();">保存</button>
						<button class="fr">取消</button>
					</div>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/ssq.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/add_user.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     addressInit('cmbProvince', 'cmbCity', 'cmbArea','${list.province}','${list.city}','${list.area}');
     </script>
</html>
