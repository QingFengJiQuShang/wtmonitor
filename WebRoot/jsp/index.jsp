<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%@page import="com.jrsoft.fri.cs.Tip"%>
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
		<script type="text/javascript" src="<%=path %>/js/comet4j.js"></script>
		<style type="text/css">
		  /*sheyMsg style*/
    .msg { width:240px;display:none; }

   
    .blue .top,.blue .top .title,.blue .top span,.blue .bottom,.blue .bottom a {background:transparent url(<%=path%>/img/msg_bg_blue.gif) no-repeat 0px 0px;}
    .blue .center {background:url(<%=path%>/img/center_bg_blue.gif) repeat-y;}
    
    .msg .top{width:240px;height:25px;position:relative;}
    .msg .top .title {background-position:-195px -70px;padding-left:30px;line-height:22px;width:100px;height:25px;}
    .msg .top span {background-position:0px -70px;width:36px; height:17px;position:absolute;top:1px;left:198px;cursor:pointer;}
    .msg .top span:hover {background-position:-43px -71px;}

    .msg .center { width:240px;height:115px;}
    .msg .center h3{color:#0c4e7c;text-align:center;line-height:23px;font-size:13px;}
    .msg .center p{color: #0c4e7c;margin:0px 10px;line-height:20px;}

    .msg .bottom {height:29px;background-position:0px -32px;}
    .msg .bottom a {background-position:-120px -75px;padding-left:20px;margin:7px 10px;float:right;width:30px;height:20px;}
    /*sheyMsg style end*/
		</style>
		</head>
<script type="text/javascript">
function sign(){
	if(window.confirm('您确定要退出登录吗？')){
		window.location.href="<%=path%>/userLogout";	
	}
}
function toMain(flag){
		   if(flag==1){
			   window.main.location="<%=path%>/indexAction.do?method=query";
		   }
		   if(flag==21){
			   window.main.location="<%=path%>/elevatorAction.do?method=query";
		   }
		   if(flag==22){
			   window.main.location="<%=path%>/elevatorAction.do?method=queryMonitor";
		   }
		   if(flag==23){
			   window.main.location="<%=path%>/elevatorAction.do?method=queryPlayback";
		   }
		   if(flag==24){
			   window.main.location="<%=path%>/elevatorAction.do?method=queryManage";
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
		   if(flag==41){
			   window.main.location="<%=path%>/alarmAction.do?method=query";
		   }
		   if(flag==42){
			   window.main.location="<%=path%>/faultAction.do?method=query";
		   }
		   if(flag==43){
			   window.main.location="<%=path%>/faultAction.do?method=query1";
		   }
		   
		    if(flag==51){
			   window.main.location="<%=path%>/jsp/count/fault.jsp";
		   }
		   if(flag==52){
			   window.main.location="<%=path%>/jsp/count/rescue.jsp";
		   }
		   if(flag==53){
			   window.main.location="<%=path%>/jsp/count/brand.jsp";
		   }
		    if(flag==54){
			   window.main.location="<%=path%>/jsp/count/Maintenance.jsp";
		   }
		     if(flag==55){
			   window.main.location="<%=path%>/jsp/count/useNuit.jsp";
		   }
		   if(flag==61){
			   window.main.location="<%=path%>/logAction.do?method=query";
		   }
		   if(flag==62){
			   window.main.location="<%=path%>/jsp/xtsz/help/help.jsp";
		   }
		   if(flag==63){
			   window.main.location="<%=path%>/jsp/xtsz/message/messageList.jsp";
		   }
		   if(flag==64){
			   window.main.location="<%=path%>/jsp/xtsz/message/jurisdiction.jsp";
		   }
		   if(flag==65){
			   window.main.location="<%=path%>/jsp/xtsz/message/messageLog.jsp";
		   }
		   
		    if(flag==71){
			   window.main.location="<%=path%>/jsp/Insurance/uninsured/insuranceList.jsp";
		   }
		   if(flag==72){
			   window.main.location="<%=path%>/jsp/Insurance/paul/insuranceList.jsp";
		   }
		    if(flag==73){
			   window.main.location="<%=path%>/jsp/Insurance/offPaul/insuranceList.jsp";
		   }
		    if(flag==74){
			   window.main.location="<%=path%>/jsp/Insurance/count/count.jsp";
		   }
		    if(flag==75){
			   window.main.location="<%=path%>/jsp/Insurance/count/brandCount.jsp";
		   }
	   }
</script>

		<body  onload="push();">
		<div class="top clearfix">
			<p class="fl">
				<img src="<%=path %>/img/logo.png" />&nbsp;
			</p>
			<p class="fr out"  onclick="sign();">
				<img src="<%=path %>/img/sign_out.png" class="manage" alt="" />退出
			</p>
			<p class="fr userName">
				<img src="<%=path %>/img/manage.png" alt="" class="manage" />您好，<%=user.getName() %>
			</p>
		</div>
		<ul class="list ">
			<li class="list-item"   onclick="toMain('1')">
				<p class="one active" data="<%=path %>/">首页</p>
			</li>
			<li class="list-item"  >
				<p class="one grade" >
					<i class=""></i>电梯监控</p>
				<div class="next">
					<p  onclick="toMain('21')">电梯列表</p>
					<p  onclick="toMain('22')">电梯监控</p>
					<p  onclick="toMain('23')">电梯回放</p>
					<p  onclick="toMain('24')">电梯管理</p>
				</div>
			</li>
			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>故障管理</p>
				<div class="next">
					<p	 onclick="toMain('41')">人工接警</p>
					<p	 onclick="toMain('42')">当前故障</p>
					<p	 onclick="toMain('43')">历史故障</p>
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
				<p class="one">
					<i class=""></i>保险管理</p>
				<div class="next">
					<p class="two" onclick="toMain(71)">未保电梯</p>
					<p class="two" onclick="toMain(72)">在保电梯</p>
					<p class="two" onclick="toMain(73)">脱保电梯</p>
					<p class="two" onclick="toMain(74)">保险统计</p>
					<p class="two" onclick="toMain(75)">品牌保险统计</p>
				</div>
			</li>
			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>统计分析</p>
				<div class="next">
					<p	 onclick="toMain('51')">故障统计</p>
					<p	 onclick="toMain('52')">救援统计</p>
					<p	 onclick="toMain('53')">电梯品牌统计</p>
					<p	 onclick="toMain('54')">维保单位统计</p>
					<p	 onclick="toMain('55')">使用单位统计</p>
				</div>
			</li>
			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>系统设置</p>
				<div class="next">
					<p	 onclick="toMain('61')">操作日志</p>
					<p	 onclick="toMain('62')">系统帮助</p>
					<p	 onclick="toMain('63')">短信警告</p>
					<p	 onclick="toMain('64')">短信权限</p>
					<p	 onclick="toMain('65')">短信日志</p>
				</div>
			</li>
		</ul>
		<iframe src="<%=path%>/indexAction.do?method=query" id="main"  name="main"  frameborder="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()" width="100%" height=""   style="margin-top: -2px; overflow-x:hidden;  ">
			
		</iframe>
		<input  type="hidden" value=""   id="pushId"  name="pushId">
		<!--sheyMsg start-->
	<div class="msg blue" id="msgbox">
		<div class="top">
		    <div class="title">电梯报警</div>
		    <span title="close" id="msgclose"></span>
		</div>
		 <div class="center">
			<h3>电梯报警</h3>
			<p id="news"></p>
		</div>
		<audio  id="music"   loop="loop"> 
			<source src="<%=path%>/music/music.mp3" type="audio/mpeg" /> 
		</audio>

</audio>
		<div class="bottom"><a target="_blank" href="http://blog.csdn.net/sohighthesky/archive/2009/11/10/4795886.aspx">查看</a>
		</div>
	</div>
	<!--sheyMsg end-->
	</body>
	<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/map.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="<%=path %>/js/sheyMsg.js"></script>
	<script type="text/javascript">
		function iFrameHeight() {
			var ifm = document.getElementById("main");
			var subWeb = document.frames ? document.frames["main"].document : ifm.contentDocument;
			if(ifm != null && subWeb != null) {
				ifm.height = subWeb.body.scrollHeight;
				//ifm.width = subWeb.body.scrollWidth;
			}
		}
		
var g=function(id){return document.getElementById(id)};

	setInterval('push()',10000); //指定30秒刷新一次s
	function push (){
		$.ajax({
				 mtype:'post',
				 url: '<%=path%>/pushAction.do?method=push',
				 data:'',
				 success: function(rs){
				          var json=eval(rs.rows);			
				         // alert(json.length);
				          for(var i=0;i<json.length;i++){
				        	  var msg=new sheyMsg("msgbox",{
								    showDelay:1,
								    onHide:clsoePush
								});
				        	  	g("msgclose").onclick=function() {//hide
									msg.hide();
								}
				        	  document.getElementById('pushId').value=json[0].id;
				         	  document.getElementById('news').innerHTML ="注册号："+json[0].registerid+"<br/>识别码："+json[0].distinguishid+"<br/>安装地址："+json[0].installPlace+"<br/>"+json[0].faultType+"<br/>";
				          	  document.getElementById('music').play();		//开始播放
				          }
				          
				  },
				  error: function(result){
							alert("操作失败!");
					}
		 });
		
		
	}
								
	function clsoePush (){
		var pushId= document.getElementById('pushId').value;
		document.getElementById('music').pause();				//停止播放
		
		$.ajax({
				 mtype:'post',
				 url: '<%=path%>/pushAction.do?method=updatePush',
				 data:'pushId='+pushId,
				 success: function(rs){
				        // alert("已确认");
				  },
				  error: function(result){
							alert("操作失败!");
					}
		 });
		
	}
	
	</script>

</html>
