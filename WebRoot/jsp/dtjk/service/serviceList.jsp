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
    
    <title>电梯服务费</title>
    
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
			<p class="user">电梯服务费</p>
			<div class="warp">
				<div class="select clearfix">
					<p class="fl">
						<label for="user">单位名称&nbsp;:&nbsp;</label>
						<input type="hidden"  id="elevatorId"  name="elevatorId"  value="${elevatorId}" />
						<input type="text" id="name" placeholder="请输入"  value="${name}" />
					</p>
					<p class="fl">
						<label for="unit">单位类型&nbsp;:&nbsp;</label>
						<select name="type" id="type">
							<option value="">请选择</option>
							<option <c:if test="${type=='物业'}">selected="selected" </c:if> value="物业">物业</option>
							<option <c:if test="${type=='政府'}">selected="selected" </c:if> value="政府">政府</option>
							<option <c:if test="${type=='事业单位'}">selected="selected" </c:if> value="事业单位">事业单位</option>
						
						</select>				
					</p>
					<p class="fl">
						<label for="man">联系人&nbsp;:&nbsp;</label>
						<input type="text" id="liaisons" placeholder="请输入"  value="${liaisons}"/>
					</p>
					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
						<p class="fl add"   onclick="add('${elevatorId}');"><img src="<%=path%>/img/add.png" />新增</p>
						<p class="fl del">批量删除</p>&nbsp;&nbsp;
						<p class="fl add" onclick="exp();">&nbsp;&nbsp;下载&nbsp;&nbsp;</p>
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
								<th>电梯使用单位</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>服务金额</th>
								<th>服务类型</th>
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
									<td><fmt:formatDate value='${list.startTime}' pattern='yyyy-MM-dd'/></td>
									<td><fmt:formatDate value='${list.endTime}' pattern='yyyy-MM-dd'/></td>
									<td>${list.inspectionUnit}</td>
									<td>${list.result}</td>
									<td>
										<img src="<%=path%>/img/content.png"  title="详情"  alt="详情"   onclick="findById('${list.id}','2');"/>
										<img src="<%=path%>/img/compile.png"  title="修改"  alt="修改"   onclick="findById('${list.id}','1');"/>
										<img src="<%=path%>/img/del.png" title="删除"  alt="删除"   class="del_one" onclick="del('${list.id}');"/>
									
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
	<script src="<%=path%>/js/dtjk/service.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
		//添加
		function add(){
			  window.location="<%=path%>/jsp/dtjk/service/addService.jsp";
			  
		  }
	</script>


</html>
