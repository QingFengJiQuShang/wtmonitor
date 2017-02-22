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
    
    <title>年检记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list_add.css" />
		
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
				
	</head>

	<body>
	<form id="form" action="<%=path %>/inspectionAction.do?method=updateEntity" method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">年检记录</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改年检记录</p>
				
				<div class="table_con">
					<p class="fill">
						<label for="user">电梯注册号&nbsp;:&nbsp;</label>
						<input type="hidden" id="id"  name="inspection.id"  value="${list.id}" />
						<input type="hidden" id="elevatorId"  name="inspection.elevatorId.id"  value="${list.elevatorId.id}" />
						<input type="text"    value="${list.elevatorId.registerid}"  readonly="readonly"  />
						
					</p>
					<p class="fill">
						<label for="code">识别码&nbsp;:&nbsp;</label>
						<input type="text"    value="${list.elevatorId.distinguishid}"  readonly="readonly" />

					</p>
					<p class="fill">
						<label for="unit">使用单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="useUnitId"  name="inspection.useUnitId.id"  value="${list.elevatorId.propertyUnitId.id}"/>
						<input type="text"  id="useUnitId"   value="${list.elevatorId.propertyUnitId.name}"  readonly="readonly" />
					</p>
					<p class="fill">
						<label for="unit">物业单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="propertyUnitId"  name="inspection.propertyUnitId.id"  value="${list.elevatorId.propertyUnitId.id}"/>
						<input type="text"  id="propertyUnitId1"   value="${list.elevatorId.propertyUnitId.name}"  readonly="readonly" />
					</p>
					<p class="fill">
						<label for="place">安装地点&nbsp;:&nbsp;</label>
						<input type="text"    value="${list.elevatorId.installPlace}" readonly="readonly" />
					</p>
					<p class="fill">
						<label for="datetimepicker3">检验日期&nbsp;:&nbsp;</label>
						<input type="text" id="time"   class="Wdate"    name="time"  value="<fmt:formatDate value='${list.time}' pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
					</p>
					<p class="fill">
						<label for="datetimepicker3">下次年检时间&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"   id="nextTime"  name="nextTime"  value=""   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
					</p>
					<p class="fill">
						<label for="wb_unit">检验单位&nbsp;:&nbsp;</label>
						<input type="text" id="inspectionUnit"  name="inspection.inspectionUnit"  value="${list.inspectionUnit}"/>						
					</p>
					<p class="fill">
						<label for="wb_con">检验结果&nbsp;:&nbsp;</label>
						<input type="text" id="result"  name="inspection.result"  value="${list.result}" />
					</p>
					<p class="fill">
						<label for="beizhu">备注&nbsp;:&nbsp;</label>
						<input type="text" id="remarks"  name="inspection.remarks"  value="${list.remarks}" />
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
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
      $(function() {
		$("#time").on("click", function(e) {
			e.stopPropagation();
			$(this).lqdatetimepicker({
				css: 'datetime-day',
				dateType: 'D',
				selectback: function() {}
			});
		});
		});
</script>
</html>
