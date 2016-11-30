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
	<form id="form" action="<%=path %>/usersAction.do?method=addEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">用户</p>
			<p class="back" onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">新增用户</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">用户名&nbsp;:&nbsp;</label>
						<input type="text" id="name" name="users.name"/>
					</p>
					<p class="fill">
						<label for="logn">登录名&nbsp;:&nbsp;</label>
						<input type="text" id="logn"  name="users.loginname"  onblur="skip();" />
					</p>
					<p class="fill">
						<label for="password">密码&nbsp;:&nbsp;</label>
						<input type="password" id="password"    name="users.password"  />
					</p>
					<p class="fill">
						<label for="unit">手机&nbsp;:&nbsp;</label>
						<input type="text" id="phone"    name="users.phone"  />
					</p>
					<p class="fill">
						<label for="unit">公司单位&nbsp;:&nbsp;</label>
						<input type="text" id="unit"    name="users.unit"  />
					</p>
					<p class="fill">
						<label for="area">区域&nbsp;:&nbsp;</label>
						<select id="cmbProvince" name="users.province"  style="width: 95px;"></select>&nbsp;&nbsp;
						<select id="cmbCity" name="users.city" style="width: 95px;"></select>&nbsp;&nbsp;
						<select id="cmbArea"   style="display: none;"></select>&nbsp;&nbsp;
					</p>
					
						<div class="fill">
						<label for="power" class="fl">权限管理&nbsp;:&nbsp;</label>
						<div class="choose">
							<div class="">
								<p class="manage">电梯监控权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<input type="checkbox" name="authority" class="selected" id="selected" value="dtjk_check" />
									<label for="user_ll">查看</label>
									<input type="checkbox" name="authority" id="selected" value="dtjk_update" />
									<label for="user_xg">修改</label> <br>
									<input type="checkbox" name="authority" id="selected" value="dtjk_del" />
									<label for="user_del">删除</label>
									<input type="checkbox" name="authority" id="selected" value="dtjk_add" />
									<label for="user_add">增加</label>
									<input type="checkbox"  name="controlAll" id="controlAll" />
									<label for="controlAll">全选</label>
								</p>
							</div>
							<div class="">
								<p class="manage">故障管理权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<input type="checkbox" name="authority" class="selected" id="selectedjk" value="gzgl_check" />
									<label for="user_ll">查看</label>
									<input type="checkbox" name="authority" id="selectedjk" value="gzgl_update" />
									<label for="user_xg">修改</label> <br>
									<input type="checkbox" name="authority" id="selectedjk" value="gzgl_del" />
									<label for="user_del">删除</label>
									<input type="checkbox" name="authority" id="selectedjk" value="gzgl_add" />
									<label for="user_add">增加</label>
									<input type="checkbox"  id="controlAlljk" />
									<label for="controlAll">全选</label>
								</p>
							</div>		
							<div class="">
								<p class="manage">用户管理权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<input type="checkbox" name="authority" class="selected" id="selectedgz" value="yhgl_check" />
									<label for="user_ll">查看</label>
									<input type="checkbox" name="authority" id="selectedgz" value="yhgl_update" />
									<label for="user_xg">修改</label> <br>
									<input type="checkbox" name="authority" id="selectedgz" value="yhgl_del" />
									<label for="user_del">删除</label>
									<input type="checkbox" name="authority" id="selectedgz" value="yhgl_add" />
									<label for="user_add">增加</label>
									<input type="checkbox"  name="controlAllgz" id="controlAllgz" />
									<label for="controlAll">全选</label>
								</p>
							</div>
							<div class="">
								<p class="manage">统计分析权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<input type="checkbox" name="selectedtj" class="selected" id="selectedtj" value="1" />
									<label for="user_ll">查看</label>
									<input type="checkbox" name="selectedtj" id="selectedtj" value="2" />
									<label for="user_xg">修改</label> <br>
									<input type="checkbox" name="selectedtj" id="selectedtj" value="3" />
									<label for="user_del">删除</label>
									<input type="checkbox" name="selectedtj" id="selectedtj" value="4" />
									<label for="user_add">增加</label>
									<input type="checkbox"  name="controlAlltj" id="controlAlltj" />
									<label for="controlAll">全选</label>
								</p>
							</div>
							
							<div class="">
								<p class="manage">系统设置权限分配 <i class="jia"></i></p>
								<p class="select clearfix">
									<input type="checkbox" name="selectedxt" class="selected" id="selectedxt" value="1" />
									<label for="user_ll">查看</label>
									<input type="checkbox" name="selectedxt" id="selectedxt" value="2" />
									<label for="user_xg">修改</label> <br>
									<input type="checkbox" name="selectedxt" id="selectedxt" value="3" />
									<label for="user_del">删除</label>
									<input type="checkbox" name="selectedxt" id="selectedxt" value="4" />
									<label for="user_add">增加</label>
									<input type="checkbox"  name="controlAllxt" id="controlAllxt" />
									<label for="controlAll">全选</label>
								</p>
							</div>
						</div>

					</div>
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
	<script src="<%=path %>/js/ssq.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/add_user.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     addressInit('cmbProvince', 'cmbCity', 'cmbArea','请选择','请选择','请选择');
     
	
     </script>
</html>
