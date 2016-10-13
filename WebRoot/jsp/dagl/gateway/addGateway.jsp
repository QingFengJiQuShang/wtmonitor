<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/file/file_wang.css" />	
	   <link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
	
	<body>
		
		<div class="wrap clearfix">
			<div class="con fl">			
				<div class="con_right">	
					<div class="add_info">
						<form id="form" action="<%=path %>/gatewayAction.do?method=addEntity" method="post"  encType="multipart/form-data">
							<ul>
								<li class="clearfix">
									<p class="fl">
										<label for="style">电梯网关类型&nbsp;:&nbsp;</label>
										<select name="gateway.type" id="type">
											<option value="并行网关">并行网关</option>
											<option value="事件网关">事件网关</option>
											<option value="包含网关">包含网关</option>
										</select>
									</p>
									<p class="fl">
											<label for="flow">取流方式&nbsp;:&nbsp;</label>
											<select name="gateway.flow"  id="flow">
												<option value="并联">并联</option>
												<option value="串联">串联</option>
											</select>
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="net">入网方式&nbsp;:&nbsp;</label>
										<select name="gateway.net" id="net">
											<option value="有线">有线</option>
											<option value="无线">无线</option>
										</select>
									</p>
									<p class="fl">
										<label for="line">通信线路&nbsp;:&nbsp;</label>
										<input type="text" id="communication"  name="gateway.communication"/>
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
											<label for="models">终端机型&nbsp;:&nbsp;</label>
											<input type="text" id="terminal"   name="gateway.terminal"  />								
											
									</p>
									<p class="fl">
											<label for="hardware">硬件版本&nbsp;:&nbsp;</label>
											<input type="text" id="hardware"   name="gateway.hardware"  />								
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="software">软件版本&nbsp;:&nbsp;</label>
										<input type="text" id="software"   name="gateway.software"  />
										
									</p>
									<p class="fl">
										<label for="sim">SIM卡号信息&nbsp;:&nbsp;</label>
										<input type="text" id="sim"  name="gateway.sim"/>
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
										<label for="report ">上报周期&nbsp;:&nbsp;</label>
										<input type="text" id="report"   name="gateway.report"/>
									</p>
									<p class="fl">
										<label for="fixing">设备序列号&nbsp;:&nbsp;</label>
										<input type="text" id="serialNumber"   name="gateway.serialNumber"/>
									</p>
								</li>
								<li class="clearfix">
									<p class="fl">
											<label for="address">IP地址&nbsp;:&nbsp;</label>
											<input type="text" id="ip"   name="gateway.ip"/>
									</p class="fl">
									<p class="fl">
											<label for="address">PORT&nbsp;:&nbsp;</label>
											<input type="text" id="port"   name="gateway.port"/>
									</p class="fl">
								</li>
								
							</ul>
							
							<p class="clearfix sub">
								<label for="">&nbsp;</label>
								<input type="submit" class="sure" value="确定"  onclick="add();"></input>
								<input type="reset" class="reset" value="重置"  >
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
