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
    
    <title>使用单位</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/count_fault.css" />
		
		
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">品牌保险统计</p>
			<div class="warp">
				<div class="select clearfix">
				<p class="fl">
						<label for="unit">区域&nbsp;:&nbsp;</label>
						<select name="type" id="type">
							<option value="">请选择</option>
							
						</select>				
					</p>
					<p class="fl">
						<label for="user">安装地址&nbsp;:&nbsp;</label>
						<input type="text" id="name" placeholder="请输入"  value="${name}" />
					</p>
					
					<p class="fl">
						<label for="man">联系人&nbsp;:&nbsp;</label>
						<input type="text" id="liaisons" placeholder="请输入"  value="${liaisons}"/>
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
						
					</div>
				<div class="table_con">
						<div class="fl">
							<table border="" cellspacing="" cellpadding="">
								<caption>时间<span>&nbsp;:&nbsp;1016-01-0-1至2016-03-31</span></caption>
								<thead>
									<th>电梯品牌</th>
									<th>投保电梯</th>
									<th>投保率</th>
									<th>受理投保率</th>
								</thead>
								<tbody>
									<tr>
										<td>电梯品牌</td>
										<td>投保电梯</td>
										<td>投保率</td>
										<td>受理投保率</td>
									</tr>
									<tr>
										<td>电梯品牌</td>
										<td>投保电梯</td>
										<td>投保率</td>
										<td>受理投保率</td>
									</tr>
									<tr>
										<td>电梯品牌</td>
										<td>投保电梯</td>
										<td>投保率</td>
										<td>受理投保率</td>
									</tr>
									<tr>
										<td>电梯品牌</td>
										<td>投保电梯</td>
										<td>投保率</td>
										<td>受理投保率</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="fr" id="main" style="height:400px">

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
				var myChart = ec.init(document.getElementById('main'));
				//设置数据
				option = {
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						x: 'right',
						y: 'center',
						data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
					},
					series: [{
						name: '访问来源',
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
			}
		);
	
	</script>

</html>
