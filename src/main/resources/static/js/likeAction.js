$("#btnPostLike").on("click", function() {
	event.preventDefault();
	$("#btnPostLike").prop("disabled", true);

	var likeForm = {};
	likeForm["postId"] = $("#postId").val();

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/like/post",
		data: JSON.stringify(likeForm),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
		}, success: function(data) {
			if ($("#btnPostLike").hasClass("btn-success")) {
				$("#btnPostLike").removeClass("btn-success");
				$("#btnPostLike").addClass("btn-outline-danger");
			} else {
				$("#btnPostLike").removeClass("btn-outline-danger");
				$("#btnPostLike").addClass("btn-success");
			}
		}, error: function(e) {
			$("#btnPostLike").prop("disabled", false);
		}
	});
});

$(".btn").click(function() {
	var btnId = $(this).attr("id");
	if (btnId.indexOf("btnModify") != -1) {
		var num = btnId.substring(9);
		var txtComment = $("#txtComment" + num);
		if (txtComment.attr("readonly")) {
			txtComment.attr("readonly", false);
		} else {
			commentModifyAction($(btnId), txtComment, num);
		}
	}
});

function commentModifyAction(btnModify, txtComment, num) {
	event.preventDefault();
	btnModify.prop("disabled", true);

	var commentForm = {};
	commentForm["id"] = num;
	commentForm["content"] = txtComment.val();

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/comment/update",
		data: JSON.stringify(commentForm),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
		}, success: function(data) {
			txtComment.attr("readonly", true);
			btnModify.prop("disabled", false);
		}, error: function(e) {
			txtComment.attr("readonly", true);
			btnModify.prop("disabled", false);
		}
	});
};