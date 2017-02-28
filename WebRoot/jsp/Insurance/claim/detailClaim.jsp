<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
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

    <title>理赔记录</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 鼠标悬浮图片放大效果 -->
		<link rel="stylesheet" type="text/css" href="<%=path%>/imageHover/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/imageHover/css/style.css" />
 		<script src="<%=path%>/imageHover/js/prefixfree.min.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/css/lanrenzhijia.css" />
		<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
			</head>

	<body style="background-color:#fff;">
		<div class="con">
			<p class="user">理赔记录</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">查看理赔记录</p>
				<div class="table_con">
				  <script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
   				 <p class="fill">
					<label for="unit">事故原因&nbsp;:&nbsp;</label>${list.cause}
				</p>
				<p class="fill">
					<label for="start_end">事故发生时间&nbsp;:&nbsp;</label>
					<fmt:formatDate value="${list.happenTime}"  pattern='yyyy-MM-dd'/>
				</p>
				<p class="fill">
					<label for="start_end">理赔时间&nbsp;:&nbsp;</label>
					<fmt:formatDate value="${list.claimTime}"  pattern='yyyy-MM-dd'/>

				</p>
				<p class="fill">
					<label for="unit">保险金额&nbsp;:&nbsp;</label>${list.money}
				</p>

				<p class="fill">
					<label for="">上传保单图片&nbsp;:&nbsp;</label>
					<div class="gallery cf">
						<c:forEach items="${str}" var="str" varStatus="s">
							<div>
						    	<img src="${str}"  style="width: 100px;height: 100px;"/>
						  	</div>
						</c:forEach>
						</div>
				</p>

					<p class="or clearfix">
            <%if(Authority.haveRigth(user.getId(),"bxgl_lpjl_update")) {%>
						<input type="button"  value="修改"    onclick="findById('${list.id}','1');" >
              <%}%>
            <input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/bxgl/claim.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

</html>
