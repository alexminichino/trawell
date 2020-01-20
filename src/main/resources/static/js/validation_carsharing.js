/**
 * @author Alfieri Davide
 */
    // Wait for the DOM to be ready
$(function() {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("#target").validate({
      // Specify validation rules
      rules: {
        // The key name on the left side is the name attribute
        // of an input field. Validation rules are defined
        // on the right side

       
           

        departureDate: "required",
        description: "required",
        carsharingspot: "required",
        departure: "required"
        
       
      },
      // Specify validation error messages
      messages: {
        departureDate: "Please enter departure date",
        carsharingspot: "Please enter spot number",
        description: "Please enter description",
        departure: "Please enter departure"
      },
      // Make sure the form is submitted to the destination defined
      // in the "action" attribute of the form when valid
      submitHandler: function(form) {
        form.submit();
      }
    });
  });
    