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

		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
		
	
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
					
				</div>
				<div class="fl fenzhi">
				<p>
						<label for="model">型号&nbsp;:&nbsp;</label>
						<input type="text"  id="model"  name="elevator.model"  placeholder="请输入"  value="${list.model}" />
					</p>
					<p>
						<label for="">注册状态</label>
						<select id="registerState"  name="elevator.registerState" >
							<option name="">请选择</option>
						  	<option <c:if test="${list.registerState=='已注册'}">selected="selected" </c:if> name="已注册">已注册</option>
						  	<option <c:if test="${list.registerState=='未注册'}">selected="selected" </c:if> name="未注册">未注册</option>
						</select>
					</p>
					
				</div>
			</div>
			<!--第二层-->
			<div class="record clearfix">
				<p class="name">电梯安装信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="user">使用单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="useUnitId"  name="elevator.useUnitId.id"    value="${list.useUnitId.id}" />
						<input type="text"  id="useUnitId1"   placeholder="请选择"   value="${list.useUnitId.name}"  readonly="readonly"  onclick="selectUseUnitId('useUnitId','useUnitId1');"/>
					</p>
					
					<p>
						<label for="mount">安装人员&nbsp;:&nbsp;</label>
						<input type="text"  id="installUser"  name="elevator.installUser"  placeholder="请输入"  value="${list.installUser}"  />
					</p>
					
					<p>
						<label for="datetimepicker4">使用年限&nbsp;:&nbsp;</label>
						<input type="text"  id="serviceIfe"  name="elevator.serviceIfe" class="form-control" placeholder="请选择"  value="${list.serviceIfe}" />
					</p>

				</div>
				<div class="fl fenzhi">
					<p onclick="tishi()">
						<label for="weibao_user">维保单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUnitId"  name="elevator.maintenanceUnitId.id"   value="${list.maintenanceUnitId.id}" />
						<input type="text"  id="maintenanceUnitId1"   placeholder="请输入" readonly="readonly" value="${list.maintenanceUnitId.name}"  onclick="selectMaintenanceUnit('maintenanceUnitId','maintenanceUnitId1');"/>
					</p>
					
					<p>
						<label for="time">安装时间&nbsp;:&nbsp;</label>
						<input type="text"  id="installTime"  name="installTime"  placeholder="请输入"   value="<fmt:formatDate value="${list.installTime}"  pattern='yyyy-MM-dd'/>" />
					</p>
					
				</div>
				<div class="fl fenzhi">
					<p onclick="tishi()">
						<label for="men">维保人&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUsersId"  name="elevator.maintenanceUsersId.id"  value="${list.maintenanceUsersId.id}"  />
						<input type="text"  id="maintenanceUsersId1"  placeholder="请选择" readonly="readonly"  value="${list.maintenanceUsersId.name}"   onclick="selectMaintenanceUsers('maintenanceUsersId','maintenanceUsersId1');"/>
					</p>
					
					<p>
						<label for="place">安装地点&nbsp;:&nbsp;</label>
						<input type="text"  id="installPlace"  name="elevator.installPlace"  placeholder="请输入"  value="${list.installPlace}" />
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="mount_user">安装单位&nbsp;:&nbsp;</label>
						<input type="text"  id="installUnit"  name="elevator.installUnit" placeholder="请输入"  value="${list.installUnit}" />
					</p>
					
					<p>
						<label for="datetimepicker3">生产日期&nbsp;:&nbsp;</label>
						<input type="text"  id="manufactureTime"  name="manufactureTime" class="form-control" placeholder="请选择" value="<fmt:formatDate value="${list.manufactureTime}"  pattern='yyyy-MM-dd'/>" />
						
						
					</p>
					
				</div>
			</div>
			<!--第三层-->
			<div class="maker clearfix">
				<p class="name">电梯制造商信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="maker">制造商&nbsp;:&nbsp;</label>
						<input type="text"   id="manufacturer"  name="elevator.manufacturer" placeholder="请输入" value="${list.manufacturer}" />
					</p>
					<p>
						<label for="maker_web">制造商网站&nbsp;:&nbsp;</label>
						<input type="text"  id="manufacturerUrl"  name="elevator.manufacturerUrl"   placeholder="请输入"  value="${list.manufacturerUrl}" />
					</p>
					<p>
						<label for="make_phone">本地分公司电话&nbsp;:&nbsp;</label>
						<input type="text"  id="filialePhone"  name="elevator.filialePhone" class="form-control" placeholder="请选择" value="${list.filialePhone}" />
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="maker_address">制造商地址&nbsp;:&nbsp;</label>
						<input type="text"  id="manufacturerAddress"  name="elevator.manufacturerAddress"  placeholder="请输入" value="${list.manufacturerAddress}" />
					</p>
					<p>
						<label for="maker">本地分公司地址&nbsp;:&nbsp;</label>
						<input type="text"  id="filialeAddress"  name="elevator.filialeAddress" placeholder="请输入" value="${list.filialeAddress}" />
					</p>
					
					
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="maker_sure">制作商电话&nbsp;:&nbsp;</label>
						<input type="text"  id="manufacturerPhone"  name="elevator.manufacturerPhone"  placeholder="请输入"  value="${list.manufacturerPhone}" />
					</p>
					<p>
						<label for="make_men">本地分公司联系人&nbsp;:&nbsp;</label>
						<input type="text"  id="filialeContact"  name="elevator.filialeContact" placeholder="请输入" value="${list.filialeContact}" />
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
