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
			onImageUploadError: function(msg) {
				alert('Change image! (Max: 2MB)');
			}
		}
	});
});
