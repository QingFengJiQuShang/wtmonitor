<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<form id="form"   action="<%=path %>/messageAction.do?method=updateEntity"  method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">短信警告</p>
			<p class="back"  onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table">
				<p class="add">修改短信警告</p>
				<div class="table_con">
				
				<p class="fill">
					<label for="phone">客户手机号码&nbsp;:&nbsp;</label>
					<input type="hidden" name="message.id" id="id" value="${list.id}" />
					<input type="" name="message.phone" id="phone" value="${list.phone}" />
				</p>
				<p class="fill">
					<label for="state">发送状态&nbsp;:&nbsp;</label>
					<input  name="message.state" id="state" value="${list.state}"  readonly="readonly"/>
				</p>
				<p class="fill textarea">
					<label for="con">发送内容&nbsp;:&nbsp;</label>
					<textarea name="message.content" rows="3" cols="40">${list.content}</textarea>
				</p>
				
					
					<p class="or clearfix">
						<input type="button"  class="fl"  value="保存"   onclick="add();"/>
						<input type="button"  class="fl"  value="取消"   onclick="history.go(-1); " style="float: right;"/>
					</div>
				</div>

			</div>
		</div>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
     function add(){
		 	$('#form').submit();
     }
     
     </script>
</html>
