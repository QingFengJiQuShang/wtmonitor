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
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">未保电梯</p>
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
						<table border="" cellspacing="" cellpadding="">
							<thead>
								<th>序列</th>
								<th>电梯注册号</th>
								<th>识别码</th>
								<th>电梯使用单位</th>
								<th>电梯安装单位</th>
								<th>电梯品牌</th>
								<th>电梯层数</th>
								<th>电梯状态</th>
								<th>操作</th>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>电梯注册号</td>
									<td>识别码</td>
									<td>电梯使用单位</td>
									<td>电梯安装单位</td>
									<td>电梯品牌</td>
									<td>电梯层数</td>
									<td>电梯状态</td>
									<td><a href="<%=path %>/jsp/Insurance/uninsured/addUninsured.jsp"   style="color: blue; ">添加保险</a></td>

								</tr>
								<tr>
									<td>1</td>
									<td>电梯注册号</td>
									<td>识别码</td>
									<td>电梯使用单位</td>
									<td>电梯安装单位</td>
									<td>电梯品牌</td>
									<td>电梯层数</td>
									<td>电梯状态</td>
									<td><a href="<%=path %>/jsp/Insurance/uninsured/addUninsured.jsp"   style="color: blue; ">添加保险</a></td>
								</tr>
								<tr>
									<td>1</td>
									<td>电梯注册号</td>
									<td>识别码</td>
									<td>电梯使用单位</td>
									<td>电梯安装单位</td>
									<td>电梯品牌</td>
									<td>电梯层数</td>
									<td>电梯状态</td>
									<td><a href="<%=path %>/jsp/Insurance/uninsured/addUninsured.jsp"   style="color: blue; ">添加保险</a></td>
								</tr>
								<tr>
									<td>1</td>
									<td>电梯注册号</td>
									<td>识别码</td>
									<td>电梯使用单位</td>
									<td>电梯安装单位</td>
									<td>电梯品牌</td>
									<td>电梯层数</td>
									<td>电梯状态</td>
									<td><a href="<%=path %>/jsp/Insurance/uninsured/addUninsured.jsp"   style="color: blue; ">添加保险</a></td>
								</tr>
								<tr>
									<td>1</td>
									<td>电梯注册号</td>
									<td>识别码</td>
									<td>电梯使用单位</td>
									<td>电梯安装单位</td>
									<td>电梯品牌</td>
									<td>电梯层数</td>
									<td>电梯状态</td>
									<td><a href="<%=path %>/jsp/Insurance/uninsured/addUninsured.jsp"   style="color: blue; ">添加保险</a></td>
								</tr>
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
</html>
