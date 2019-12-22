$(document).ready(function() {
    $('#slider').hide();
    $('#toggle').click(function () {
        $('#slider').slideToggle();
        $('#target').attr("action") == 'signUp' ? $('#target').attr("action", "signUpAgency") : $('#target').attr("action", "signUp");
    });
});