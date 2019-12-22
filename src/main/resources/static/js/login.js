/**
	 * @author Alfieri Davide
	 * The javascript provides a client side validation of the login form
   * which obbligates user to compile both username and password fields
	 */

$(document).ready(function() {
    $("form").validate({
      rules: {
        username : {
          required: true,
        },
        password: {
          required: true
        }
      }, messages: {
        username: "Please insert username",
        password:  "Please insert password"    
    }
    });
  });