function Loading() {
	var maskHeight = Math.max($(document).height(), $(window).height());
	var maskWidth = $(window).width();

	var mask = "<div id='mask' style='position:fixed; z-index:9000; background-color:#ffffff; display:none; left:0; top:0; width:100%; height:100%;'></div>";
	var loadingImg = '';

	loadingImg += " <div id='loadingImg'>";
	loadingImg += " <img src='/resources/img/icons8-ferris-wheel.gif' style='position:fixed; z-index:9500; display:block; margin:auto; top:50%; left:50%; transform:translate(-50%, -50%);'/>";
	loadingImg += "</div>";

	$('body').append(mask).append(loadingImg);

	$('#mask').css({
		'opacity' : '0.5'
	});

	$('#mask').show();
	$('#loadingImg').show();
}

function closeLoading() {
	$('#mask, #loadingImg').hide();
	$('#mask, #loadingImg').remove();
}

$(function() {

	Loading();
	setTimeout("closeLoading()", 500);

});