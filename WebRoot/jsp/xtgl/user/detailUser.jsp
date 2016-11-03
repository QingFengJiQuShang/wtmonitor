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
		<div class="con">
			<p class="user">用户</p>
			<p class="back" onclick="history.go(-1); "> <img src="<%=path%>/img/back.png"  />返回</p>
			<div class="table">
				<p class="add">查询用户</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">用户名&nbsp;:&nbsp;</label>
						${list.name}
					</p>
					<p class="fill">
						<label for="logn">登录名&nbsp;:&nbsp;</label>
						${list.loginname}
					</p>
					<p class="fill">
						<label for="unit">手机&nbsp;:&nbsp;</label>
						${list.phone}
					</p>
					<p class="fill">
						<label for="unit">公司单位&nbsp;:&nbsp;</label>
						${list.unit}
					</p>
					<p class="fill">
						<label for="area">区域&nbsp;:&nbsp;</label>
						${list.province}&nbsp;&nbsp;
						${list.city}&nbsp;&nbsp;
						
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
						<button class="fl"  onclick="findById('${list.id}','1');">修改</button>
						<button class="fr"  onclick="history.go(-1); ">取消</button>
					</div>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/ssq.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/user.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    
     </script>
</html>
