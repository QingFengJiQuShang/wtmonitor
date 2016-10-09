$(function(){

});


//重新获取验证码
function vcode(){
	$("#vcode").attr("src","page/vcode.jsp?time="+(new Date()).valueOf());
}
//回车登陆
function GoURLB(event) {
    if (event.keyCode == 13) {
    	login();
    }
}

function doLogin(){
	var loginName = $("input[name='loginName']").val();
	var password = $("input[name='password']").val();
	
	$.doService(
		"user_login",
		{"loginName":loginName,"password":password},
		function(data){
			location.href="page/index.jsp";
		},function(errorCode,errorMsg){
				alert('登陆失败');
		}
	);
}