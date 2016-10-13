<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电梯信息列表</title>
    
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/file/file_dianti.css" />
		
	   <link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
	
	<body>
		
		<div class="wrap clearfix">
			<div class="con fl">			
				<div class="con_right">	
					<div class="add_info">
						<form id="form" action="<%=path %>/elevatorAction.do?method=updateEntity" method="post"  encType="multipart/form-data">
							<ul>
								<li class="clearfix">
									<p class="fl">
										<label for="user">注册号&nbsp;:&nbsp;</label>
										<input type="hidden" id="id"   name="elevator.id"  value="${list.id}" />
										<input type="text" id="registerid"   name="elevator.registerid"  value="${list.registerid}" />
									</p>
									<p class="fl">
										<label for="code">识别码&nbsp;:&nbsp;</label>
										<input type="text" id="distinguishid"  name="elevator.distinguishid" value="${list.distinguishid}" />
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="brand">电梯品牌&nbsp;:&nbsp;</label>
										<select name="elevator.brand" id="brand">
											<option <c:if test="${list.brand=='三菱'}">selected="selected" </c:if>  value="三菱">三菱</option>
											<option  <c:if test="${list.brand=='三菱'}">selected="selected" </c:if>  value="中慧">中慧</option>
										</select>
									</p>
									<p class="fl">
										<label for="model">电梯型号&nbsp;:&nbsp;</label>
										<select name="elevator.model" id="model">
											<option <c:if test="${list.model=='abc'}">selected="selected" </c:if>   value="abc">abc</option>
											<option  <c:if test="${list.model=='def'}">selected="selected" </c:if>  value="def">def</option>
										</select>
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="style">电梯类型&nbsp;:&nbsp;</label>
										<select name="elevator.type" id="type">
											<option <c:if test="${list.type=='直梯'}">selected="selected" </c:if>   value="直梯">直梯</option>
											<option <c:if test="${list.type=='扶梯'}">selected="selected" </c:if>   value="扶梯">扶梯</option>
										</select>
									</p>
									<p class="fl">
										<label for="wangguan">电梯网关&nbsp;:&nbsp;</label>
										<input type="text" id="gatewayId"  name="elevator.gatewayId"  value="${list.gatewayId}" />										
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="data">生产日期&nbsp;:&nbsp;</label>
										<input type="text" id="manufactureTime"   name="time"  value="<fmt:formatDate value='${list.manufactureTime}'  pattern='yyyy-MM-dd'  />"  /> 
										
									</p>
									<p class="fl">
										<label for="state">注册状态&nbsp;:&nbsp;</label>
										<input type="text" id="state"  name="elevator.state"  value="${list.state}" />
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="floor">总层数&nbsp;:&nbsp;</label>
										<select  name="elevator.numbers" id="numbers"  value="${list.numbers}" >
											<option <c:if test="${list.numbers=='10'}">selected="selected" </c:if>   value="10">10</option>
											<option  <c:if test="${list.numbers=='15'}">selected="selected" </c:if>  value="15">15</option>
										</select>
									</p>
									<p class="fl">
										<label for="map">地图标注&nbsp;:&nbsp;</label>
										<input type="text" id="label"   name="elevator.label"  value="${list.label}" />
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="length">长度&nbsp;:&nbsp;</label>
										<select  name="elevator.lengths" id="lengths">
											<option  <c:if test="${list.lengths=='10'}">selected="selected" </c:if>  value="10">10米</option>
											<option <c:if test="${list.lengths=='20'}">selected="selected" </c:if>   value="20">20米</option>
										</select>
									</p class="fl">
									<p class="fl">
										<label for="place">安装地点&nbsp;:&nbsp;</label>
										<input type="text" id="place"  name="elevator.place"  value="${list.place}"  />
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="user_pany">使用单位&nbsp;:&nbsp;</label>
										<input type="text" id="useUnitId"   name="elevator.useUnitId"  value="${list.useUnitId}" />
									</p>
									<p class="fl">
										<label for="weih_pany">维保单位&nbsp;:&nbsp;</label>
										<input type="text" id="maintenanceUnitId"  name="elevator.maintenanceUnitId"  value="${list.maintenanceUnitId}" />
									</p>
								</li>
								<li class="clearfix">
									<p>
										<label for="man">维保人&nbsp;:&nbsp;</label>
										<input type="text" id="man"   name="elevator."  />
									</p>
								</li>
							</ul>
							
							<p class="clearfix sub">
								<label for="">&nbsp;</label>
								<input type="submit" class="sure" value="确定"  onclick="add();"></input>
								<input type="reset" class="reset" value="重置"  onclick="reset();">
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
		</div>
		<script type="text/javascript" src="<%=path %>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/selectUi.js"></script></head>
	   <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     $(function() {
	$("#manufactureTime").on("click", function(e) {
		e.stopPropagation();
		$(this).lqdatetimepicker({
			css: 'datetime-day',
			dateType: 'D',
			selectback: function() {}
		});
	});
	
});
</script>
  </body>
</html>
