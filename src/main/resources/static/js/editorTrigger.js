$(document).ready(function() {
	$('#editor').summernote({
		toolbar: [
			['fontname', ['fontname']],
			['fontsize', ['fontsize']],
			['font', ['bold', 'underline', 'clear']],
			['color', ['color']],
			['para', ['ul', 'ol', 'paragraph']],
			['table', ['table']],
			['insert', ['picture']],
		],
		height: 400,
		maximumImageFileSize: 2097152,
		callbacks: {
			onImageUpload: function(files) {
				if (!files.length) {
					return;
				}
				var file = files[0];
				if (file.size > 2097152) {
					alert('Change image! (Max: 2MB)');
					return;
				}
				var reader = new FileReader();
				reader.onloadend = function() {
					var img = $("<img>").attr({ src: reader.result, width: "100%" });
					$('#editor').summernote("insertNode", img[0]);
				}
				if (file) {
					reader.readAsDataURL(file);
				}
			}
		}
	});
});

function maxLength(object) {
	if (object.value.length > object.maxLength) {
		object.value = object.value.slice(0, object.maxLength);
	}
}
