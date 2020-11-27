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
			if ($("#btnPostLike").hasClass("btn-info")) {
				$("#btnPostLike").removeClass("btn-info");
				$("#btnPostLike").addClass("btn-outline-danger");
				$("#postLikeCount").text(Number($("#postLikeCount").text()) + 1);
			} else {
				$("#btnPostLike").removeClass("btn-outline-danger");
				$("#btnPostLike").addClass("btn-info");
				$("#postLikeCount").text(Number($("#postLikeCount").text()) - 1);
			}
			$("#btnPostLike").prop("disabled", false);
		}, error: function(e) {
			$("#btnPostLike").prop("disabled", false);
		}
	});
});

$(".ajaxTrigger").click(function() {
	var btnId = $(this).attr("id");
	if (btnId.indexOf("btnModify") != -1) {
		var num = btnId.substring(9);
		var txtComment = $("#txtComment" + num);
		if (txtComment.attr("readonly")) {
			txtComment.attr("readonly", false);
			txtComment.removeClass("comment-entered");
		} else {
			commentModifyAction($(btnId), txtComment, num);
		}
	}
	if (btnId.indexOf("btnCommentLike") != -1) {
		var num = btnId.substring(14);
		commentLikeAction($('#' + btnId), num);
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
			txtComment.addClass("comment-entered");
			btnModify.prop("disabled", false);
		}, error: function(e) {
			txtComment.attr("readonly", true);
			txtComment.addClass("comment-entered");
			btnModify.prop("disabled", false);
		}
	});
};

function commentLikeAction(btnCommentLike, num) {
	event.preventDefault();
	btnCommentLike.prop("disabled", true);
	var likeForm = {};
	likeForm["commentId"] = num;

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/like/comment",
		data: JSON.stringify(likeForm),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
		}, success: function(data) {
			if (btnCommentLike.hasClass("btn-info")) {
				btnCommentLike.removeClass("btn-info");
				btnCommentLike.addClass("btn-outline-danger");
				$('#commentLikeCount' + num).text(Number($('#commentLikeCount' + num).text()) + 1);
			} else {
				btnCommentLike.removeClass("btn-outline-danger");
				btnCommentLike.addClass("btn-info");
				$('#commentLikeCount' + num).text(Number($('#commentLikeCount' + num).text()) - 1);
			}
			btnCommentLike.prop("disabled", false);
		}, error: function(e) {
			btnCommentLike.prop("disabled", false);
		}
	});
};
