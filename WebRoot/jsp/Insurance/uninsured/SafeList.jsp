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
            <%if(Authority.haveRigth(user.getId(),"bxgl_bdjl_add")) {%>
						<p class="fl add"    onclick="add('${elevatorId}');"><img src="<%=path%>/img/add.png" />新增</p>
            <%}%>
            <%if(Authority.haveRigth(user.getId(),"bxgl_bdjl_del")) {%>
						<p class="fl del">批量删除</p>
            <%}%>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th class="all">
									<i></i>
								</th>
								<th>序列</th>
								<th>保单号</th>
								<th>保险开始日期</th>
								<th>保险结束日期</th>
								<th>保险公司</th>
								<th>保险金额</th>
								<th>受益人</th>
								<th>是否理赔</th>
								<th>理赔记录数</th>
								<th>操作</th>
							</thead>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""><input type="hidden" value="${list.id}" /></i>
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.number}</td>
									<td><fmt:formatDate value='${list.startTime}' pattern='yyyy-MM-dd'/></td>
									<td><fmt:formatDate value='${list.endTime}' pattern='yyyy-MM-dd'/></td>
									<td>${list.safeUnitName}</td>
									<td>${list.money}</td>
									<td>${list.beneficiary}</td>
									<td>${list.state}</td>
									<td><a href="<%=path %>/claimAction.do?method=query&safeId=${list.id}"   style="color: blue; ">${list.num}</a></td>

									<td>
										<img src="<%=path%>/img/content.png"  title="详情"  alt="详情"   onclick="findById('${list.id}','2');"/>
                    <%if(Authority.haveRigth(user.getId(),"bxgl_bdjl_update")) {%>
                    <img src="<%=path%>/img/compile.png"  title="修改"  alt="修改"   onclick="findById('${list.id}','1');"/>
                    <%}%>
                    <%if(Authority.haveRigth(user.getId(),"bxgl_bdjl_del")) {%>
                    <img src="<%=path%>/img/del.png" title="删除"  alt="删除"   class="del_one" onclick="del('${list.id}');"/>
                    <%}%>
									</td>
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


	</script>

</html>
