var recaptchachecked = false;

function recaptchaCallback() {
	$.ajax({
		type: "POST",
		url: '/verifyRecaptcha',
		data: {
			recaptcha: $("#g-recaptcha-response").val()
		},
		beforeSend: function(xhr) {
			xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
		},
		success: function(data) {
			if (data.msgs.length == 0) {
				recaptchachecked = true;
			} else {
				var json = "";
				data.msgs.forEach(function(msg) {
					msg = msg.replaceAll("\\\"", "")
					json += "<div class='alert alert-danger' role='alert'>" + msg + "</div>";
				});
				$('#feedback').html(json);
				recaptchachecked = false;
			}
		}
	});
}

$(form).submit(function(event) {
	$('#feedback').html("");
	if (!recaptchachecked) {
		alert("자동 가입 방지 항목을 체크해주세요.");
		return false;
	}
	event.preventDefault();

	var userForm = {};
	userForm["username"] = $("#username").val();
	userForm["email"] = $("#email").val();

	$("#signup").prop("disabled", true);

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/signup/check",
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
			$("#signup").prop("disabled", false);
		}, error: function(e) {
			var json = "<div class='alert alert-danger' role='alert'>" + e.responseText + "</div>";
			$('#feedback').html(json);
			$("#signup").prop("disabled", false);
		}
	});
});
