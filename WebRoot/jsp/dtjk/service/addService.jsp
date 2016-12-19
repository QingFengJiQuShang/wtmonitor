<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电梯流量</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
	
	</head>

	<body>
	<form id="form"  method="post"  encType="multipart/form-data">
		<div class="con">
				<p class="user">电梯服务费</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
					<p class="add">电梯服务费登记</p>
				<div class="table_con">
					<p class="fill">
					<label for="user">电梯注册号&nbsp;:&nbsp;</label>
					<input type="hidden" id="elevatorId"  name="inspection.elevatorId.id"  value="${list.id}" />
						<input type="text"    value="${list.registerid}"  readonly="readonly"  />
				</p>
				<p class="fill">
					<label for="code">识别码&nbsp;:&nbsp;</label>
					<input type="text"    value="${list.distinguishid}"  readonly="readonly" />
				</p>
				<p class="fill">
					<label for="unit">电梯使用单位&nbsp;:&nbsp;</label>
					<input type="text"  id="useUnitId"   value="${list.useUnitId.name}"  readonly="readonly" />
				</p>
				<p class="fill">
					<label for="start_end">服务开始时间&nbsp;:&nbsp;</label>
					<input type="text"  class="Wdate"   id="start_end"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
				</p>
				<p class="fill">
					<label for="start_end">服务结束时间&nbsp;:&nbsp;</label>
					<input type="text"  class="Wdate"  	 id="time_end"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
				</p>
				<p class="fill">
					<label for="place">服务金额&nbsp;:&nbsp;</label>
					<input type="text" id="place" placeholder="请输入"/>
				</p>

				<p class="fill">
					<label for="wb_unit">服务类型&nbsp;:&nbsp;</label>
					<select name="">
						<option value="">请选择</option>
					</select>
				</p>
					<div class="keep clearfix">
				<!-- 	<input type="button"  class="fl"  value="保存"   onclick="add();"/> -->	
						<button class="fl"    onclick="add();">保存</button>
						<button class="fr"   onclick="history.go(-1); ">取消</button>
					</div>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     
     </script>
</html>
