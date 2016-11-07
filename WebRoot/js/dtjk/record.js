	function gotoUrl (){
			 var elevatorId= document.getElementById("elevatorId").value;
			 var direction= document.getElementById("direction").value;
			 var people= document.getElementById("people").value;
			  var door= document.getElementById("door").value;
			  var url="";
			  if(elevatorId!=""){
				  url=url+"&elevatorId="+elevatorId;
			  }
			  if(direction!=""){
				  url=url+"&direction="+direction;
			  }
			  if(people!=""){
				  url=url+"&people="+people;
			  }
			  if(door!=""){
				  url=url+"&door="+door;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="recordAction.do?method=query"+gotoUrl();
		  }
		
		//列表分页  
		  function fenye(num){
    		  window.location.href="recordAction.do?method=query&num="+num+gotoUrl();
         }
		   //编辑
		  function findById(id){
    		  window.location.href="recordAction.do?method=findById&id="+id;
         }