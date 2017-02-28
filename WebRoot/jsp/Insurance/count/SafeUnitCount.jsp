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

    <title>保单列表</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />

		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">保单列表</p>
			<div class="warp">
				<div class="select clearfix">

					<p class="fl" style="width: 300px;">
					<label for="start_end"  style="width: 120px;">保险开始日期&nbsp;:&nbsp;</label>
					<input type="hidden" id="elevatorId"  name="elevatorId"  value="${elevatorId}" />

					<input type="text"  class="Wdate"  name="startTime"  id="startTime"   value="<fmt:formatDate value="${startTime}"  pattern='yyyy-MM-dd'/>"    onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="事故发生时间"   readonly="readonly">
				</p>
					<p class="fl"  style="width: 300px;">
					<label for="start_end" style="width: 120px;">保险结束日期&nbsp;:&nbsp;</label>
					<input type="text"  class="Wdate"   name="endTime"  	 id="endTime"  value="<fmt:formatDate value="${endTime}"  pattern='yyyy-MM-dd'/>"    onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="理赔时间"   readonly="readonly">
				</p>
				<p class="fl">
						<label for="user">保险公司&nbsp;:&nbsp;</label>
						<input type="text" id="company" placeholder="请输入"  value="${company}" />
					</p>
					<p class="fl">
						<label for="user">受益人&nbsp;:&nbsp;</label>
						<input type="text" id="beneficiary" placeholder="请输入"  value="${beneficiary}" />
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"bxgl_bxgs_exp")) {%>
						<p class="fl add" onclick="exp();" style="width: 100px;">下载</p>
            <%}%>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th class="all">
									<i></i>
								</th>
								<th>序列</th>
								<th>保险单位名称</th>
								<th>共投保电梯数</th>
								<th>受理赔次数</th>
								<th>理赔率</th>
							</thead>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""><input type="hidden" value="" /></i>
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.name}</td>
									<td>${list.num}</td>
									<td>${list.claimNum}</td>
									<td>${list.claimRate}</td>
								</tr>
								</c:forEach>

							</tbody>
						</table>
						<div class="choose">
							<p class="num">当前显示<span><c:if test="${page.pageNum==0}">${(page.pageNum+1)*1 }</c:if><c:if test="${page.pageNum!=0}">${(page.pageNum)*5 }</c:if></span>到<span>${(page.pageNum+1)*5 }</span>条，共<span>${page.count }</span>条记录</p>
							<div class="page">
								<a href="javascript:void(0);"  title="首页" onclick="fenye('0')" style="background-color: #00AAEE;color: #fff;"><<</a>

								<c:if test="${page.pageNum==0}">
										<a href="javascript:void(0);"  title="上一页"   style="background-color: #333;color: #fff;"><</a>
								 </c:if>
							 	 <c:if test="${page.pageNum!=0}">
							 	 		<a href="javascript:void(0);"  title="上一页"  onclick="fenye('${page.pageNum-1	}')"  style="background-color: #00AAEE;color: #fff;"><</a>
                         		</c:if>

								<c:if test="${page.pageNum+1==page.countSize}">
                        				<a href="javascript:void(0);" title="下一页"  style="background-color: #333;color: #fff;">></a>
		                        </c:if>
		                        <c:if test="${page.pageNum+1!=page.countSize}">
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
		<script src="<%=path%>/js/bxgl/safe.js" type="text/javascript" charset="utf-8"></script>

	<script type="text/javascript">

	//下载
		  function exp(){
    		  window.location.href="<%=path%>/safeAction.do?method=exportSafeUnit";
         }
	</script>

</html>
