<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

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
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
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
				<div class="level">
					<p>使用单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="speed" id="speed"/>
							<label for="speed" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="kun"/>
							<label for="kun">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="rush"/>
							<label for="rush">冲顶</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="end"/>
							<label for="end">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="stop"/>
							<label for="stop">停电</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="selected" id="colse"/>
							<label for="colse">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="open"/>
							<label for="open">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="rush_kun"/>
							<label for="rush_kun">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="end_kun"/>
							<label for="end_kun">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="loding"/>
							<label for="loding">运动中开门</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="selected" id="open_or"/>
							<label for="open_or">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="arrive"/>
							<label for="arrive">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="or_kun"/>
							<label for="or_kun">非平层困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="or_stop"/>
							<label for="or_stop">非平层停梯</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>维保单位权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="speed" id="speed2"/>
							<label for="speed2" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="kun2"/>
							<label for="kun2">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="rush2"/>
							<label for="rush2">冲顶</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="end2"/>
							<label for="end2">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="stop2"/>
							<label for="stop2">停电</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="selected" id="colse2"/>
							<label for="colse2">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="open2"/>
							<label for="open2">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="rush_kun2"/>
							<label for="rush_kun2">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="end_kun2"/>
							<label for="end_kun2">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="loding2"/>
							<label for="loding2">运动中开门</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="selected" id="open_or2"/>
							<label for="open_or2">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="arrive2"/>
							<label for="arrive2">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="or_kun2"/>
							<label for="or_kun2">非平层困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="or_stop2"/>
							<label for="or_stop2">非平层停梯</label>
						</p>
					</div>
				</div>
				<div class="level">
					<p>系统用户权限</p>
					<div class="level_tow clearfix">
						<p class="fl">
							<input type="checkbox" name="speed" id="speed3"/>
							<label for="speed3" >超速</lael>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="kun3"/>
							<label for="kun3">困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="rush3"/>
							<label for="rush3">冲顶</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="end3"/>
							<label for="end3">蹲底</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="stop3"/>
							<label for="stop3">停电</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="selected" id="colse3"/>
							<label for="colse3">门关不上</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="open3"/>
							<label for="open3">开门走梯</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="rush_kun3"/>
							<label for="rush_kun3">冲顶困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="end_kun3"/>
							<label for="end_kun3">蹲底困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="loding3"/>
							<label for="loding3">运动中开门</label>
						</p>
						<p></p>
						<p class="fl">
							<input type="checkbox" name="selected" id="open_or3"/>
							<label for="open_or3">非平层开门</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="arrive3"/>
							<label for="arrive3">开门不到位</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="or_kun3"/>
							<label for="or_kun3">非平层困人</label>
						</p>
						<p class="fl">
							<input type="checkbox" name="selected" id="or_stop3"/>
							<label for="or_stop3">非平层停梯</label>
						</p>
					</div>
				</div>
				<div class="or clearfix">
					<button class="fr">保存</button>
					<button class="fl">取消</button>
				</div>
	</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	 <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>

</html>
