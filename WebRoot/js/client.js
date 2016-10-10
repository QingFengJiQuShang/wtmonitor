$(function() {
	$.post("client_loadList",{},function(data){
		$("#table").bootstrapTable({
			data : data.data
		});
	});
});


function actionFormatter(value, row, index) {
	return [
			'<a href="javascript:void(0);" data-target="#myModalUpdate" data-toggle="modal" onClick="loadById('
					+ row.id + ')"><span>编辑</span></a>',
			'<a onClick="del(' + row.id
					+ ')" href="javascript:void(0);" ><span>删除</span></a>' ]
			.join('   ');
};

function add(){
	var name = $("#nameAdd").val();
	var company = $("#companyAdd").val();
	var phone = $("#phoneAdd").val();
	var description = $("#descriptionAdd").val();
	var address = $("#addressAdd").val();
	$.post("client_addClient",{
		"name":name,
		"company":company,
		"phone":phone,
		"description":description,
		"address":address
	},function(data){
		if(data.status){
			alert("添加成功!");
			window.location.reload();
		}else{
			alert("添加失败!");
		}
	});
}

function del(id){
	$.post("client_del",{"id":id},function(data){
		if(data.status){
			alert("删除成功!");
			window.location.reload();
		}else{
			alert("删除失败!");
		}
	});
}
var updateId;
function loadById(id){
	updateId = id;
	$.post("client_loadById",{"id":id},function(data){
		$("#nameUpdate").val(data.data.name);
		$("#companyUpdate").val(data.data.company);
		$("#phoneUpdate").val(data.data.phone);
		$("#addressUpdate").val(data.data.address);
		$("#descriptionUpdate").val(data.data.description);
	});
}

function update(){
	var name = $("#nameUpdate").val();
	var company = $("#companyUpdate").val();
	var phone = $("#phoneUpdate").val();
	var description = $("#descriptionUpdate").val();
	var address = $("#addressUpdate").val();
	$.post("client_update",{
		"id":updateId,
		"name":name,
		"company":company,
		"phone":phone,
		"address":address,
		"description":description
	},function(data){
		if(data.status){
			alert("修改成功!");
			window.location.reload();
		}else{
			alert("修改失败!");
		}
	});
}