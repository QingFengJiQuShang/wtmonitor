	function gotoUrl (){
			 var province= document.getElementById("province").value;
			 var city= document.getElementById("city").value;
			 var area= document.getElementById("area").value;
			 var name= document.getElementById("name").value;
			 var address= document.getElementById("address").value;
			 var liaisons= document.getElementById("liaisons").value;
			  var phone= document.getElementById("phone").value;
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
			  if(name!=""){
				  url=url+"&name="+name;
			  }
			  if(liaisons!=""){
				  url=url+"&liaisons="+liaisons;
			  }
			  if(address!=""){
				  url=url+"&address="+address;
			  }
			  if(phone!=""){
				  url=url+"&phone="+phone;
			  }
			  return url;
		  }
	   //模糊查询
		function query(){
    		  window.location.href="makeUnitAction.do?method=query"+gotoUrl();
		  }
		//添加
		function add(){
			  window.location="jsp/xtgl/makeUnit/addMakeUnit.jsp";
			  
		  }
/**
 * 上传页面
 */
upload=function(){
	JqueryDialog.Open('导入制造单位', 'jsp/xtgl/makeUnit/upload.jsp', 600, 300);
}
		//列表分页  
		  function fenye(num){
			
    		  window.location.href="makeUnitAction.do?method=query&num="+num+gotoUrl();
         }
		  //编辑
		  function findById(id,flag){
    		  window.location.href="makeUnitAction.do?method=findById&id="+id+"&flag="+flag;
         }
		//删除
		  function del(id){
			   if( confirm("确定删除这条制造单位记录？")){
    		  		window.location.href="makeUnitAction.do?method=delEntity&id="+id;
    		  }
         }
		    //下载  
		  function exp(){
    		  window.location.href="makeUnitAction.do?method=export"+gotoUrl ();
         }
		  
		   //模糊查询
		   //模糊查询
		function query1(){
			 var id= document.getElementById("id").value;
			 var id1= document.getElementById("id1").value;
    		 window.location.href="makeUnitAction.do?method=query1&id="+id+"&id1="+id1+gotoUrl();
		  }
		//列表分页  
		  function fenye1(num){
			 var id= document.getElementById("id").value;
			 var id1= document.getElementById("id1").value;
    		  window.location.href="makeUnitAction.do?method=query1&num="+num+"&id="+id+"&id1="+id1+gotoUrl();
         }
		  	//		批量删除
$(".del").click(function() {
	var ids="";
	var len=0;
	$(".wei").each(function() {
			if($(this).children("i").hasClass("gou")) {
				ids=ids+$(this).find("i").children().val()+",";
				len++;
			}
		})
		 if(len>0 && confirm("确定删除这 "+len+" 条记录？")){
	 		window.location.href="makeUnitAction.do?method=deleteEntity&ids="+ids;
		 }
})
		  

	//全选
$("tbody").on("click", ".wei", function() {
		$(this).children("i").toggleClass("gou");
		var checked = $(".wei").length;
		var unchecked = $("tbody").find(".gou").length
		if(checked == unchecked) {
			$(".all").children("i").addClass("gou")
		} else {
			$(".all").children("i").removeClass("gou")
		}
	})
	//取消全选
$(".all").on("click", function() {
	var checked = $(".wei").length;
	var unchecked = $("tbody").find(".gou").length
	if(checked == unchecked) {
		$(this).children("i").removeClass("gou")
		$("tbody").find("i").removeClass("gou")
	} else {
		$(this).children("i").addClass("gou")
		$("tbody").find("i").addClass("gou")
	}
})
