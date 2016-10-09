$.extend({
	doService:function(url,param,success,err){
		/*$("body").append('<iframe id="waitframe"  src="page/wait.html" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" ></iframe>');
		*/
		$.ajax({
			"url":url,
			"type":"post",
			"data":param,
			"beforeSend":function(){
				$("body").append('<iframe id="waitframe"  src="page/wait.html" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" ></iframe>');
			},
			"complete":function(){
				$("iframe#waitframe").remove();
			},
			"success":function(result){
				if (result.status) {
					if(success && jQuery.isFunction(success)){
						success.call(this, result.data);
					}
				}
				else if (err && jQuery.isFunction(err)){
					err.call(this,result.errorCode,result.errorMsg);
				}else{
					alert(result.errorMsg);
				}
			},
			"error":function(error){
				alert(error);
			}
		});
	}
});
