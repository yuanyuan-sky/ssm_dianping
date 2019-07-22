$(function() {
	common.showMessage($("#message").val());
	$("#mainForm").validate({
		rules : {
			"title" : "required",
			"link" : "required",
			"weight" : {
				required : true,
				digits : true,
				maxlength : 5
			}
		},
		messages : {
			"title" : "请输入标题！"
		}
	});
});

var modify = {
	modify:function () {
		$("#mainForm").submit();
	},
	goback:function () {
		location.href = $('#basePath').val() + '/ad/addInit';
	}
}
