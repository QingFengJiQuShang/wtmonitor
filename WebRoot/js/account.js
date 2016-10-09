$(function(){
	
	$.post("user_userList", {}, function(data) {
		$("#table").bootstrapTable({
			data: data.data,
		});
	});
});

function actionFormatter(value, row, index) {
	console.log(row.company.id);
	return [
			'<a href="javascript:void(0);" data-target="#myModalUpdate" data-toggle="modal" onClick="loadById('
					+ row.id + ');loadCompany('+row.company.id+');loadRole('+row.company.id+')"><span>编辑</span></a>',
			'<a onClick="del(' + row.id
					+ ')" href="javascript:void(0);" ><span>删除</span></a>' ]
			.join('   ');
};

function del(id){
	$.post("user_delUser",{"id":id},function(data){
		if(data.status){
			alert('删除成功!');
			window.location.reload();
		}else{
			alert('删除失败!');
		}
	});
}


function loadRole(companyId){
	$.post("role_loadListByCompanyId",{"companyId":companyId},function(data){
		console.log(data);
		$("#roleAdd").empty();
		$("#roleUpdate").empty();
		var html ='';
		$.each(data.data,function(i,o){
			html += '<option id="'+o.id+'">'+o.name+'</option>';
		});
		$("#roleAdd").append(html);
		$("#roleUpdate").append(html);
	});
}

function loadCompany(companyId){
	$.post("company_loadList",{},function(data){
		$("#companyAdd").empty();
		$("#companyUpdate").empty();
		var html = '';
		$.each(data.data,function(i,o){
			if(companyId == o.id){
				html += '<option id="'+o.id+'" selected="selected">'+o.name+'</option>';
			}else{
				html += '<option id="'+o.id+'">'+o.name+'</option>';
			}
		});
		$("#companyAdd").append(html);
		$("#companyUpdate").append(html);
	});
}
var userId;
function loadById(id){
	userId = id;
	$.post("user_loadById",{"id":id},function(data){
		$("#loginNameUpdate").val(data.data.loginName);
		$("#pwdUpdate").val(data.data.password);
		$("#nameUpdate").val(data.data.name);
		$("#phoneUpdate").val(data.data.phone);
	});
}

function userUpdate(){
	var loginName = $("#loginNameUpdate").val();
	var pwd = $("#pwdUpdate").val();
	var name = $("#nameUpdate").val();
	var phone = $("#phoneUpdate").val();
	var companyId = $("#companyUpdate").find("option:checked").attr("id");
	var roleId = $("#roleUpdate").find("option:checked").attr("id");
	$.post("user_userUpdate",{
		"userId":userId,
		"loginName":loginName,
		"pwd":pwd,
		"phone":phone,
		"companyId":companyId,
		"roleId":roleId
	},function(data){
		if(data.status){
			alert('保存成功!');
			window.location.reload();
		}else{
			alert('保存失败!');
		}
	});
}

function userAdd(){
	var loginName = $("#loginNameAdd").val();
	var pwd = $("#pwdAdd").val();
	var name = $("#nameAdd").val();
	var phone = $("#phoneAdd").val();
	var companyId = $("#companyAdd").find("option:checked").attr("id");
	var roleId = $("#roleAdd").find("option:checked").attr("id");
	$.post("user_addUser",{
		"loginName":loginName,
		"pwd":pwd,
		"phone":phone,
		"name":name,
		"companyId":companyId,
		"roleId":roleId
	},function(data){
		if(data.status){
			alert("添加成功!");
			window.location.reload();
		}else{
			alert("添加失败");
		}
	});
}