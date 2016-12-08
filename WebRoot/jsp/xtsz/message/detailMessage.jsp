<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>短信警告</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
	<style type="text/css">
			.user,
			.order {
				height: 58px;
				line-height: 58px;
				background-color: #037eb6;
				text-align: center;
				font-size: 20px;
				color: #FFF;
			}
			
			.calendar {
				top: 130px!important;
			}
			
			.wrap {
				margin-top: 20px;
			}
			
			.wrap .textarea {
				height: 120px;
			}
			
			textarea {
				border: 1px solid #d2d2d2;
				border: 1px solid #d2d2d2;
				width: 235px;
				height: 82px;
				resize: none;
			}
		</style>
	</head>

	<body>
		<div class="con">
			<p class="user">短信警告</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">查询短信警告</p>
				<div class="table_con">
				
				<p class="fill">
					<label for="phone">客户手机号码&nbsp;:&nbsp;</label>
					${list.phone}
				</p>
				<p class="fill">
					<label for="state">发送状态&nbsp;:&nbsp;</label>
					${list.state}
				</p>
				<c:if test="${list.state=='已发送'}">
					<p class="fill">
						<label for="state">发送时间&nbsp;:&nbsp;</label>
						<fmt:formatDate value="${list.time }"  pattern='yyyy-MM-dd HH:mm:ss'/>
					</p>
				</c:if>
				<p class="fill textarea">
					<label for="con">发送内容&nbsp;:&nbsp;</label>${list.content}
				</p>
				
					
					<div class="keep clearfix">
						<c:if test="${list.state=='未发送'}">
						<button class="fl"    onclick="findById('${list.id}','1');">修改</button>
						</c:if>
						<button class="fr"   onclick="history.go(-1); ">取消</button>
					</div>
				</div>

			</div>
		</div>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtsz/message.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
     
     
     </script>
</html>
