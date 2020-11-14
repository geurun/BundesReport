$(document).mousemove(function(event) {
	 eye = $(".eye");
	 x = (eye.offset().left) + (eye.width() / 2);
	 y = (eye.offset().top) + (eye.height() / 2);
	 rad = Math.atan2(event.pageX - x, event.pageY - y);
	 rot = (rad * (180 / Math.PI) * -1) + 180;
	.css({
		it-transform': 'rotate(' + rot + 'deg)',
		transform': 'rotate(' + rot + 'deg)',
		ransform': 'rotate(' + rot + 'deg)',
		form': 'rotate(' + rot + 'deg)'
	
});    (jQuery); // End of use strict
