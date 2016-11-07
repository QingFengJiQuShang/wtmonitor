	function gotoUrl (){
			 var registerid= document.getElementById("registerid").value;
			 var distinguishid= document.getElementById("distinguishid").value;
			 var useUnitName= document.getElementById("useUnitName").value;
			  var brand= document.getElementById("brand").value;
			 var numbers= document.getElementById("numbers").value;
			  var url="";
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
    		  window.location.href="elevatorAction.do?method=queryPlayback"+gotoUrl();
		  }
		
		//列表分页  
		  function fenye(num){
    		  window.location.href="elevatorAction.do?method=queryPlayback&num="+num+gotoUrl();
         }
		   //编辑
		  function findById(id){
			 // alert(id);
    		  window.location.href="recordAction.do?method=query&elevatorId="+id;
         }