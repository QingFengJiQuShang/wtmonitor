
/**
 * 自动加载 省
 */
 window.onload =function province (){
		
		$.ajax({
		     mtype:'post',
             url: "provinceAction.do?method=query",
             data: "",
             success: function(data){
					for(var i=0;i<data.rows.length;i++){
						$("#province").append("<option  value='"+data.rows[i].province+"'  onclick=\"city('"+data.rows[i].provinceid+"');\">"+data.rows[i].province+"</option>");

					}
             }
   		 });
	}
 
 /**
 * 自动加载 市
 */
  function city(province){
	  document.getElementById("city").options.length=1;  
	  document.getElementById("area").options.length=1;  
		$.ajax({
		     mtype:'post',
             url: "cityAction.do?method=query",
             data: "provinceid="+province,
             success: function(data){
					for(var i=0;i<data.rows.length;i++){
						
						$("#city").append("<option  value='"+data.rows[i].city+"'  onclick=\"area('"+data.rows[i].cityid+"');\">"+data.rows[i].city+"</option>");
					}
             }
   		 });
	}
 /**
 * 自动加载 区
 */
function area(city){
	document.getElementById("area").options.length=1;  
	
		$.ajax({
		     mtype:'post',
             url: "areaAction.do?method=query",
             data: "cityid="+city,
             success: function(data){
					for(var i=0;i<data.rows.length;i++){
						$("#area").append("<option value='"+data.rows[i].area+"'>"+data.rows[i].area+"</option>");

					}
             }
   		 });
	}