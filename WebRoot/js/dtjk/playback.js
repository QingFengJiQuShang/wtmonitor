	function gotoUrl (){
			 var province= document.getElementById("province").value;
			 var city= document.getElementById("city").value;
			 var area= document.getElementById("area").value;
			 var registerid= document.getElementById("registerid").value;
			 var distinguishid= document.getElementById("distinguishid").value;
			 var useUnitName= document.getElementById("useUnitName").value;
			  var maintenanceUnitName= document.getElementById("maintenanceUnitName").value;
			 var propertyUnitName= document.getElementById("propertyUnitName").value;
			 var makeUnitName= document.getElementById("makeUnitName").value;
			 var installPlace= document.getElementById("installPlace").value;
			  var url="";
			  if(province!=""){
				  url=url+"&province="+province;
			  }
			  if(city!=""){
				  url=url+"&city="+city;
			  }
			  if(area!=""){
				  url=url+"&area="+area;
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
			  if(maintenanceUnitName!=""){
				  url=url+"&maintenanceUnitName="+maintenanceUnitName;
			  }
			  if(propertyUnitName!=""){
				  url=url+"&propertyUnitName="+propertyUnitName;
			  }
			   if(makeUnitName!=""){
				  url=url+"&makeUnitName="+makeUnitName;
			  }
			  if(installPlace!=""){
				  url=url+"&installPlace="+installPlace;
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