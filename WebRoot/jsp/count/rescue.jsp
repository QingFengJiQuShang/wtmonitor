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
						<input  class="Wdate"   id="begintime"  name="begintime"  value="<fmt:formatDate value="${begintime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
					</p>
					
					<p class="fl">
						<label for="man">结束时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="endtime"  name="endtime"    value="<fmt:formatDate value="${endtime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="结束时间"   readonly="readonly">
					</p>
					<p class="fl">
						<label for="man">救援单位&nbsp;:&nbsp;</label>
						<input  id="unit"  name="unit"    value="${unit}"   >
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
						
					</div>
				<div class="table_con">
						<div class="fl" style="width: 33%;">
							<table border="" cellspacing="" cellpadding="">
								<caption></caption>
								<thead>
									<th style="width: 150px;">救援单位</th>
									<th>平均救援到达时间</th>
									<th>平均救援完成时间</th>
									<th>救援次数</th>
								</thead>
								<tbody>
									<c:forEach items="${counts}" var="counts" varStatus="s">
									<tr>
										<td>${counts.name}</td>
										<td>${counts.arriveTime}分钟</td>
										<td>${counts.successTime}分钟</td>
										<td>${counts.num}</td>
									</tr>
									</c:forEach>
									
								</tbody>
							</table>
						</div>
						<div  class="fr" id="fr"   style="width: 33%;height:400px;float: left;">
						</div>
						<div   id="mian" class="mian"  style="width: 33%;height:400px; float: left;">
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
						formatter: "{a} <br/>救援到达时间：{b} 分钟内<br/>次数: {c} "
					},
					legend: {
						orient: 'vertical',
						x: 'left',
						data: ${title}
					},
					series: [{
						name: '救援到达时间占比分布',
						type: 'pie',
						radius: '30%',
						center: ['50%', '50%'],
						data: ${rows}
					}]
				};
//设置数据
				option2 = {
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>救援完成时间：{b} 分钟内<br/>次数: {c} "
					},
					legend: {
						orient: 'vertical',
						x: 'left',
						data: ${title}
					},
					series: [{
						name: '救援成功时间占比分布',
						type: 'pie',
						radius: '30%',
						center: ['50%', '50%'],
						data: ${rows1}
					}]
				};
				// 为echarts对象加载数据 
				myChart.setOption(option2);
				myChart2.setOption(option);
			}
		);
	//模糊查询
		function query(){
			 var begintime= document.getElementById("begintime").value;
			 var endtime= document.getElementById("endtime").value;
			 var unit= document.getElementById("unit").value;
    		  window.location.href="<%=path%>/countAction.do?method=rescueCount&begintime="+begintime+"&endtime="+endtime+"&unit="+unit;
		  }
	</script>

</html>
