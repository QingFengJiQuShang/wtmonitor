<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>电梯列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery_dialog.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery_dialog.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/lq.datetimepick.css" />
		<script type="text/javascript" src="<%=path %>/js/Share.js"></script>
		
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">维保记录</p>
			<div class="warp">
				<div class="select">
				<div class="clearfix">
					<p class="fl">
						<label for="user">使用单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="elevatorId"  name="elevatorId"  value="${elevatorId}" />
						<input type="hidden"  id="useUnitId"  value="${useUnitId}" name="elevator.useUnitId.id"   />
						<input type="text"  id="useUnitId1"  value="${useUnitId1}"  placeholder="请选择" readonly="readonly"  onclick="selectUseUnitId('useUnitId','useUnitId1');"/>
				
					</p>
					<p class="fl">
						<label for="code">维保日期&nbsp;:&nbsp;</label>
						<input type="text" id="time"  value="<fmt:formatDate value='${time}' pattern='yyyy-MM-dd'/>" />
					</p>
					<p class="fl">
						<label for="man">维保单位&nbsp;:&nbsp;</label>
						<input type="hidden"  id="maintenanceUnitId" value="${maintenanceUnitId}"  name="elevator.maintenanceUnitId.id"  />
						<input type="text"  id="maintenanceUnitId1"  value="${maintenanceUnitId1}"  placeholder="请输入" readonly="readonly" onclick="selectMaintenanceUnit('maintenanceUnitId','maintenanceUnitId1');"/>
					
					</p>
					
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="table">
					<div class="or clearfix">
						<%if(Authority.haveRigth(user.getId(),"dtjk_add")) {%>
						<p class="fl add"    onclick="add('${elevatorId}');"><img src="<%=path%>/img/add.png" />新增</p>
						<%} %>
						<%if(Authority.haveRigth(user.getId(),"dtjk_del")) {%>	
						<p class="fl del">批量删除</p>
						<%} %>
						<p class="fl add" onclick="exp();" style="width: 100px;">下载</p>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th class="all">
									<i></i>
								</th>
							<th>序列</th>
							<th>电梯注册号</th>
							<th>识别码</th>
							<th>使用单位</th>
							<th>安装地点</th>
							<th>维保日期</th>
							<th>维保人</th>
							<th>维保内容</th>
							<th>操作</th>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""><input type="hidden" value="${list.id}" /></i>
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.registerid }</td>
									<td>${list.distinguishid }</td>
									<td>${list.useUnitName }</td>
									<td>${list.place }</td>
									<td><fmt:formatDate value='${list.time}' pattern='yyyy-MM-dd'/></td>
									<td>${list.userName}</td>
									<td>${list.content}</td>
									<td>
										<img src="<%=path%>/img/content.png"  title="详情"  alt="详情"   onclick="findById('${list.id}','2');"/>
										<%if(Authority.haveRigth(user.getId(),"dtjk_update")) {%>
										<img src="<%=path%>/img/compile.png" title="修改"  alt="修改"  onclick="findById('${list.id}','1');"/>
										<%} %>
										<%if(Authority.haveRigth(user.getId(),"dtjk_del")) {%>
										<img src="<%=path%>/img/del.png"  title="删除"  alt="删除"   class="del_one" onclick="del('${list.id}');"/>
										<%} %>
									</td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
						<div class="choose">
							<p class="num">当前显示<span><c:if test="${page.pageNum==0}">${(page.pageNum+1)*1 }</c:if><c:if test="${page.pageNum!=0}">${(page.pageNum)*5 }</c:if></span>到<span>${(page.pageNum+1)*5 }</span>条，共<span>${page.count }</span>条记录</p>
							<div class="page">
								<a href="javascript:void(0);"  title="首页" onclick="fenye('0')" style="background-color: #00AAEE;color: #fff;"><<</a>								
								
								<c:if test="${page.pageNum==0||page.countSize==0}">
										<a href="javascript:void(0);"  title="上一页"   style="background-color: #333;color: #fff;"><</a>
								 </c:if>
							 	 <c:if test="${page.pageNum!=0&&page.countSize!=0}">
							 	 		<a href="javascript:void(0);"  title="上一页"  onclick="fenye('${page.pageNum-1	}')"  style="background-color: #00AAEE;color: #fff;"><</a>
                         		</c:if>
								
								<c:if test="${page.pageNum+1==page.countSize||page.countSize==0}">
                        				<a href="javascript:void(0);" title="下一页"  style="background-color: #333;color: #fff;">></a>
		                        </c:if>
		                        <c:if test="${page.pageNum+1!=page.countSize&&page.countSize!=0}">
		                        		<a href="javascript:void(0);"  title="下一页"  onclick="fenye('${page.pageNum+1}')"  style="background-color: #00AAEE;color: #fff;">></a>
		                    	</c:if>
								<a href="javascript:void(0);" class="mo" title="尾页"  onclick="fenye('${page.countSize-1}')"  style="background-color: #00AAEE;color: #fff;">>></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/comm.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/dtjk/maintenanceRecords.js" type="text/javascript" charset="utf-8"></script>
	 <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
  
      $(function() {
		$("#time").on("click", function(e) {
			e.stopPropagation();
			$(this).lqdatetimepicker({
				css: 'datetime-day',
				dateType: 'D',
				selectback: function() {}
			});
		});
		
	});
     </script>
</html>
