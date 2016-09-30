$(function() {
	$.post("proclamation_loadList", {}, function(data) {
		$("#table").bootstrapTable({
			data : data.data
		});
	});
});



function actionFormatter(value, row, index) {
	return [
			'<a href="javascript:;" data-target="#myModalUpdate" data-toggle="modal" onClick="loadById('
					+ row.id + ')"><span>编辑</span></a>',
			'<a onClick="del(' + row.id
					+ ')" href="javascript:;" ><span>删除</span></a>' ]
			.join('   ');
};

function titleFormatter(value, row, index){
	return [
			'<a href="page/proclamation.jsp?id='+row.id+'" target="indexContent"><span>'+row.title+'</span></a>' ]
			.join('   ');
}

function add(){
	var title = $("#titleAdd").val();
	var msg = $("#msgAdd").val();
	$.post("proclamation_add",{"title":title,"msg":msg},function(data){
		if(data.status){
			alert("添加成功");
			window.location.reload();
		}else{
			alert("添加失败");
		}
	});
}

function del(id){
	$.post("proclamation_del",{"id":id},function(data){
		if(data.status){
			alert("删除成功");
			window.location.reload();
		}else{
			alert("删除失败");
		}
	});
}

var proclamationId;
function loadById(id){
	proclamationId = id;
	$.post("proclamation_loadById",{"id":id},function(data){
		console.log(data);
		$("#titleUpdate").val(data.data[0].title);
		$("#msgUpdate").val(data.data[0].msg);
	});
}

function update(){
	var title = $("#titleUpdate").val();
	var msg = $("#msgUpdate").val();
	$.post("proclamation_update",{"id":proclamationId,"title":title,"msg":msg},function(data){
		if(data.status){
			alert("修改成功");
			window.location.reload();
		}else{
			alert("修改失败");
		}
	});
}

