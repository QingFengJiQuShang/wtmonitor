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
    
    <title>短信权限</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		
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
			<p class="user">短信权限</p>			
			<div class="warp">
				<form id="form" action="<%=path %>/usersAction.do?method=updateMessage" method="post"  encType="multipart/form-data">
			
				<div class="level">
					<p>使用单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="sy_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="sy_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="sy_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="sy_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="sy_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="sy_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="sy_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="sy_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="sy_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="sy_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="sy_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="sy_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="sy_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="sy_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="sy_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>维保单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="wb_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="wb_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="wb_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="wb_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="wb_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="wb_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="wb_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="wb_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="wb_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="wb_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="wb_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="wb_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="wb_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wb_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="wb_00"/>
							<label for="or_stop">自动发送</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>系统用户权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="authority" id="authority"  value="xt_01"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="kun" value="xt_02"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush"  value="xt_03"/>
							<label for="rush">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end"  value="xt_04"/>
							<label for="end">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="stop"  value="xt_05"/>
							<label for="stop">冲顶</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="colse"  value="xt_06"/>
							<label for="colse">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open"  value="xt_07"/>
							<label for="open">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="rush_kun"  value="xt_08"/>
							<label for="rush_kun">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="end_kun"  value="xt_09"/>
							<label for="end_kun">运动中开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="loding"  value="xt_0a"/>
							<label for="loding">非平层困人</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="authority" id="open_or"  value="xt_0b"/>
							<label for="open_or">非平层停梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="arrive"  value="xt_0c"/>
							<label for="arrive">停电</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_kun"  value="xt_0d"/>
							<label for="or_kun">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="xt_10"/>
							<label for="or_stop">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="authority" id="or_stop"  value="xt_00"/>
							<label for="or_stop">自动发送</label>
						</p>
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
		 	$('#form').submit();
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
