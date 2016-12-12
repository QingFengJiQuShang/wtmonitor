<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人工接警</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list_add.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>

		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/calendar.css" />
		
	</head>

	<body>
	<form id="form" action="<%=path %>/alarmAction.do?method=addEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">人工接警</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">新增人工接警</p>
				<div class="table_con">
					<p class="fill">
						<label for="name">电梯注册号&nbsp;:&nbsp;</label>
						<input type="hidden" id="elevatorId"  name="alarm.elevatorId.id"  />
						<input type="text" id="elevatorId1"  readonly="readonly"   onclick="selectElevator('elevatorId','elevatorId1','place')"/>
					</p>
					<p class="fill">
						<label for="logn">电梯安装地址&nbsp;:&nbsp;</label>
						<input type="text" id="place"    readonly="readonly"    />
					</p>
					<p class="fill">
						<label for="man">接警时间&nbsp;:&nbsp;</label>
						<input type="text" id="time"  name="time"  value="<fmt:formatDate value="<%=new Date() %>"  pattern='yyyy-MM-dd HH:mm:ss'/>"/>
					</p>
					<p class="fill">
						<label for="man">故障发生时间&nbsp;:&nbsp;</label>
						<input type="text" id="happenTime"  name="happenTime"  value="<fmt:formatDate value="<%=new Date() %>"  pattern='yyyy-MM-dd HH:mm:ss'/>"/>
					</p>
					<p class="fill">
						<label for="phone">报警人&nbsp;:&nbsp;</label>
						<input type="text" id="alarmPerson"  name="alarm.alarmPerson"  />
					</p>
					<p class="fill">
						<label for="area">报警人电话&nbsp;:&nbsp;</label>
						<input type="text" id="phone"  name="alarm.phone"  />
					</p>
					<p class="fill">
						<label for="area">故障问题&nbsp;:&nbsp;</label>
						<!-- 
						<input type="text" id="fault"  name="alarm.fault"  /> -->
						<select id="fault"  name="alarm.fault"  />
							<option  value="">请选择</option>
						    <option  value="超速">超速</option>
						    <option  value="困人">困人</option>
						    <option  value="门关不上">门关不上</option>
						    <option  value="冲顶困人">冲顶困人</option>
						    <option  value="冲顶">冲顶</option>
						    <option  value="蹲底困人">蹲底困人</option>
						    <option  value="蹲底">蹲底</option>
						    <option  value="开门走梯">开门走梯</option>
						    <option  value="运动中开门">运动中开门</option>
						    <option  value="非平层困人">非平层困人</option>
						    <option  value="非平层停梯">非平层停梯</option>
						    <option  value="停电">停电</option>
						     <option  value="开门不到位">开门不到位</option>
						    <option  value="非平层开门">非平层开门</option>
						</select>
					</p>
					<p class="fill">
						<label for="area">故障描述&nbsp;:&nbsp;</label>
						<textarea rows="3" cols="50" id="describe"  name="alarm.describe"  ></textarea>
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
	    <script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/selectUi.js"></script></head>
	    <script src="<%=path %>/js/calendar.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     //	日期插件
		date = new Date();
		Calendar.setup({
					inputField     :    "happenTime",
					ifFormat       :    "%Y-%m-%d %H:%M:%S",
					showsTime      :    true,
					timeFormat     :    "24"
		});
		Calendar.setup({
					inputField     :    "time",
					ifFormat       :    "%Y-%m-%d %H:%M:%S",
					showsTime      :    true,
					timeFormat     :    "24"
		});
    
     </script>
</html>
