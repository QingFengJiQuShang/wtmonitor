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

    <title>区域统计</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/dtjk_comm.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<script src="<%=path%>/js/region.js" type="text/javascript" charset="utf-8"></script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">区域统计</p>
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
					<button class="fl"  onclick="query();">查询</button>
					</div>
				</div>
				<div class="table">
					<div class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"tjfx_gzqytj_exp")) {%>
					<p class="fl add" onclick="exp();" style="width: 100px;">下载</p>
          <%}%>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th style="width: 80px;">省</th>
								<th>市</th>
								<th>区</th>
								<th>故障总数</th>
								<th>人工接警数量</th>
								<th>自动接警数量</th>
								<th>困人故障数量</th>
								<th>困人率</th>
								<th>其它故障数量</th>
							</thead>
								<tr>
									<td>${count.province }</td>
									<td>${count.city }</td>
									<td>${count.area }</td>
									<td>${count.faultNum }</td>
									<td>${count.alarmNum }</td>
									<td>${count.automaticNum }</td>
									<td>${count.peopleTrappedNum }</td>
									<td>${count.peopleTrappedRate }</td>
									<td>${count.otherNum }</td>
									<td>

									</td>
								</tr>

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
		<script src="<%=path%>/js/bxgl/count.js" type="text/javascript" charset="utf-8"></script>
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
						text: '故障区域统计'
							//						subtext: '纯属虚构'
					},
					tooltip: {
						trigger: 'axis'
					},
					//设置坐标轴
					xAxis: [{
						type: 'category',
						data: ["省：${count.province},市：${count.city},区：${count.area}"]
					}],
					yAxis: [{
						type: 'value'
					}],
					//设置数据
					series: [{
						"name": "故障总数",
						"type": "bar",
						"data": [${count.faultNum}],
					},
					{
						"name": "人工接警数量",
						"type": "bar",
						"data": [${count.alarmNum}],
					},
					{
						"name": "自动接警数量",
						"type": "bar",
						"data": [${count.automaticNum}],
					},
					{
						"name": "困人故障数量",
						"type": "bar",
						"data": [${count.peopleTrappedNum}],
					},
					{
						"name": "困人率",
						"type": "bar",
						"data": [${count.peopleTrappedRate}],
					},
					{
						"name": "其它故障数量",
						"type": "bar",
						"data": [${count.otherNum}],
					}
					
					]
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
			  return url;
		  }
	 //模糊查询
		function query(){
    		  window.location.href="<%=path%>/countAction.do?method=regionCount"+gotoUrl();
		  }
	  //下载
		  function exp(){
    		  window.location.href="<%=path%>/countAction.do?method=exportCount"+gotoUrl ();
         }
	</script>

</html>
