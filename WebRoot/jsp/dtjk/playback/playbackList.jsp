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
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />

	</head>
<script type="text/javascript">
		 function myrefresh(){
			window.location.reload();
		}
		setTimeout('myrefresh()',30000); //指定30秒刷新一次s
</script>
	<body>
		<div class="con" id="user">
			<p class="user">电梯回放</p>
			<div class="warp">
				<div class="select" style="height: 110px;">
				<div class="clearfix">
					<p class="fl">
						<label for="user">开始时间&nbsp;:&nbsp;</label>
						<input type="hidden"  id="elevatorId"  name="elevatorId"  value="${elevatorId}" />

						<input  class="Wdate"   id="begintime"  name="begintime"  value="<fmt:formatDate value="${begintime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="开始时间"   readonly="readonly">
					</p>

					<p class="fl">
						<label for="man">结束时间&nbsp;:&nbsp;</label>
						<input  class="Wdate"   id="endtime"  name="endtime"    value="<fmt:formatDate value="${endtime}"  pattern='yyyy-MM-dd'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  placeholder="结束时间"   readonly="readonly">
					</p>
					<p class="fl">
						<label for="man">电梯状态&nbsp;:&nbsp;</label>
						<select name="state" id="state">
							<option value="">请选择</option>
							<option <c:if test="${state=='正常'}">selected="selected" </c:if> value="正常">正常</option>
							<option <c:if test="${state=='维保'}">selected="selected" </c:if> value="维保">维保</option>
							<option <c:if test="${state=='故障'}">selected="selected" </c:if> value="故障">故障</option>
						</select>
					</p>
				</div>
				<div class="clearfix">
					<p class="fl">
						<label for="user">运行方向&nbsp;:&nbsp;</label>
						<select name="direction" id="direction">
							<option value="">请选择</option>
							<option <c:if test="${direction=='上行'}">selected="selected" </c:if> value="上行">上行</option>
							<option <c:if test="${direction=='下行'}">selected="selected" </c:if> value="下行">下行</option>
							<option <c:if test="${direction=='静止'}">selected="selected" </c:if> value="静止">静止</option>
						</select>
					</p>
					<p class="fl">
						<label for="code">是否有人&nbsp;:&nbsp;</label>
						<select name="people" id="people">
							<option value="">请选择</option>
							<option <c:if test="${people=='有人'}">selected="selected" </c:if> value="有人">有人</option>
							<option <c:if test="${people=='没人'}">selected="selected" </c:if> value="没人">没人</option>
						</select>
					</p>
					<p class="fl">
						<label for="man">电梯门状态&nbsp;:&nbsp;</label>
						<select name="door" id="door">
							<option value="">请选择</option>
							<option <c:if test="${door=='开门'}">selected="selected" </c:if> value="开门">开门</option>
							<option <c:if test="${door=='关门'}">selected="selected" </c:if> value="关门">关门</option>
						</select>
					</p>

					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="or clearfix">
						<%if(Authority.haveRigth(user.getId(),"dtjk_jlhf_exp")) {%>
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
								<th>接收时间</th>
								<th>日期</th>
								<th>时间</th>
								<th>电梯方向</th>
								<th>电梯楼层</th>
								<th>是否有人</th>
								<th>电梯门状态</th>
								<th>电梯状态</th>
							<th>操作</th>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td class="wei">
										<i class=""><input type="hidden" value="${list.id}" /></i>
									</td>
									<td>${s.index + 1 }</td>
									<td>${list.elevatorId }</td>
									<td><fmt:formatDate value="${list.foundTime}"  pattern='yyyy-MM-dd HH:mm:ss'/></td>
									<td>${list.gatewayDate }</td>
									<td>${list.gatewayTime }</td>
									<td>${list.direction }</td>
									<td>${list.floor}</td>
									<td>${list.people}</td>
									<td>${list.door}</td>
									<td>${list.state}</td>
									<td>
										<img src="<%=path%>/img/content.png"  title="详情"  alt="详情"  onclick="findById('${list.id}');"/>
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
	<script src="<%=path%>/js/dtjk/record.js" type="text/javascript" charset="utf-8"></script>
</html>
