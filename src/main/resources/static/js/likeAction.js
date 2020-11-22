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
