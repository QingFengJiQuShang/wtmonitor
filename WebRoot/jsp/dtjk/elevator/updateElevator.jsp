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
    
    <title>电梯数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list_add.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/dtjk_comm.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
		
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		
	
	</head>

	<body>
	<form id="form" action="<%=path %>/elevatorAction.do?method=updateEntity" method="post"  encType="multipart/form-data">
		<div class="con">
		<p class="user">电梯列表</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			
		<div class="wrap">
			<!--第一层-->
			<div class="record clearfix">
				<p class="name">电梯基本信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="apply">注册号&nbsp;:&nbsp;</label>
						<input type="hidden"  id="id"  name="elevator.id"  value="${list.id}"  />
						<input type="hidden"  id="userid"  name="elevator.userid.id"   value="${list.userid.id}"  />
						<input type="hidden"  id="gatewayId"  name="elevator.gatewayId.id"   value="${list.gatewayId.id}"  />
						<input type="text"  placeholder="请输入"  id="registerid"  name="elevator.registerid"   value="${list.registerid}" onblur="skip();"/>
					</p>
					<p>
						<label for="style">类型&nbsp;:&nbsp;</label>
						<select id="type"  name="elevator.type" >
							<option name="">请选择</option>
						  	<option <c:if test="${list.type=='直梯'}">selected="selected" </c:if> name="直梯">直梯</option>
						  	<option <c:if test="${list.type=='扶梯'}">selected="selected" </c:if>  name="扶梯">扶梯</option>
						</select>
					</p>
					<p onclick="label('label');">
						<label for="map">地图标注&nbsp;:&nbsp;</label>
						<input type="text" placeholder="请选择" readonly="readonly"  id="label"  name="elevator.label"  value="${list.label}" />
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="cord">识别码&nbsp;:&nbsp;</label>
						<input type="text"  id="distinguishid"  name="elevator.distinguishid"  placeholder="请输入"  value="${list.distinguishid}" />
					</p>
				<p>
						<label for="speed">电梯速度&nbsp;:&nbsp;</label>
						<input type="text"  id="speed"  name="elevator.speed"  placeholder="请输入"  value="${list.speed}" />
					</p>
					<p>
						<label for="model">视频IP&nbsp;:&nbsp;</label>
						<input type="text"  id="ip"  name="elevator.ip" value="${list.ip}" placeholder="请输入" />
					</p>
					
					
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="barnd">品牌&nbsp;:&nbsp;</label>
						<input type="text"  id="brand"  name="elevator.brand"  placeholder="请输入"  value="${list.brand}" />
					</p>
					<p>
						<label for="num">总层数&nbsp;:&nbsp;</label>
						<input type="text"  id="numbers"  name="elevator.numbers"  placeholder="请输入"  value="${list.numbers}" />
					</p>
					<p>
						<label for="datetimepicker3">维保合同期起&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  id="flowStart"  name="flowStart"     value="<fmt:formatDate value='${list.flowStart}' pattern='yyyy-MM-dd'/>"  class="form-control" placeholder="请选择" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
					</p>
				</div>
				<div class="fl fenzhi">
				<p>
						<label for="model">型号&nbsp;:&nbsp;</label>
						<input type="text"  id="model"  name="elevator.model"  placeholder="请输入"  value="${list.model}" />
					</p>
					<p>
						<label for="">注册状态&nbsp;:&nbsp;</label>
						<select id="registerState"  name="elevator.registerState" >
							<option name="">请选择</option>
						  	<option <c:if test="${list.registerState=='已注册'}">selected="selected" </c:if> name="已注册">已注册</option>
						  	<option <c:if test="${list.registerState=='未注册'}">selected="selected" </c:if> name="未注册">未注册</option>
						</select>
					</p>
					<p>
						<label for="datetimepicker3">维保合同期至&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  id="flowEnd"  name="flowEnd"    value="<fmt:formatDate value='${list.flowEnd}' pattern='yyyy-MM-dd'/>"  class="form-control" placeholder="请选择" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
					</p>
				</div>
			</div>
			<!--第二层-->
			<div class="record clearfix">
				<p class="name">电梯安装信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="user">使用单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="useUnitId"  name="elevator.useUnitId.id"  value="${list.useUnitId.id}" />
						<input type="text"  id="useUnitId1"   placeholder="请选择"  value="${list.useUnitId.name}"  readonly="readonly"  onclick="selectUseUnitId('useUnitId','useUnitId1');"/>
					</p>
					<p>
						<label for="mount_user">物业单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="propertyUnitId"  name="elevator.propertyUnitId.id"    value="${list.propertyUnitId.id}"  />
						<input type="text"  id="propertyUnitId1"  placeholder="请选择" readonly="readonly"    value="${list.propertyUnitId.name}"    onclick="selectPropertyUnitId('propertyUnitId','propertyUnitId1');"/>
					
					</p>
					<p >
						<label for="men">维保人&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUsersId"  name="elevator.maintenanceUsersId.id"   value="${list.maintenanceUsersId.id}"   />
						<input type="text"  id="maintenanceUsersId1"  placeholder="请选择" readonly="readonly"    value="${list.maintenanceUsersId.name}"   onclick="selectMaintenanceUsers('maintenanceUsersId','maintenanceUsersId1');"/>
					</p>
					<p>
						<label for="time">安装时间&nbsp;:&nbsp;</label>
						<input type="text"  class="Wdate"     value="<fmt:formatDate value='${list.installTime}' pattern='yyyy-MM-dd'/>"  id="installTime"  name="installTime"  placeholder="请输入"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  />
						
					</p>
					

				</div>
				<div class="fl fenzhi">
					<p >
						<label for="weibao_user">电梯负责人&nbsp;:&nbsp;</label>
						<input type="text"  id="useUnitLiaisons"   name="elevator.useUnitLiaisons"  value="${list.useUnitLiaisons}"  placeholder="请输入"  />
					</p>
					<p >
						<label for="weibao_user">电梯负责人&nbsp;:&nbsp;</label>
						<input type="text"  id="propertyUnitLiaisons"   name="elevator.propertyUnitLiaisons" value="${list.propertyUnitLiaisons}" placeholder="请输入"  />
					</p>
					
					<p>
						<label for="mount_user">安装单位&nbsp;:&nbsp;</label>
						<input type="text"  id="installUnit"  name="elevator.installUnit"   value="${list.installUnit}"   placeholder="请输入" />
					</p>
					<p>
						<label for="datetimepicker3">生产日期&nbsp;:&nbsp;</label>
						<input type="text"  class="Wdate"   value="<fmt:formatDate value='${list.manufactureTime}' pattern='yyyy-MM-dd'/>"   id="manufactureTime"  name="manufactureTime"  placeholder="请选择"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
						
					</p>
					
				</div>
				<div class="fl fenzhi">
					<p >
						<label for="weibao_user">负责人电话&nbsp;:&nbsp;</label>
						<input type="text"  id="useUnitPhone" name="elevator.useUnitPhone" value="${list.useUnitPhone}"  placeholder="请输入" />
					</p>
					<p >
						<label for="weibao_user">负责人电话&nbsp;:&nbsp;</label>
						<input type="text"  id="propertyUnitPhone" name="elevator.propertyUnitPhone"  value="${list.propertyUnitPhone}"   placeholder="请输入"  />
					</p>
					
					
					<p>
						<label for="place">安装地点&nbsp;:&nbsp;</label>
						<input type="text"  id="installPlace"  name="elevator.installPlace"  placeholder="请输入"   value="${list.installPlace}"  />
					</p>
					<p>
						<label for="datetimepicker4">使用年限&nbsp;:&nbsp;</label>
						<input type="text"  id="serviceIfe"  name="elevator.serviceIfe" class="form-control" placeholder="请选择"    value="${list.serviceIfe}"  />
					</p>
					
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="user">制造单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="makeUnitId"  name="elevator.makeUnitId.id"   value="${list.makeUnitId.id}"    />
						<input type="text"  id="makeUnitId1"   placeholder="请选择"  value="${list.makeUnitId.name}"   readonly="readonly"  onclick="selectMakeUnitId('makeUnitId','makeUnitId1');"/>
					</p>
					<p >
						<label for="weibao_user">维保单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUnitId"   value="${list.maintenanceUnitId.id}"    name="elevator.maintenanceUnitId.id"  />
						<input type="text"  id="maintenanceUnitId1"    value="${list.maintenanceUnitId.name}"   placeholder="请输入" readonly="readonly" onclick="selectMaintenanceUnit('maintenanceUnitId','maintenanceUnitId1');"/>
					</p>
					<p>
						<label for="mount">安装人员&nbsp;:&nbsp;</label>
						<input type="text"  id="installUser"  name="elevator.installUser"  placeholder="请输入"   value="${list.installUser}"  />
					</p>
					<p>
						<label for="datetimepicker4">首次年检时间&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  id="yearlyTime1"  name="yearlyTime1"  value="<fmt:formatDate value='${list.yearlyTime1}' pattern='yyyy-MM-dd'/>"    class="form-control" placeholder="请选择" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
					</p>
					
					
				</div>
			
			</div>
			<p class="or clearfix">
				<input type="button"  value="保存"  onclick="add();">
				<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
			</p>
		</div>

		</div>
		</form>
	</body>
		<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/selectUi.js"></script></head>
	    <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
      $(function() {
		$("#installTime").on("click", function(e) {
			e.stopPropagation();
			$(this).lqdatetimepicker({
				css: 'datetime-day',
				dateType: 'D',
				selectback: function() {}
			});
		});
		$("#manufactureTime").on("click", function(e) {
			e.stopPropagation();
			$(this).lqdatetimepicker({
				css: 'datetime-day',
				dateType: 'D',
				selectback: function() {}
			});
		});
	});
      
       //新增电梯时 判断电梯注册号是否存在
		  function skip(){
				var registerid=document.getElementById("registerid").value;
				if(submit(registerid)==true){
					$.ajax({
						     mtype:'post',
				             url: "elevatorAction.do?method=onlyRegisterid",
				             data: {"registerid":registerid},
				             dataType: "text",
				             success: function(data){
				                       if(data==0) {
				                    	   alert("该电梯注册号已存在，请重新输入！");
				                    	   $("#registerid").focus();
				                      }
				               }
				    });
				}
	}
		 function submit(registerid){
				if(registerid.length!=20&&registerid!=""){
					alert("电梯注册号长度为20位，请重新输入！");
					$("#registerid").focus();
					return false;
				}
				return true;
		 }
     </script>
</html>
