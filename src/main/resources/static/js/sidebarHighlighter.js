$(document).ready(function() {
	var path = $(location).attr('pathname');
	if ($("#hidCategory").length) {
		path = $("#hidCategory").val();
	}
	if (path.indexOf("free_board") >= 0) {
		$.setBoard(0, $("#lnkFree"));
	} else if (path.indexOf("abroad_board") >= 0) {
		$.setBoard(0, $("#lnkAbroad"));
	} else if (path.indexOf("living_qa") >= 0) {
		$.setBoard(0, $("#lnkLiving"));
	} else if (path.indexOf("deutsch_qa") >= 0) {
		$.setBoard(0, $("#lnkDeutsch"));
	} else if (path.indexOf("flea_market") >= 0) {
		$.setBoard(0, $("#lnkFlea"));
	} else if (path.indexOf("job_search") >= 0) {
		$.setBoard(0, $("#lnkJob"));
	} else if (path.indexOf("club") >= 0) {
		$.setBoard(0, $("#lnkClub"));
	} else if (path.indexOf("event_notification") >= 0) {
		$.setBoard(0, $("#lnkEvent"));
	} else if (path.indexOf("recipe") >= 0) {
		$.setBoard(0, $("#lnkRecipe"));
	} else if (path.indexOf("gallery") >= 0) {
		$.setBoard(0, $("#lnkGallery"));
	} else if (path.indexOf("/game/rain") >= 0) {
		$.setBoard(1, $("#lnkRain"));
	} else if (path.indexOf("/game/article") >= 0) {
		$.setBoard(1, $("#lnkArticle"));
	}
});

$.setBoard = function(from, collapseItem) {
	if (from == 0) {
		$("#navBoardItem").addClass("active");
		$("#navBoardLink").removeClass("collapsed");
		$("#collapseOne").addClass("show");
		collapseItem.addClass("active");
	} else if (from == 1) {
		$("#navGameItem").addClass("active");
		$("#navGameLink").removeClass("collapsed");
		$("#collapseTwo").addClass("show");
		collapseItem.addClass("active");
	}
}
