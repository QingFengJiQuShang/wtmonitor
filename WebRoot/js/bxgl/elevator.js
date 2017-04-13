	function gotoUrl (){
		 	var flag= document.getElementById("flag").value;
			 var registerid= document.getElementById("registerid").value;
			 var distinguishid= document.getElementById("distinguishid").value;
			 var useUnitName= document.getElementById("useUnitName").value;
			  var brand= document.getElementById("brand").value;
			 var numbers= document.getElementById("numbers").value;
			  var url="";
			  if(flag!=""){
				  url=url+"&flag="+flag;
			  }
			  if(registerid!=""){
				  url=url+"&registerid="+registerid;
			  }
			  if(distinguishid!=""){
				  url=url+"&distinguishid="+distinguishid;
			  }
			  if(useUnitName!=""){
				  url=url+"&useUnitName="+useUnitName;
			  }
			  if(brand!=""){
				  url=url+"&brand="+brand;
			  }
			  if(numbers!=""){
				  url=url+"&numbers="+numbers;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="safeAction.do?method=queryElevator"+gotoUrl();
		  }
		
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="elevatorAction.do?method=query&num="+num+gotoUrl();
         }
		   //下载  
		 function exp1(SafeState){
    		  window.location.href="elevatorAction.do?method=export&SafeState="+SafeState+gotoUrl ();
         }