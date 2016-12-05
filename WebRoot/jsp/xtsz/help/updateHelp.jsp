<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/comm.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/xtgl/user/add_user.css" />
	 <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>
	</head>

	<body  >
	<form id="form"  action="<%=path %>/helpAction.do?method=updateEntity"  method="post"  encType="multipart/form-data">
		<div class="con">
			<p class="user">系统帮助</p>
			<p class="back" onclick="history.go(-1); "> <img src="<%=path%>/img/back.png" />返回</p>
			<div class="table_con">
			        <p class="fill">
						<label for="name">标题&nbsp;:&nbsp;</label>
						<input type="hidden" id="id" name="help.id" value="${list.id}"/>
						<input type="text" id="title" name="help.title" value="${list.title}"/>
						<input type="hidden"  id="content" name="help.content"  >
					</p>
	    			<script id="editor" type="text/plain" style="width:1024px;height:300px;">${list.content}</script>
					<p class="or clearfix">
						<input type="button"  value="保存"  onclick="add();">
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>
		</div>
		<p></p>
		</form>
	</body>
	<script src="<%=path%>/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path %>/js/ssq.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=path%>/js/xtgl/add_user.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
 	
function add(){
			document.getElementById("content").value=UE.getEditor('editor').getContent();
		 	$('#form').submit();
     }
     </script>
</html>
