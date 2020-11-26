// Call the dataTables jQuery plugin
$(document).ready(function() {
	if ($('#postListDT').length) {
		$('#postListDT').DataTable({"order": [[ 2, "desc" ]]});
	}
	if ($('#noteListDT').length) {
		$('#noteListDT').DataTable({"order": [[ 2, "asc" ]]});
	}
});
