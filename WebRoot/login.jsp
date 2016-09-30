<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>智慧安全登监控系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/login.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>
    <script src="<%=path %>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script></head>
<script type="text/javascript">
		
		
function login(path){
	var loginName = $("input[name='loginName']").val();
	var password = $("input[name='password']").val();
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath }/userLogin",
		data:"username="+loginName+"&password="+password,
		
		success:function(jsonData){
			var data = jQuery.parseJSON(jsonData);
			if(data.result==1){		//用户登陆成功
				alert("登录成功！");
			}else{
				alert("用户名或密码不对!");
				$("input[name='password']").value="";
				$("input[name='password']").focus();
			}
		},
		 error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert("服务器出错或无响应！");
   },

	});
}

</script>
<body onkeypress="GoURLB(event)">
	<img class="background" src="img/login/344vv@2x.png" />
	<div class="login-dialog">
		<div class="login-form">
			<input type="text" name="loginName" placeholder="请输入帐号" /> <input
				type="password" name="password" placeholder="请输入密码" /> <img
				id="vcode" src="<%=path %>/vcode.jsp" onclick="vcode()" /> <input
				type="text" name="vcode" /> <input type="image"
				src="img/login/116-right-circle-arrow-icon@2x.png" name="submit" onclick="login('<%=basePath %>')" />
				</div>
				</div>
</body>
</html>
