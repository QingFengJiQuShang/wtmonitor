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
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<script src="<%=path%>/js/region.js" type="text/javascript" charset="utf-8"></script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		
	
	</head>

	<body>
	<form id="form" action="<%=path %>/elevatorAction.do?method=addEntity" method="post"  encType="multipart/form-data">
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
						<input type="hidden"  id="gatewayId"  name="elevator.gatewayId.id"   />
						<input type="text"  placeholder="请输入"  id="registerid"  name="elevator.registerid"  onblur="skip();"/>
					</p>
					<p>
						<label for="style">类型&nbsp;:&nbsp;</label>
						<select id="type"  name="elevator.type" >
							<option name="">请选择</option>
						  	<option name="直梯">直梯</option>
						  	<option name="扶梯">扶梯</option>
						</select>
					</p>
					<p onclick="label('label');">
						<label for="map">地图标注&nbsp;:&nbsp;</label>
						<input type="text" placeholder="请选择" readonly="readonly"  id="label"  name="elevator.label" />
					</p>
					<p class="fill">
						<label for="logn">省&nbsp;:&nbsp;</label>
						<select  name="elevator.province"  id="province"   >
						<option value="">请选择</option>
						</select>
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="cord">识别码&nbsp;:&nbsp;</label>
						<input type="text"  id="distinguishid"  name="elevator.distinguishid"  placeholder="请输入" />
					</p>
				<p>
						<label for="speed">电梯速度&nbsp;:&nbsp;</label>
						<input type="text"  id="speed"  name="elevator.speed"  placeholder="请输入" />
					</p>
					<p>
						<label for="model">视频IP&nbsp;:&nbsp;</label>
						<input type="text"  id="ip"  name="elevator.ip"  placeholder="请输入" />
					</p>
					
					<p class="fill">
						<label for="logn">市&nbsp;:&nbsp;</label>
						<select  name="elevator.city"  id="city" >
						<option value="">请选择</option>
						</select>
					</p>
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="barnd">品牌&nbsp;:&nbsp;</label>
						<input type="text"  id="brand"  name="elevator.brand"  placeholder="请输入" />
					</p>
					<p>
						<label for="num">总层数&nbsp;:&nbsp;</label>
						<input type="text"  id="numbers"  name="elevator.numbers"  placeholder="请输入" />
					</p>
					<p>
						<label for="datetimepicker3">维保合同期起&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  id="flowStart"  name="flowStart"  class="form-control" placeholder="请选择" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
					</p>
					
					<p class="fill">
						<label for="logn">区&nbsp;:&nbsp;</label>
						<select  name="elevator.area"  id="area" onclick="area();">
							<option value="">请选择</option>
						</select>
					</p>
				</div>
				<div class="fl fenzhi">
				<p>
						<label for="model">型号&nbsp;:&nbsp;</label>
						<input type="text"  id="model"  name="elevator.model"  placeholder="请输入" />
					</p>
					<p>
						<label for="">注册状态</label>
						<select id="registerState"  name="elevator.registerState" >
							<option name="">请选择</option>
						  	<option name="已注册">已注册</option>
						  	<option name="未注册">未注册</option>
						</select>
					</p>
					<p>
						<label for="datetimepicker3">维保合同期至&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  id="flowEnd"  name="flowEnd"  class="form-control" placeholder="请选择" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
					</p>
					
				</div>
			</div>
			<!--第二层-->
			<div class="record clearfix">
				<p class="name">电梯安装信息</p>
				<div class="fl fenzhi">
					<p>
						<label for="user">使用单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="useUnitId"  name="elevator.useUnitId.id"   />
						<input type="text"  id="useUnitId1"   placeholder="请选择" readonly="readonly"  onclick="selectUseUnitId('useUnitId','useUnitId1');"/>
					</p>
					<p>
						<label for="mount_user">物业单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="propertyUnitId"  name="elevator.propertyUnitId.id"  />
						<input type="text"  id="propertyUnitId1"  placeholder="请选择" readonly="readonly"   onclick="selectPropertyUnitId('propertyUnitId','propertyUnitId1');"/>
					
					</p>
					<p onclick="tishi()">
						<label for="men">维保人&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUsersId"  name="elevator.maintenanceUsersId.id"  />
						<input type="text"  id="maintenanceUsersId1"  placeholder="请选择" readonly="readonly"   onclick="selectMaintenanceUsers('maintenanceUsersId','maintenanceUsersId1');"/>
					</p>
					<p>
						<label for="time">安装时间&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  id="installTime"  name="installTime"  placeholder="请输入"  />
						
					</p>
					

				</div>
				<div class="fl fenzhi">
					<p >
						<label for="weibao_user">电梯负责人&nbsp;:&nbsp;</label>
						<input type="text"  id="useUnitLiaisons"   name="elevator.useUnitLiaisons" placeholder="请输入"  />
					</p>
					<p >
						<label for="weibao_user">电梯负责人&nbsp;:&nbsp;</label>
						<input type="text"  id="propertyUnitLiaisons"   name="elevator.propertyUnitLiaisons" placeholder="请输入" />
					</p>
					
					<p>
						<label for="mount_user">安装单位&nbsp;:&nbsp;</label>
						<input type="text"  id="installUnit"  name="elevator.installUnit" placeholder="请输入" />
					</p>
					<p>
						<label for="datetimepicker3">生产日期&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  id="manufactureTime"  name="manufactureTime"  class="form-control" placeholder="请选择" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
					</p>
					
				</div>
				<div class="fl fenzhi">
					<p >
						<label for="weibao_user">负责人电话&nbsp;:&nbsp;</label>
						<input type="text"  id="useUnitPhone" name="elevator.useUnitPhone"   placeholder="请输入" />
					</p>
					<p >
						<label for="weibao_user">负责人电话&nbsp;:&nbsp;</label>
						<input type="text"  id="propertyUnitPhone" name="elevator.propertyUnitPhone"   placeholder="请输入"  />
					</p>
					
					
					<p>
						<label for="place">安装地点&nbsp;:&nbsp;</label>
						<input type="text"  id="installPlace"  name="elevator.installPlace"  placeholder="请输入" />
					</p>
					<p>
						<label for="datetimepicker4">使用年限&nbsp;:&nbsp;</label>
						<input type="text"  id="serviceIfe"  name="elevator.serviceIfe" class="form-control" placeholder="请选择" />
					</p>
					
				</div>
				<div class="fl fenzhi">
					<p>
						<label for="user">制造单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="makeUnitId"  name="elevator.makeUnitId.id"   />
						<input type="text"  id="makeUnitId1"   placeholder="请选择" readonly="readonly"  onclick="selectMakeUnitId('makeUnitId','makeUnitId1');"/>
					</p>
					<p >
						<label for="weibao_user">维保单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUnitId"  name="elevator.maintenanceUnitId.id"  />
						<input type="text"  id="maintenanceUnitId1"   placeholder="请输入" readonly="readonly" onclick="selectMaintenanceUnit('maintenanceUnitId','maintenanceUnitId1');"/>
					</p>
					<p>
						<label for="mount">安装人员&nbsp;:&nbsp;</label>
						<input type="text"  id="installUser"  name="elevator.installUser"  placeholder="请输入" />
					</p>
					<p>
						<label for="datetimepicker4">首次年检时间&nbsp;:&nbsp;</label>
						<input type="text"   class="Wdate"  id="yearlyTime1"  name="yearlyTime1"  class="form-control" placeholder="请选择" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" >
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
