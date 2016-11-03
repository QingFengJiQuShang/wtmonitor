<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>智慧安全登监控系统</title>

<meta charset="utf-8" />
			<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/map/map.css" />
		
		</head>
<script type="text/javascript">
function sign(){
	if(window.confirm('您确定要退出登录吗？')){
		window.location.href="<%=path%>/userLogout";	
	}
}
function toMain(flag){
		   if(flag==1){
			   window.main.location="<%=path%>/jsp/home/main.jsp";
		   }
		   if(flag==2){
			   alert(flag);
			   window.main.location="<%=path%>/jsp/dagl/elevator/elevatorList.jsp";
		   }
		   if(flag==31){
			   window.main.location="<%=path%>/usersAction.do?method=query";
		   }
		   if(flag==32){
			   window.main.location="<%=path%>/useUnitAction.do?method=query";
		   }
		    if(flag==33){
			   window.main.location="<%=path%>/maintenanceUnitAction.do?method=query";
		   }
		    if(flag==34){
			   window.main.location="<%=path%>/rescueUnitAction.do?method=query";
		   }
		    
	   }
</script>

		<body>
		<div class="top clearfix">
			<p class="fl">
				<img src="<%=path %>/img/logo.png" />&nbsp;|&nbsp;&nbsp;电梯运行安全物联系统
			</p>
			<p class="fr out"  onclick="sign();">
				<img src="<%=path %>/img/sign_out.png" class="manage" alt="" />退出
			</p>
			<p class="fr userName">
				<img src="<%=path %>/img/manage.png" alt="" class="manage" />您好，<%=user.getName() %>
			</p>
		</div>
		<ul class="list ">
			<li class="list-item">
				<p class="one active" data="<%=path %>/">首页</p>
			</li>
			<li class="list-item"  >
				<p class="one grade"   onclick="toMain('1')">
					<i class=""></i>电梯监控</p>
				<div class="next">
					<p  onclick="toMain('2')">电梯列表</p>
					<p  onclick="toMain('1')">电梯监控</p>
					<p  onclick="toMain('1')">电梯回放</p>
					<p  onclick="toMain('1')">电梯管理</p>
				</div>
			</li>
			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>用户管理</p>
				<div class="next">
					<p	 onclick="toMain('31')">系统用户</p>
					<p	 onclick="toMain('32')">使用单位</p>
					<p	 onclick="toMain('33')">维保单位</p>
					<p	 onclick="toMain('34')">救援单位</p>
				</div>
			</li>
			<li class="list-item">
				<p class="one" 	 onclick="toMain('1')">故障管理</p>
			</li>
			<li class="list-item">
				<p class="one"	 onclick="toMain('1')">统计分析</p>
			</li>
			<li class="list-item">
				<p class="one"	 onclick="toMain('1')">系统设置</p>
			</li>
		</ul>
		<iframe src="<%=path %>/jsp/home/main.jsp" id="main"  name="main"  frameborder="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()" width="100%" height=""   style="margin-top: -2px; overflow-x:hidden;  ">
			
		</iframe>
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/map.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		function iFrameHeight() {
			var ifm = document.getElementById("main");
			var subWeb = document.frames ? document.frames["main"].document : ifm.contentDocument;
			if(ifm != null && subWeb != null) {
				ifm.height = subWeb.body.scrollHeight;
				//ifm.width = subWeb.body.scrollWidth;
			}
		}
	</script>

</html>
