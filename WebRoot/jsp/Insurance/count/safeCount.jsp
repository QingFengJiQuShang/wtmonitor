<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>保单列表</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">保单列表</p>
			<div class="warp">
				<div class="select clearfix">

					<p class="fl" >
					<label for="start_end"  >制造单位&nbsp;:&nbsp;</label>
					<input type="hidden"  id="makeUnitId"  name="elevator.makeUnitId.id" value="${makeUnitId}"   />
						<input type="text"  id="makeUnitId1"   placeholder="请选择" value="${makeUnitId1}" readonly="readonly"  onclick="selectMakeUnitId('makeUnitId','makeUnitId1');"/>
				</p>
					<p class="fl"  >
					<label for="start_end" >使用单位&nbsp;:&nbsp;</label>
					<input type="hidden"  id="useUnitId"  name="elevator.useUnitId.id"  value="${useUnitId}" />
						<input type="text"  id="useUnitId1"   placeholder="请选择" value="${useUnitId1}"  readonly="readonly"  onclick="selectUseUnitId('useUnitId','useUnitId1');"/>
				</p>
				<p class="fl">
						<label for="user">物业单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="propertyUnitId"  name="elevator.propertyUnitId.id"  value="${propertyUnitId}" />
						<input type="text"  id="propertyUnitId1"  placeholder="请选择" value="${propertyUnitId1}"   readonly="readonly"   onclick="selectPropertyUnitId('propertyUnitId','propertyUnitId1');"/>

					</p>
					<p class="fl">
						<label for="user">维保单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUnitId"  name="elevator.maintenanceUnitId.id"  value="${maintenanceUnitId}" />
						<input type="text"  id="maintenanceUnitId1"   placeholder="请输入" value="${maintenanceUnitId1}"  readonly="readonly" onclick="selectMaintenanceUnit('maintenanceUnitId','maintenanceUnitId1');"/>
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
          <%if(Authority.haveRigth(user.getId(),"bxgl_bxtj_exp")) {%>
					<p class="fl add" onclick="exp();" style="width: 100px;">下载</p>
          <%}%>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th style="width: 80px;">电梯总数</th>
								<th>未保电梯数</th>
								<th>未保电梯率</th>
								<th>在保电梯数</th>
								<th>在保电梯率</th>
								<th>脱保电梯数</th>
								<th>脱保电梯率</th>
								<th>受理理赔次数</th>
								<th>受理理赔率</th>
								<th>最高理赔次数</th>
							</thead>
								<tr>
									<td>${safe.zong }</td>
									<td>${safe.wei }</td>
									<td>${safe.weiRate }</td>
									<td>${safe.zai }</td>
									<td>${safe.zaiRate }</td>
									<td>${safe.tuo }</td>
									<td>${safe.tuoRate }</td>
									<td>${safe.claimNum }</td>
									<td>${safe.claimRate }</td>
									<td>${safe.mostNum }</td>
									<td>

									</td>
								</tr>

							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
		<script src="<%=path%>/js/bxgl/safe.js" type="text/javascript" charset="utf-8"></script>

	<script type="text/javascript">
	function gotoUrl (){
			 var makeUnitId= document.getElementById("makeUnitId").value;
			 var makeUnitId1= document.getElementById("makeUnitId1").value;
			 var useUnitId= document.getElementById("useUnitId").value;
			 var useUnitId1= document.getElementById("useUnitId1").value;
			 var propertyUnitId= document.getElementById("propertyUnitId").value;
			 var propertyUnitId1= document.getElementById("propertyUnitId1").value;
			 var maintenanceUnitId= document.getElementById("maintenanceUnitId").value;
			  var maintenanceUnitId1= document.getElementById("maintenanceUnitId1").value;
			  var url="";
			  if(makeUnitId!=""){
				  url=url+"&makeUnitId="+makeUnitId;
			  }
			  if(makeUnitId1!=""){
				  url=url+"&makeUnitId1="+makeUnitId1;
			  }
			  if(useUnitId!=""){
				  url=url+"&useUnitId="+useUnitId;
			  }
			  if(useUnitId1!=""){
				  url=url+"&useUnitId1="+useUnitId1;
			  }
			  if(propertyUnitId!=""){
				  url=url+"&propertyUnitId="+propertyUnitId;
			  }
			  if(propertyUnitId1!=""){
				  url=url+"&propertyUnitId1="+propertyUnitId1;
			  }
			  if(maintenanceUnitId!=""){
				  url=url+"&maintenanceUnitId="+maintenanceUnitId;
			  }
			  if(maintenanceUnitId1!=""){
				  url=url+"&maintenanceUnitId1="+maintenanceUnitId1;
			  }
			  return url;
		  }
	 //模糊查询
		function query(){
    		  window.location.href="<%=path%>/safeAction.do?method=querySafe"+gotoUrl();
		  }
	  //下载
		  function exp(){
    		  window.location.href="<%=path%>/safeAction.do?method=exportSafe"+gotoUrl ();
         }
	</script>

</html>
