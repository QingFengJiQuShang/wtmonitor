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

    <title>当前故障</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/calendar.css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />

	</head>

	<body>
		<div class="con" id="user">
			<p class="user">当前故障</p>
			<div class="warp">
				<div class="select clearfix">
					<p class="fl">
						<label for="user">电梯注册号&nbsp;:&nbsp;</label>
						<input type="text" id="registerid" placeholder="请输入"  value="${registerid}" />
					</p>
					<p class="fl">
						<label for="unit">安装地址&nbsp;:&nbsp;</label>
						<input type="text" id="place"    value="${place}"  />
					</p>
					<p class="fl"  style="width: 450px;">
						<label for="man"  style="width: 100px;">故障发生时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="begintime"  name="begintime"   value="<fmt:formatDate value="${begintime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
						--
						<input  class="Wdate"   id="endtime"  name="endtime"   value="<fmt:formatDate value="${endtime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
					<!--  	<p class="fl add"    onclick="add();"><img src="<%=path%>/img/add.png" />新增</p>
						<p class="fl del">批量删除</p>&nbsp;&nbsp;-->
            <%if(Authority.haveRigth(user.getId(),"gzgl_dqgz_exp")) {%>
						<p class="fl add" onclick="exp();">&nbsp;&nbsp;下载&nbsp;&nbsp;</p>
            <%}%>
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
								<th>安装地址</th>

								<th>故障发生时间</th>
								<th>救援到达时间</th>
								<th>救援完成时间</th>
								<th>困人数量</th>
								<th>值班人</th>
								<th>故障状态</th>
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
									<td>${list.place }</td>

									<td><fmt:formatDate value="${list.happenTime }"  pattern='yyyy-MM-dd HH:mm:ss'/></td>
									<td><fmt:formatDate value="${list.arriveTime }"  pattern='yyyy-MM-dd HH:mm:ss'/></td>
									<td><fmt:formatDate value="${list.successTime }"  pattern='yyyy-MM-dd HH:mm:ss'/></td>
									<td>${list.numbers}</td>
									<td>${list.dutyName}</td>
									<td>${list.state }</td>
									<td>

										<img src="<%=path%>/img/content.png"  title="详情"  alt="详情"   onclick="findById('${list.id}','2');"/>
										<%if(Authority.haveRigth(user.getId(),"gzgl_dqgz_update")) {%>
										<img src="<%=path%>/img/compile.png"  title="修改"  alt="修改" onclick="findById('${list.id}','1');"/>
										<%} %>
						<!-- 		<img src="<%=path%>/img/del.png" alt="" class="del_one" onclick="del('${list.id}');"/> -->
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
	<script src="<%=path%>/js/gzcl/fault.js" type="text/javascript" charset="utf-8"></script>
	    <script src="<%=path %>/js/calendar.js" type="text/javascript" charset="utf-8"></script>

</html>
