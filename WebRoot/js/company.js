$(function() {
	$.post("company_loadList", {}, function(data) {
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

function add(){
	var name = $("#nameAdd").val();
	var address = $("#addressAdd").val();
	var tel = $("#telAdd").val();
	var description = $("#descriptionAdd").val();
	$.post("company_add",{
		"name":name,
		"address":address,
		"tel":tel,
		"description":description
	},function(data){
		if(data.status){
			alert("添加成功!");
			window.location.reload();
		}else{
			alert("添加失败!");
		}
	});
}
var companyId;
function loadById(id){
	companyId = id;
	$.post("company_loadById",{"id":id},function(data){
		$("#nameUpdate").val(data.data.name);
		$("#addressUpdate").val(data.data.address);
		$("#telUpdate").val(data.data.tel);
		$("#descriptionUpdate").val(data.data.description);
	});
}

function update(){
	var name = $("#nameUpdate").val();
	var address = $("#addressUpdate").val();
	var tel = $("#telUpdate").val();
	var description = $("#descriptionUpdate").val();
	$.post("company_update",{
		"id":companyId,
		"name":name,
		"address":address,
		"tel":tel,
		"description":description
	},function(data){
		if(data.status){
			alert("保存成功!");
			window.location.reload();
		}else{
			alert("保存失败!");
		}
	});
}

function del(id){
	$.post("company_del",{"id":id},function(data){
		if(data.status){
			if(data.status){
				alert("删除成功!");
				window.location.reload();
			}else{
				alert("删除失败!");
			}
		}
	});
}
