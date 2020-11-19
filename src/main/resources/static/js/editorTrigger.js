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
  });
});