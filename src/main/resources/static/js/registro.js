$(document).ready(function() {

	$('.input').focus(function() {
		$(this).parent().find(".label-txt").addClass('label-active');
	});

	$(".input").focusout(function() {
		if ($(this).val() == '') {
			$(this).parent().find(".label-txt").removeClass('label-active');
		}
		;
	});

});

var password = document.getElementById("password"), confirm_password = document
		.getElementById("confirm_password");

var check = function() {
	if (document.getElementById('password').value == document
			.getElementById('confirm_password').value) {
		document.getElementById('message').style.color = 'green';
		document.getElementById('message').innerHTML = 'matching';
	} else {
		document.getElementById('message').style.color = 'red';
		document.getElementById('message').innerHTML = 'not matching';
	}
}

password.onchange = validatePassword;
password.onkeyup = validatePassword;
confirm_password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;