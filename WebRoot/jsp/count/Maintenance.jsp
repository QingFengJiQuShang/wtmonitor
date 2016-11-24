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
		<div class="con" >
			<p class="user">维保单位统计</p>			
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
					<div class="table_con clearfix">
						<div style="width: 70%;">
							<table border="" cellspacing="" cellpadding="">
								<caption>时间<span>&nbsp;:&nbsp;1016-01-0-1至2016-03-31</span></caption>
								<thead>
									<th>维保单位</th>
									<th>型号</th>
									<th>电梯总数量</th>
									<th>故障数量</th>
									<th>无故障运行时间</th>
									<th>故障发生次数</th>
									<th>故障发生率</th>
								</thead>
								<tbody>
									<tr>
										<td>维保单位</td>
										<td>型号</td>
										<td>电梯总数量</td>
										<td>故障数量</td>
										<td>无故障运行时间</td>
										<td>故障发生次数</td>
										<td>故障发生率</td>
									</tr>
									<tr>
										<td>维保单位</td>
										<td>型号</td>
										<td>电梯总数量</td>
										<td>故障数量</td>
										<td>无故障运行时间</td>
										<td>故障发生次数</td>
										<td>故障发生率</td>
									</tr>
									<tr>
										<td>维保单位</td>
										<td>型号</td>
										<td>电梯总数量</td>
										<td>故障数量</td>
										<td>无故障运行时间</td>
										<td>故障发生次数</td>
										<td>故障发生率</td>
									</tr>
									<tr>
										<td>维保单位</td>
										<td>型号</td>
										<td>电梯总数量</td>
										<td>故障数量</td>
										<td>无故障运行时间</td>
										<td>故障发生次数</td>
										<td>故障发生率</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="main" style="height:400px">

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
				'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
			],
			function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('main'));
				//设置数据
				var option = {
					title: {
						text: '各维保单位平均无故障运行时间'
							//						subtext: '纯属虚构'
					},
					tooltip: {
						trigger: 'axis'
					},
					//设置坐标轴
					xAxis: [{
						type: 'category',
						data: ["单位1", "单位2", "单位3", "单位4", "单位5", "单位6", "单位7", "单位8", "单位9", "单位10"]
					}],
					yAxis: [{
						type: 'value'
					}],
					//设置数据
					series: [{
						"name": "数量",
						"type": "bar",
						"data": [5,23, 20,12, 40,10, 10,20, 24,10, 20,10, 24,10, 32,20, 60],
					}]
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			}
		);

	
	</script>

</html>
