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

    <title>类型统计</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/dtjk_comm.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<script src="<%=path%>/js/region.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">类型统计</p>
			<div class="warp">
				<div class="select " >
					<div class="clearfix">
					<p class="fl" >
						<label for="logn">省&nbsp;:&nbsp;</label>
						<input type="hidden"   id="provinceid"  >
						<select   id="province"   >
						<option value="${province}"  selected="selected">${province}</option>
						</select>
				</p>
					<p class="fl"  >
					<label for="logn">市&nbsp;:&nbsp;</label>
						<select   id="city" >
						<option value="${city}"  selected="selected">${city}</option>
						</select>
				</p>
				<p class="fl">
						<label for="logn">区&nbsp;:&nbsp;</label>
						<select   id="area" >
							<option value="${area}"  selected="selected">${area}</option>
						</select>
					</p>
					</div>
				<div class="clearfix">
					<p class="fl">
						<label for="user">开始时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="begintime"  name="begintime"  value="<fmt:formatDate value="${begintime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
					</p>

					<p class="fl">
						<label for="man">结束时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="endtime"  name="endtime"    value="<fmt:formatDate value="${endtime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="结束时间"   readonly="readonly">
					</p>
					<p class="fl">
							<label for="man">单位类型&nbsp;:&nbsp;</label>
							<select id="flag" name="flag">
									<option <c:if test="${flag==''}">selected="selected" </c:if>  value="">请选择</option>
									<option	<c:if test="${flag=='1'}">selected="selected" </c:if>  value="1">使用单位</option>
									<option	<c:if test="${flag=='2'}">selected="selected" </c:if>  value="2">物业单位</option>
									<option	<c:if test="${flag=='3'}">selected="selected" </c:if>  value="3">维保单位</option>
									<option	<c:if test="${flag=='4'}">selected="selected" </c:if>  value="4">制造单位</option>
									<option	<c:if test="${flag=='5'}">selected="selected" </c:if>  value="5">救援单位</option>
							<!-- 		<option value="6">保险单位</option> -->
							</select>
					</p>
					<p class="fl">
							<label for="man">单位名称&nbsp;:&nbsp;</label>
							<input type="hidden"  id="unitId"    value="${unitId}" />
							<input type="text"  id="unitId1"   value="${unitId1}"  readonly="readonly" onclick="selectTypeUnitId('unitId','unitId1','flag');"/>
					</p>
					<button class="fl"  onclick="query();">查询</button>
					</div>
				</div>
				<div class="table">
					<div class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"tjfx_gzlxtj_exp")) {%>

					<p class="fl add" onclick="exp();" style="width: 100px;">下载</p>
          <%}%>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th style="width: 200px;">单位名称</th>
								<th>故障类型名称</th>
								<th>此故障数量</th>
								<th>故障发生率</th>
							</thead>
							<c:forEach items="${counts}" var="list" varStatus="s">
							<tr>
									<td>${list.name }</td>
									<td>${list.faultType }</td>
									<td>${list.faultNum }</td>
									<td>${list.incidence }</td>
								</tr>
							</c:forEach>


							</tbody>
						</table>

					</div>
				</div>
			</div>
			<div id="main" style="height:300px"></div>
		</div>
		
	</div>
	
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/echarts/build/dist/echarts.js" type="text/javascript" charset="utf-8"></script>

	<script type="text/javascript">
	require.config({
			paths: {
				echarts: '<%=path%>/js/echarts/build/dist'
			}
		});
	// 使用
		require(
			[
				'echarts',
				'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
			],
			function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('main'));
				//设置数据
				var option = {
					title: {
						text: '故障类型统计'
							//						subtext: '纯属虚构'
					},
					tooltip: {
						trigger: 'axis'
					},
					//设置坐标轴
					xAxis: [{
						type: 'category',
						data: ${title}
					}],
					yAxis: [{
						type: 'value'
					}],
					//设置数据
					series: [{
						"name": "数量",
						"type": "bar",
						"data": ${num},
					}]
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			}
		);
	
	function gotoUrl (){
			 var begintime= document.getElementById("begintime").value;
			 var endtime= document.getElementById("endtime").value;
			 var province= document.getElementById("province").value;
			 var city= document.getElementById("city").value;
			 var area= document.getElementById("area").value;
			 var flag= document.getElementById("flag").value;
			 var unitId= document.getElementById("unitId").value;
			 var unitId1= document.getElementById("unitId1").value;

			  var url="";
			  if(begintime!=""){
				  url=url+"&begintime="+begintime;
			  }
			  if(endtime!=""){
				  url=url+"&endtime="+endtime;
			  }
			  if(province!=""){
				  url=url+"&province="+province;
			  }
			  if(city!=""){
				  url=url+"&city="+city;
			  }
			  if(area!=""){
				  url=url+"&area="+area;
			  }
			  if(flag!=""){
				  url=url+"&flag="+flag;
			  }
			  if(unitId!=""){
				  url=url+"&unitId="+unitId;
			  }
			  if(unitId1!=""){
				  url=url+"&unitId1="+unitId1;
			  }
			  return url;
		  }
	 //模糊查询
		function query(){
    		  window.location.href="<%=path%>/countAction.do?method=faultCount"+gotoUrl();
		  }
	  //下载
		  function exp(){
    		  window.location.href="<%=path%>/countAction.do?method=exportFaultCount"+gotoUrl ();
         }
	</script>

</html>
