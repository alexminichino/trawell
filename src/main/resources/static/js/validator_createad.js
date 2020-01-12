/**
	 * @author Paone Mario
	 * The javascript provides a agency side validation of create ad form
	 */

    $(document).ready(function() {
        $("form").validate({
          rules: {
            payment_method : {
              required: true,
              minlength: 16,
              maxlength: 16
            },
            starting_time: {
              required: true,
              date: true
            }
          }, messages: {
            payment_method:{
               required: "Insert a valid credit card number",
               minlength: "Credit card must have 16 numbers",
               maxlength: "Credit card must have 16 numbers"
            },
            starting_time:  "Please insert valid date"    
        }
        });
      });