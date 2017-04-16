<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jrsoft.fri.xtsz.action.Message"%>
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

    <title>短信提醒</title>

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
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
	</head>

	<body>
		<div class="con" id="user">
			<p class="user">短信提醒</p>
			<div class="warp">
				<div class="select">
				<div class="clearfix">
					<p class="fl">
							<label for="unit">手机号&nbsp;:&nbsp;</label>
							<input type="hidden" id="flag"    value="${flag}"  />
							<input type="hidden"  class="Wdate"   id="begintime"  name="begintime"   value="<fmt:formatDate value="${begintime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
							<input type="hidden"   class="Wdate"   id="endtime"  name="endtime"   value="<fmt:formatDate value="${endtime}"  pattern='yyyy-MM-dd HH:mm:ss'/>"   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
							<input type="text" id="phone"    value="${phone}"  />
						</p>


					<button class="fl"  onclick="query();">查询</button>
				</div>
				<div class="table">
					<div class="table">
					<div class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"xtsz_dxtx_add")) {%>
							<p class="fl del"  onclick="add();">新增</p>&nbsp;&nbsp;
            <%}%>
					</div>
				<div class="table_con">
						<table border="" cellspacing="" cellpadding="">
							<thead>
							<th  style="width: 30px;">序列</th>
							<th style="width: 150px;">用户手机号码</th>
							<th style="width: 100px;">发送状态</th>
							<th style="width: 100px;">创建时间</th>
							<th>内容</th>
							<th>操作</th>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list" varStatus="s">
								<tr>
									<td>${s.index + 1 }</td>
									<td>&nbsp;&nbsp;${list.phone}&nbsp;&nbsp;</td>
									<td>&nbsp;&nbsp;${list.state }&nbsp;&nbsp;</td>

									<td>&nbsp;&nbsp;<fmt:formatDate value="${list.foundTime }"  pattern='yyyy-MM-dd HH:mm:ss'/>&nbsp;&nbsp;</td>

									<td>
									<c:if test="${fn:length(list.content)>70 }">
				                         ${fn:substring(list.content, 0, 70)}...
				                   </c:if>
				                  <c:if test="${fn:length(list.content)<=70 }">
				                         ${list.content }
				                   </c:if> </td>
									<td>
										<img src="<%=path%>/img/content.png" alt=""  onclick="findById('${list.id}','0');"/>
                    <%if(Authority.haveRigth(user.getId(),"xtsz_dxtx_update")) {%>
										<img src="<%=path%>/img/compile.png"  title="修改"  alt="修改"   onclick="findById('${list.id}','1');"/>
                    <%}%>
                    <%if(Authority.haveRigth(user.getId(),"xtsz_dxtx_send")) {%>
                    <img style="width: 18px;" src="<%=path%>/img/send.png"   onclick="send('${list.id}');"/>
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
	<script src="<%=path%>/js/xtsz/message.js" type="text/javascript" charset="utf-8"></script>
	 <script src="<%=path %>/js/lq.datetimepick.js" type="text/javascript" charset="utf-8"></script>

</html>
