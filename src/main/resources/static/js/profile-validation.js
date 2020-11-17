$(form).submit(function(event) {
	event.preventDefault();

	var userForm = {};
	userForm["email"] = $("#email").val();

	$("#btnSave").prop("disabled", true);

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/user/profile/check",
		data: JSON.stringify(userForm),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
		}, success: function(data) {
			if (data.msgs.length == 0) {
				event.currentTarget.submit();
			} else {
				var json = "";
				data.msgs.forEach(function(msg) {
					msg = msg.replaceAll("\\\"", "")
					json += "<div class='alert alert-danger' role='alert'>" + msg + "</div>";
				});
				$('#feedback').html(json);
			}
			$("#btnSave").prop("disabled", false);
		}, error: function(e) {
			var json = "<div class='alert alert-danger' role='alert'>" + e.responseText + "</div>";
			$('#feedback').html(json);
			$("#btnSave").prop("disabled", false);
		}
	});
});
