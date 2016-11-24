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
    
    <title>故障统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/count_fault.css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />

	</head>

	<body>
		<div class="con" id="user">
			<p class="user">故障统计</p>
			<div class="warp">
				<div class="select clearfix">
				<p class="fl">
						<label for="unit">区域&nbsp;:&nbsp;</label>
						<select name="type" id="type">
							<option value="">请选择</option>
							
						</select>				
					</p>
					<p class="fl">
						<label for="user">开始时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="start"  name="start"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
					</p>
					
					<p class="fl">
						<label for="man">结束时间&nbsp;:&nbsp;</label>
					<input  class="Wdate"   id="time_end"  name="time_end"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="结束时间"   readonly="readonly">
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
						
					</div>
				<div class="table_con">
						<div class="fl" style="width: 33%;">
							<table border="" cellspacing="" cellpadding="">
								<caption>时间<span>&nbsp;:&nbsp;1016-01-0-1至2016-03-31</span></caption>
								<thead>
									<th>故障类型</th>
									<th>故障次数</th>
									<th>电梯数</th>
									<th>故障发生率</th>
								</thead>
								<tbody>
									<tr>
										<td>故障类型</td>
										<td>故障次数</td>
										<td>电梯数</td>
										<td>故障发生率</td>
									</tr>
									<tr>
										<td>故障类型</td>
										<td>故障次数</td>
										<td>电梯数</td>
										<td>故障发生率</td>
									</tr>
									<tr>
										<td>故障类型</td>
										<td>故障次数</td>
										<td>电梯数</td>
										<td>故障发生率</td>
									</tr>
									<tr>
										<td>故障类型</td>
										<td>故障次数</td>
										<td>电梯数</td>
										<td>故障发生率</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="fr" id="fr" style="width: 33%;height:400px;float: left;">

						</div>
						<div id="mian" class="mian" style="width: 33%;height:400px; float: left;">

						</div>
					</div>
				</div>
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
				'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载
			],
			function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('mian'));
				var myChart2 = ec.init(document.getElementById('fr'));
				//设置数据
				option = {
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						x: 'left',
						data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
					},
					series: [{
						name: '救援到达时间占比分布',
						type: 'pie',
						radius: '55%',
						center: ['50%', '60%'],
						data: [{
							value: 335,
							name: '直接访问'
						}, {
							value: 310,
							name: '邮件营销'
						}, {
							value: 234,
							name: '联盟广告'
						}, {
							value: 135,
							name: '视频广告'
						}, {
							value: 1548,
							name: '搜索引擎'
						}]
					}]
				};
//设置数据
				option2 = {
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						x: 'left',
						data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
					},
					series: [{
						name: '救援成功时间占比分布',
						type: 'pie',
						radius: '55%',
						center: ['50%', '60%'],
						data: [{
							value: 335,
							name: '直接访问'
						}, {
							value: 310,
							name: '邮件营销'
						}, {
							value: 234,
							name: '联盟广告'
						}, {
							value: 135,
							name: '视频广告'
						}, {
							value: 1548,
							name: '搜索引擎'
						}]
					}]
				};
				// 为echarts对象加载数据 
				myChart.setOption(option);
				myChart2.setOption(option2);
			}
		);
	
	</script>

</html>
