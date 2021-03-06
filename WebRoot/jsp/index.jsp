<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtsz.action.Dictionary"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
			String dictionary=Dictionary.getDictionary("2");
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
    .msg .top .title {/*background-position:-195px -70px;*/padding-left:10px;line-height:22px;width:100px;height:25px;}
    .msg .top span {background-position:0px -70px;width:36px; height:17px;position:absolute;top:1px;left:198px;cursor:pointer;}
    .msg .top span:hover {background-position:-43px -71px;}

    .msg .center { width:240px;height:155px;}
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
		    if(flag==35){
			   window.main.location="<%=path%>/propertyUnitAction.do?method=query";
		   }
		    if(flag==36){
			   window.main.location="<%=path%>/makeUnitAction.do?method=query";
		   }
		    if(flag==37){
			   window.main.location="<%=path%>/safeUnitAction.do?method=query";
		   }
		    if(flag==38){
			   window.main.location="<%=path%>/regionUnitAction.do?method=query";
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
			   window.main.location="<%=path%>/countAction.do?method=regionCount";
		   }
		   if(flag==52){
			   window.main.location="<%=path%>/countAction.do?method=faultCount";
		   }
		   if(flag==53){
			   window.main.location="<%=path%>/countAction.do?method=rescueRegionCount";
		   }
		    if(flag==54){
			   window.main.location="<%=path%>/countAction.do?method=rescueUnitCount";
		   }
		     if(flag==55){
			   window.main.location="<%=path%>/countAction.do?method=maintenanceRegionCount";
		   }
		   if(flag==56){
			   window.main.location="<%=path%>/countAction.do?method=maintenanceAttendanceCount";
		   }
		    if(flag==57){
			   window.main.location="<%=path%>/countAction.do?method=messageCount";
		   }
		   if(flag==61){
			   window.main.location="<%=path%>/logAction.do?method=query&flag=1";
		   }
		   if(flag==62){
			   window.main.location="<%=path%>/logAction.do?method=query&flag=0";
		   }
		   if(flag==63){
			   window.main.location="<%=path%>/helpAction.do?method=query";
		   }
		   if(flag==64){
			   window.main.location="<%=path%>/messageAction.do?method=query&flag=0";
		   }
		   if(flag==65){
			   window.main.location="<%=path%>/usersAction.do?method=findByMessage";
		   }
		   if(flag==66){
			   window.main.location="<%=path%>/messageAction.do?method=query&flag=1";
		   }
		   if(flag==67){
			   window.main.location="<%=path%>/dictionaryAction.do?method=findById&flag=0";
		   }
		   if(flag==68){
			   window.main.location="<%=path%>/dictionaryAction.do?method=findById&flag=1";
		   }
		   if(flag==69){
			   window.main.location="<%=path%>/usersAction.do?method=findByAlarm";
		   }
		   if(flag==610){
			   window.main.location="<%=path%>/dictionaryAction.do?method=findByTemplate&flag=4";
		   }
		   if(flag==611){
			   window.main.location="<%=path%>/jsp/xtsz/position/main.jsp";
		   }
		    if(flag==71){
			   window.main.location="<%=path%>/safeAction.do?method=queryElevator&flag=1";
		   }
		   if(flag==72){
			   window.main.location="<%=path%>/safeAction.do?method=queryElevator&flag=3";
		   }
		    if(flag==73){
			   window.main.location="<%=path%>/safeAction.do?method=queryElevator&flag=2";
		   }
		    if(flag==74){
			   window.main.location="<%=path%>/safeAction.do?method=querySafe";
		   }
		    if(flag==75){
			   window.main.location="<%=path%>/safeAction.do?method=querySafeUnit";
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
			<%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_check")||Authority.haveRigth(user.getId(),"dtjk_dtjk_check")||Authority.haveRigth(user.getId(),"dtjk_jlhf_check")||Authority.haveRigth(user.getId(),"dtjk_dtgg_check")) {%>
			<li class="list-item"  >
				<p class="one grade" >
					<i class=""></i>电梯监控</p>
				<div class="next">
					<%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_check")) {%>
					<p  class="two"  onclick="toMain('21')">电梯列表</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dtjk_dtjk_check")) {%>
					<p  class="two"  onclick="toMain('22')">实时监控</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dtjk_jlhf_check")) {%>
					<p  class="two"  onclick="toMain('23')">记录回放</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dtjk_dtgg_check")) {%>
					<p  class="two" onclick="toMain('24')">电梯管理</p>
					<%} %>
				</div>
			</li>
			<%} %>
			<%if(Authority.haveRigth(user.getId(),"gzgl_rgjj_check")||Authority.haveRigth(user.getId(),"gzgl_dqgz_check")||Authority.haveRigth(user.getId(),"gzgl_lsgz_check")) {%>
			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>故障管理</p>
				<div class="next">
			<%if(Authority.haveRigth(user.getId(),"gzgl_rgjj_check")) {%>
					<p	 class="two"  onclick="toMain('41')">人工接警</p>
					<%} %>
			<%if(Authority.haveRigth(user.getId(),"gzgl_dqgz_check")) {%>
					<p	 class="two"  onclick="toMain('42')">当前故障</p>
					<%} %>
			<%if(Authority.haveRigth(user.getId(),"gzgl_lsgz_check")) {%>
					<p	 class="two" onclick="toMain('43')">历史故障</p>
					<%} %>
				</div>
			</li>
			<%} %>
			<%if(Authority.haveRigth(user.getId(),"dwgl_sydw_check")||Authority.haveRigth(user.getId(),"dwgl_wbdw_check")||Authority.haveRigth(user.getId(),"dwgl_jydw_check")||Authority.haveRigth(user.getId(),"dwgl_wydw_check")||Authority.haveRigth(user.getId(),"dwgl_zzdw_check")||Authority.haveRigth(user.getId(),"dwgl_bxdw_check")||Authority.haveRigth(user.getId(),"dwgl_qudw_check")) {%>
			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>单位管理</p>
				<div class="next">
					<%if(Authority.haveRigth(user.getId(),"dwgl_sydw_check")) {%>
					<p	 class="two"  onclick="toMain('32')">使用单位</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dwgl_wbdw_check")) {%>
					<p	 class="two"  onclick="toMain('33')">维保单位</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dwgl_jydw_check")) {%>
					<p	 class="two"  onclick="toMain('34')">救援单位</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dwgl_wydw_check")) {%>
					<p	 class="two"  onclick="toMain('35')">物业单位</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dwgl_zzdw_check")) {%>
					<p	 class="two"  onclick="toMain('36')">制造单位</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dwgl_bxdw_check")) {%>
					<p	 class="two"  onclick="toMain('37')">保险单位</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"dwgl_qydw_check")) {%>
					<p	 class="two"  onclick="toMain('38')">区域单位</p>
					<%} %>
				</div>
			</li>
			<%} %>
			<%if(Authority.haveRigth(user.getId(),"bxgl_wbdt_check")||Authority.haveRigth(user.getId(),"bxgl_zbdt_check")||Authority.haveRigth(user.getId(),"bxgl_tbdt_check")||Authority.haveRigth(user.getId(),"bxgl_bxtj_check")||Authority.haveRigth(user.getId(),"bxgl_bxgs_check")) {%>
				<li class="list-item">
				<p class="one">
					<i class=""></i>保险管理</p>
				<div class="next">
					<%if(Authority.haveRigth(user.getId(),"bxgl_wbdt_check")) {%>
					<p class="two" onclick="toMain(71)">未保电梯</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"bxgl_zbdt_check")) {%>
					<p class="two" onclick="toMain(72)">在保电梯</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"bxgl_tbdt_check")) {%>
					<p class="two" onclick="toMain(73)">脱保电梯</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"bxgl_bxtj_check")) {%>
					<p class="two" onclick="toMain(74)">保险统计</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"bxgl_bxgs_check")) {%>
					<p class="two" onclick="toMain(75)">保险公司统计</p>
					<%} %>
				</div>
			</li>

			<%}%>
			<%if(Authority.haveRigth(user.getId(),"xtsz_xtyh_check")||Authority.haveRigth(user.getId(),"xtsz_czrz_check")||Authority.haveRigth(user.getId(),"xtsz_txrz_check")||Authority.haveRigth(user.getId(),"xtsz_xtbz_check")||Authority.haveRigth(user.getId(),"xtsz_dxmb_check")||Authority.haveRigth(user.getId(),"xtsz_dxtx_check")||Authority.haveRigth(user.getId(),"xtsz_dxqx_check")||Authority.haveRigth(user.getId(),"xtsz_dxrz_check")||Authority.haveRigth(user.getId(),"xtsz_sxsj_check")||Authority.haveRigth(user.getId(),"xtsz_dbll_check")||Authority.haveRigth(user.getId(),"xtsz_bjkz_check")||Authority.haveRigth(user.getId(),"xtsz_dlwz_check")) {%>

			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>系统设置</p>
				<div class="next">
					<%if(Authority.haveRigth(user.getId(),"xtsz_xtyh_check")) {%>
					<p	 class="two"  onclick="toMain('31')">系统用户</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_czrz_check")) {%>
					<p	 class="two"  onclick="toMain('61')">操作日志</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_txrz_check")) {%>
					<p	 class="two"  onclick="toMain('62')">通信日志</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_xtbz_check")) {%>
					<p	 class="two"  onclick="toMain('63')">系统帮助</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_dxmb_check")) {%>
					<p	 class="two"  onclick="toMain('610')">短信模板</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_dxtx_check")) {%>
					<p	 class="two"  onclick="toMain('64')">短信提醒</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_dxqx_check")) {%>
					<p	 class="two"  onclick="toMain('65')">短信权限</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_dxrz_check")) {%>
					<p	 class="two"  onclick="toMain('66')">短信日志</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_sxsj_check")) {%>
					<p	 class="two"  onclick="toMain('67')">刷新时间</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_dbll_check")) {%>
					<p	 class="two"  onclick="toMain('68')">单包流量</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_bjkz_check")) {%>
					<p	 class="two"  onclick="toMain('69')">报警控制</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"xtsz_dlwz_check")) {%>
					<p	 class="two"  onclick="toMain('611')">地理位置</p>
					<%} %>
				</div>
			</li>
			<%}%>
			<%if(Authority.haveRigth(user.getId(),"tjfx_gzqytj_check")||Authority.haveRigth(user.getId(),"tjfx_gzlxtj_check")||Authority.haveRigth(user.getId(),"tjfx_jyqytj_check")||Authority.haveRigth(user.getId(),"tjfx_jyxytj_check")||Authority.haveRigth(user.getId(),"tjfx_wbqytj_check")||Authority.haveRigth(user.getId(),"tjfx_wbcqtj_check")||Authority.haveRigth(user.getId(),"tjfx_dxtj_check")) {%>

			<li class="list-item">
				<p class="one grade"   >
					<i class=""></i>统计分析</p>
				<div class="next">
					<%if(Authority.haveRigth(user.getId(),"tjfx_gzqytj_check")) {%>
					<p	 class="two"   onclick="toMain('51')">故障区域统计</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"tjfx_gzlxtj_check")) {%>
					<p	 class="two"   onclick="toMain('52')">故障类型统计</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"tjfx_jyqytj_check")) {%>
					<p	  class="two"  onclick="toMain('53')">救援区域统计</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"tjfx_jyxytj_check")) {%>
					<p	  class="two"  onclick="toMain('54')">救援响应统计</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"tjfx_wbqytj_check")) {%>
					<p	  class="two"  onclick="toMain('55')">维保区域统计</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"tjfx_wbcqtj_check")) {%>
					<p	  class="two"  onclick="toMain('56')">维保出勤统计</p>
					<%} %>
					<%if(Authority.haveRigth(user.getId(),"tjfx_dxtj_check")) {%>
					<p	  class="two"  onclick="toMain('57')">短信统计</p>
					<%} %>
				</div>
			</li>
				<%} %>
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

	//setInterval('push()',10*1000); //指定30秒刷新一次s

	function push (){
		var pushId= document.getElementById("pushId").value;
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
								    autoHide:<%=dictionary%>,
								    onHide:clsoePush
								});
				        	  	g("msgclose").onclick=function() {//hide
									msg.hide();
								}
				        	  document.getElementById('pushId').value=json[0].id;
				         	  document.getElementById('news').innerHTML ="注册号："+json[0].registerid+"<br/>识别码："+json[0].distinguishid+"<br/>使用单位："+json[0].useUnitName+"<br/>安装地址："+json[0].installPlace+"<br/>报警时间："+json[0].alarmTime+"<br/>"+json[0].faultType+"<br/><br/>";
				          	  document.getElementById('music').play();		//开始播放
				          }
				         // setTimeout(msg.hide(),<%=dictionary%>*1000);
				         // window.clearTimeout(t1);//去掉定时器
				  },
				  error: function(result){
							//alert("操作失败!");
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
							//alert("操作失败!");
					}
		 });
		document.getElementById("pushId").value=="";
	}


	</script>

</html>
