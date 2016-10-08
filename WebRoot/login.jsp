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

   <meta http-equiv="X-UA-Compatible" content="IE=10" />
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/reset.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
		<script src="<%=path %>/js/jquery-ui.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script></head>
<script type="text/javascript">
//改变验证码图片 
  function reloadcode(){
	$("#code").attr("src","<%=path %>/PictureCheckCode?time="+new Date());
	
	}  

	
function doLogin(){
	//alert(login_onclick());
	if(login_onclick()==true){
		var username = $("input[name='username']").val();
		var passwd = $("input[name='passwd']").val();
		var code = $("input[name='code']").val();
		//alert(username+"  "+passwd+"  "+code);
		
		$.ajax({
			type:"POST",
			url:"<%=path%>/userLogin",
			data:"username="+username+"&passwd="+passwd+"&code="+code,
			dataType: "json",
			success:function(jsonData){
			
				alert(jsonData.result);
				if(jsonData.result==1){		//用户登陆成功
						 window.location="<%=path%>/index.jsp";
				}else if(jsonData.result==3){		//验证码不正确
					alert("验证码不正确!");
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
}

function login_onclick(){
	var username = $("input[name='username']").val();
	var passwd = $("input[name='passwd']").val();
	var code = $("input[name='code']").val();
	if (username==""){
		alert("对不起,用户名不能为空!");
		 return false;
	}else if (passwd==""){
		alert("对不起,密码不能为空!");
		return false;
	}else if (code==""){
		alert("对不起,验证码不能为空!");
		return false;
	}else{
		return true;
	}
	
	
}

</script>

	<body>
		<div class="warp">
			<div class="con">
				<div class="right">
					<form class="fl"   action="<%=path%>/userLogin" method="post">
						<p>
							<label for="">用户名&nbsp;:&nbsp;</label>
							<input type="text" name="username" id="" placeholder="" />
						</p>
						<p>
							<label for="">密<span>吗</span>码&nbsp;:&nbsp;</label>
							<input type="text" name="passwd" id="" placeholder="" />
						</p>
						<p>
							<label for="">验证码&nbsp;:&nbsp;</label>
							<input type="text" name="code"  />
							<img src="<%=path %>/PictureCheckCode"   id="code" onclick="reloadcode()" style="cursor: pointer;" alt="看不清楚,换一张"> 
						</p>
						<button class="logn"  onclick="doLogin();">登录</button>
					</form>
					<img src="img/index_logo.png" alt="" class="fl"/>
					<p>
						管理系统登录
					</p>
				</div>
			</div>
		</div>
	</body>

</html>
