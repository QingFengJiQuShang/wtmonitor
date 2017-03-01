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
						<input  class="Wdate"   id="begintime"  name="begintime"  value="<fmt:formatDate value="${begintime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
					</p>

					<p class="fl">
						<label for="man">结束时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="endtime"  name="endtime"    value="<fmt:formatDate value="${endtime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="结束时间"   readonly="readonly">
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
									<th style="width: 100px;">电梯品牌</th>
									<th style="width: 100px;">电梯总数量</th>
									<th style="width: 100px;">故障发生次数</th>
									<th style="width: 100px;">无故障运行时间</th>
									<th style="width: 100px;">故障发生率</th>
								</thead>
								<tbody>
									<c:forEach items="${counts}" var="counts" varStatus="s">
									<tr>
										<td>${counts.brand}</td>
										<td>${counts.num}</td>
										<td>${counts.faultNum}</td>
										<td>${counts.time}小时</td>
										<td>${counts.incidence}%</td>
									</tr>
									</c:forEach>
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
						text: '各品牌平均无故障运行时间'
							//						subtext: '纯属虚构'
					},
					tooltip: {
						trigger: 'axis',
						formatter: "电梯品牌：{b}<br/>{a} ：  {c} 小时 "
					},
					//设置坐标轴
					xAxis: [{
						"name": "电梯品牌",
						type: 'category',
						data: ${title}
					}],
					yAxis: [{
						type: 'value'
					}],
					//设置数据
					series: [{
						"name": "平均无故障运行时间",
						"type": "bar",
						"data": ${rows}
					}]
				};

				// 为echarts对象加载数据
				myChart.setOption(option);
			}
		);
	//模糊查询
		function query(){
			 var begintime= document.getElementById("begintime").value;
			 var endtime= document.getElementById("endtime").value;
    		  window.location.href="<%=path%>/countAction.do?method=useUnitCount&begintime="+begintime+"&endtime="+endtime;
		  }

	</script>

</html>
