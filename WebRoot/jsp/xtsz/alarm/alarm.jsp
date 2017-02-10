<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglAuthority"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<XtglAuthority> authority=(List<XtglAuthority> )request.getAttribute("authority");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报警控制</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<script src="<%=path %>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
			<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		    <script src="<%=path%>/js/Share.js" type="text/javascript" charset="utf-8"></script>
	
		<style type="text/css">
			.warp {
				padding: 10px 0;
				border: 2px solid #ccc;
				-webkit-border-radius: 10px;
				-moz-border-radius: 10px;
				border-radius: 10px;
			}
			
			.level {
				padding: 10px 0;
				border-bottom: 1px solid #ccc;
			}
			
			.level:nth-child(3) {
				border-bottom: 0;
			}
			
			.level>p {
				font-size: 16px;
				padding: 10px;
			}
			
			.level_tow {
				padding-left: 6%;
			}
			
			.level_tow p {
				height: 20px;
				width: 15%;
				text-indent: 10px;
				line-height: 20px;
			}
			
			.or {
				width: 45%;
				margin: 30px auto 0;
			}
			
			.or button {
				width: 76px;
				height: 32px;
				background-color: #00aaee;
				color: #fff;
				font-size: 14px;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
				cursor: pointer;
			}
		</style>
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">报警控制</p>			
			<div class="warp">
				<form id="form" action="<%=path %>/usersAction.do?method=updateAlarm" method="post"  encType="multipart/form-data">
			
				<div class="level">
					<p>报警类型</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="bjkz_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="bjkz_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="bjkz_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="bjkz_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="bjkz_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="bjkz_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="bjkz_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="bjkz_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="bjkz_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="bjkz_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="bjkz_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="bjkz_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="bjkz_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="bjkz_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
				<!-- 		<p class="fl">
							<input type="checkbox" name="authority" id="send"  value="bjkz_00"/>
							<label for="or_stop">自动发送</label>
						</p> -->
					</div>
				</div>
				<div class="level">
					<p>提醒时间</p>
					
					<div class="level_tow  ">
						<p class="fill" style="width: 30%;">
							<label for="logn">提醒时间(秒)&nbsp;:&nbsp;</label>
							<input type="hidden"  name="dictionary.id"  id="id"  value="${list.id}">
							<input type="hidden"  name="dictionary.flag"  id="flag"  value="${list.flag}">
							<input type="hidden"  name="dictionary.remarks"  id="remarks"  value="${list.remarks}">
							<input type="text" id="dictionary"  name="dictionary.dictionary"  value="${list.dictionary}"  style="width: 150px;height: 29px;border: 1px solid #d2d2d2;text-indent: 10px;"/>
						</p>
						<p></p>
					</div>
				</div>
				<p class="or clearfix">
						<input type="button"  value="保存"  onclick="add();">
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</form>
	</div>
	</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	 <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
		function add(){
		 	 if(showIsNumber("dictionary","提醒时间")){
    		 $('#form').submit();
    	 }
     }

		  var obj=document.getElementsByName("authority");
	    <%for(int i=0;i<authority.size();i++){%>
			    	 for(var j=0;j<obj.length;j++){
					    	if(obj[j].value=='<%=authority.get(i).getKey()%>'){
								 obj[j].checked = true;
							}
					}
	  <%  } %>
</script>
</html>
