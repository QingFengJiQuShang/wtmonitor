<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtgl.action.Authority"%>
<%@page import="com.jrsoft.fri.xtgl.entity.XtglUsers"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String unitId=request.getParameter("unitId");
XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>维保单位</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user_comm.css" />
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">维保人</p>
			<div class="warp">
				<div class="select clearfix">
					<p class="fl">
						<label for="user">维保人姓名&nbsp;:&nbsp;</label>
						<input type="hidden"  id="unitId"  value="<%=unitId %>"/>
						<input type="text" id="name" placeholder="请输入"   value="${name}"/>
					</p>
					<p class="fl">
						<label for="unit">维保证编号&nbsp;:&nbsp;</label>
						<input type="text" id="numbers" placeholder="请输入"  value="${numbers}"/>
					</p>
					<p class="fl">
						<label for="">维保卡号&nbsp;:&nbsp;</label>
						<input type="text" id="cardNumber" placeholder="请输入" value="${cardNumber}"/>
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
						<%if(Authority.haveRigth(user.getId(),"yhgl_add")) {%>
						<p class="fl add"    onclick="add('<%=unitId %>');"><img src="<%=path%>/img/add.png" />新增</p>
						<%} %>
						<%if(Authority.haveRigth(user.getId(),"yhgl_del")) {%>
						<p class="fl del">批量删除</p>
						<%} %>
						<p class="fl add" onclick="exp();">&nbsp;&nbsp;下载&nbsp;&nbsp;</p>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th class="all">
									<i></i>
								</th>
								<th>序列</th>
								<th>维保人名称</th>
								<th>维保人电话</th>
								<th>维保证编号</th>
								<th>维保证有效期限</th>
								<th>维保卡号</th>
								<th>操作</th>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""><input type="hidden" value="${list.id}" /></i>
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.name }</td>
									<td>${list.phone }</td>
									<td>${list.numbers }</td>
									<td>${list.validity }</td>
									<td>${list.cardNumber}</td>
									<td>
										<img src="<%=path%>/img/content.png" title="详情"  alt="详情" onclick="findById('${list.id}','2');"/>
										<%if(Authority.haveRigth(user.getId(),"yhgl_update")) {%>
										<img src="<%=path%>/img/compile.png"  title="修改"  alt="修改" onclick="findById('${list.id}','1');"/>
										<%} %>
										<%if(Authority.haveRigth(user.getId(),"yhgl_del")) {%>
										<img src="<%=path%>/img/del.png" title="删除"  alt="删除"  class="del_one" onclick="del('${list.id}');"/>
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
	<script src="<%=path%>/js/xtgl/maintenanceUsers.js" type="text/javascript" charset="utf-8"></script>
</html>
