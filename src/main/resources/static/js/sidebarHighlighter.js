$(document).ready(function() {
	var path = $(location).attr('pathname');
	if (path.indexOf("/post/list/free_board") >= 0) {
		$.setBoard(0, $("#lnkFree"));
	} else if (path.indexOf("/post/list/abroad_board") >= 0) {
		$.setBoard(0, $("#lnkAbroad"));
	} else if (path.indexOf("/post/list/living_qa") >= 0) {
		$.setBoard(0, $("#lnkLiving"));
	} else if (path.indexOf("/post/list/flea_market") >= 0) {
		$.setBoard(0, $("#lnkFlea"));
	} else if (path.indexOf("/post/list/job_search") >= 0) {
		$.setBoard(0, $("#lnkJob"));
	} else if (path.indexOf("/post/list/club") >= 0) {
		$.setBoard(0, $("#lnkClub"));
	} else if (path.indexOf("/post/list/event_notification") >= 0) {
		$.setBoard(0, $("#lnkEvent"));
	} else if (path.indexOf("/post/list/recipe") >= 0) {
		$.setBoard(0, $("#lnkRecipe"));
	} else if (path.indexOf("/post/list/gallery") >= 0) {
		$.setBoard(0, $("#lnkGallery"));
	} else if (path.indexOf("/game/rain") >= 0) {
		$.setBoard(1, $("#lnkRain"));
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
