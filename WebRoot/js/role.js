$(function() {
	loadUserRights();
	var roleId = $("#sessionRoleId").val();
	var companyId = $("#sessionCompanyId").val();
	if(roleId == '1'){
		loadCompany();
	}else{
		loadCompanyRole(companyId);
	}
	
});

function loadCompany() {
	var search = $("#search").val();
	$.post("company_loadList", {
		"search" : search,
	}, function(data) {
		var html = '';
		$.each(data.data, function(i, o) {
			html += '<li class="list-group-item">'
					+ '<a href="javascript:void(0)" onclick="loadCompanyRole('
					+ o.id + ')">' + o.name + '</a></li>';
		});
		$("#company").append(html);
	});
}

function loadCompanyRole(companyId) {
	$("#role").empty();
	$.post("role_loadListByCompanyId", {
		"companyId" : companyId
	}, function(data) {
		var html = '';
		$.each(data.data, function(i, o) {
			html += '<li class="list-group-item">'
					+ '<a href="javascript:void(0)" onclick="addRightsList('
					+ o.id + ')">' + o.name + '</a></li>';

		});
		$("#role").append(html);
	});
}

function loadUserRights() {
	$("#rights").empty();
	$
			.post(
					"roleRightsRefere_loadRights",
					{},
					function(data) {
						var html = '';
						$
								.each(
										data.data,
										function(i, o) {
											html += '<li class="list-group-item">';
											html += '<div class="checkbox">';
											html += '<label><input type="checkbox" name="rightsCheckbox" id="rights_'
													+ o.rights.id
													+ '" value="'
													+ o.rights.id
													+ '"/>'
													+ o.rights.name
													+ '</label>';
											html += '</div>';
											html += '</li>';
										});
						$("#rights").append(html);
					});
}

function addRightsList(roleId) {
	$("#roleIdSave").val(roleId);
	$.post("roleRightsRefere_loadRightsById", {
		"roleId" : roleId
	}, function(data) {
		$(":checkbox").prop('checked', false);
		$.each(data.data, function(i, o) {
			console.log(o.rights.id);
			$("#rights_" + o.rights.id).prop("checked", true);
		});
	});
}

function save() {
	var roleId = $("#roleIdSave").val();
	$.ajax({
		data : {
			roleId : roleId
		},
		async : false,
		url : "roleRightsRefere_del",
		type : "POST",
		dataType : 'json',
		success : function(data) {
			$('input[name="rightsCheckbox"]:checked').each(function() {
				$.ajax({
					data : {
						roleId : roleId,
						rightsId : $(this).val()
					},
					async : false,
					url : "roleRightsRefere_add",
					type : "POST",
					dataType : 'json',
					success : function(data) {
					}
				});
			});
		}
	});
	alert('保存成功!');
};

function addRole(){
	var name = $("#roleName").val();
	$.post("role_add",{"name":name},function(data){
		window.location.reload();
	});
}