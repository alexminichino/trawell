/**
	 * @author Alfieri Davide
	 */ 

$(document).ready(function() {
    $('#slider').hide();
    $('#toggle').click(function () {
        $('#slider').slideToggle();
        $('#target').attr("action") == '/users/signUp' ? $('#target').attr("action", "/users/signUpAgency") : $('#target').attr("action", "/users/signUp");
    });
});