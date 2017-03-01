<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="width: 300px;">
  <head>
    <base href="<%=basePath%>">
    
    <title>使用单位</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
			<link rel="stylesheet" type="text/css" href="<%=path%>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/upload.css" />
		
		<!-- 引用控制层插件样式 -->
		<link rel="stylesheet" href="<%=path %>/control/css/zyUpload.css" type="text/css">
		<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.js"></script></head>
		<!-- 引用核心层插件 -->
		<script type="text/javascript" src="<%=path %>/core/zyFile.js"></script>
		<!-- 引用控制层插件 -->
		<script type="text/javascript" src="<%=path %>/control/js/zyUpload.js"></script>
		<!-- 引用初始化JS -->
		<script type="text/javascript" src="<%=path %>/js/demo.js"></script>
	<body style="width: 550px;padding-top: 50px;padding-left: 25px;">
	<form id="form" action="<%=path %>/rescueUnitAction.do?method=exportIn" method="post"  encType="multipart/form-data">
		<!-- 
			<div id="demo" class="demo"></div>    -->
			 		<div class="table">
				<p class="add">文件上传</p>
				
		<div class="table_con">
					
					<p class="fill">
						<label for="name">选择上传文件&nbsp;:&nbsp;</label>
						<input id="file" type="file"  name="theFile"  style="opacity:0;position:absolute;" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" onchange="document.getElementById('videoUrl_1').value=this.value"/>
						<input id="videoUrl_1" type="text" value="选择文件" >
					
					</p>
				
					<p class="or clearfix">
						<input type="button"  value="保存"  onclick="add();">
						<input type="button"  value="取消"   onclick="history.go(-1); " style="float: right;">
					</p>
				</div>

			</div>
		</form>
	</body>

<script type="text/javascript">
     function add(){
		 	$('#form').submit();
				
     }
     
     </script>
</html>
