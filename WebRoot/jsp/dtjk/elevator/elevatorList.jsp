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
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/dtjk_comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/dtjk/list.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
		<script src="<%=path%>/js/region.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">电梯列表</p>
			<div class="warp">
				<div class="select" style="height: 165px;">
				<div class="clearfix">
					<p class="fl" >
						<label for="logn">省&nbsp;:&nbsp;</label>
						<input type="hidden"   id="provinceid"  >
						<select   id="province"   >
						<option value="${province}"  selected="selected">${province}</option>
						</select>
				</p>
					<p class="fl"  >
					<label for="logn">市&nbsp;:&nbsp;</label>
						<select   id="city" >
						<option value="${city}"  selected="selected">${city}</option>
						</select>
				</p>
				<p class="fl">
						<label for="logn">区&nbsp;:&nbsp;</label>
						<select   id="area" >
							<option value="${area}"  selected="selected">${area}</option>
						</select>
					</p>
					<p class="fl">
						<label for="user">注册号&nbsp;:&nbsp;</label>
						<input type="text" id="registerid"  value="${registerid}"  placeholder="请输入" />
					</p>

					</div>
				<div class="clearfix">
					<p class="fl">
						<label for="code">识别码&nbsp;:&nbsp;</label>
						<input type="text" id="distinguishid"  value="${distinguishid}" />
					</p>
					<p class="fl">
						<label for="man">使用单位&nbsp;:&nbsp;</label>
						<input type="text" id="useUnitName"  value="${useUnitName}" placeholder="请输入" />
					</p>
					<p class="fl">
						<label for="man">维保单位&nbsp;:&nbsp;</label>
						<input type="text" id="maintenanceUnitName"  value="${maintenanceUnitName}" placeholder="请输入" />
					</p>
					<p class="fl">
						<label for="man">物业单位&nbsp;:&nbsp;</label>
						<input type="text" id="propertyUnitName"  value="${propertyUnitName}" placeholder="请输入" />
					</p>
				</div>
				<div class="clearfix">
					<p class="fl">
						<label for="brand">制造单位&nbsp;:&nbsp;</label>
						<input type="text" id="makeUnitName" value="${makeUnitName}"  placeholder="请输入" />
					</p>
					<p class="fl">
						<label for="num">安装地点&nbsp;:&nbsp;</label>
						<input type="text" id="installPlace"  value="${installPlace}" />
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">

            <%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_add")) {%>
						<p class="fl add"    onclick="add();"><img src="<%=path%>/img/add.png" />新增</p>
						<%} %>
						<%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_del")) {%>
						<p class="fl del">批量删除</p>
						<%} %>
            <%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_exp")) {%>
						<p class="fl add" onclick="exp();" style="width: 100px;">下载</p>
            <%} %>
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
							<th>物业单位</th>
							<th>电梯品牌</th>
							<th>电梯类型</th>
							<th>电梯层数</th>
							<th>年检状态</th>
							<th>电梯状态</th>
			<!-- 				<th>白名单</th>
							<th>上报周期</th>
							<th>剩余流量</th>
							<th>服务费记录数</th> -->
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
									<td>${list.propertyUnitName }</td>
									<td>${list.brand}</td>
									<td>${list.type}</td>
									<td>${list.numbers}</td>
									<td>${list.yearlyState}</td>
									<td>${list.state}</td>
					<!-- 				<td><a href="<%=path %>/phoneAction.do?method=query&elevatorId=${list.id}"   style="color: blue; ">${list.num}</a></td>
									<td  style="color: blue; " <%if(Authority.haveRigth(user.getId(),"dtjk_update")) {%> onclick="findById('${list.id}','3');"   <%} %>>${list.period}</td>
									<td><a href="javascript:void(0);"  <%if(Authority.haveRigth(user.getId(),"dtjk_update")) {%> onclick="findById('${list.id}','4');"  <%} %>style="color: blue; ">${list.flowSurplus}</a></td>
									<td><a href="<%=path %>/serviceAction.do?method=query&elevatorId=${list.id}"   style="color: blue; ">${list.numService}</a></td>
						 -->			<td style="width: 150px;">
						 				<%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_update")) {%>
						 				<img src="<%=path%>/img/wanggugan.png"  title="网关"  alt="网关"  onclick="findById1('${list.registerid}');"/>
										<%} %>
										<img src="<%=path%>/img/content.png"  title="详情"  alt="详情"  onclick="findById('${list.id}','2');"/>
										<%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_update")) {%>
										<img src="<%=path%>/img/compile.png"  title="修改"  alt="修改"  onclick="findById('${list.id}','1');"/>
										<%} %>
										<%if(Authority.haveRigth(user.getId(),"dtjk_dtlb_del")) {%>
										<img src="<%=path%>/img/del.png" title="删除"  alt="删除"  class="del_one" onclick="del('${list.id}');"/>
										<%} %>
									</td>
								</tr>
								</c:forEach>

							</tbody>
						</table>
						<div class="choose">
							<p class="num">当前显示<span><c:if test="${page.pageNum==0}">${(page.pageNum+1)*1 }</c:if><c:if test="${page.pageNum!=0}">${(page.pageNum)*(page.pageSize) }</c:if></span>到<span>${(page.pageNum+1)* (page.pageSize)}</span>条，共<span>${page.count }</span>条记录</p>
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
	<script src="<%=path%>/js/dtjk/elevator.js" type="text/javascript" charset="utf-8"></script>
</html>
