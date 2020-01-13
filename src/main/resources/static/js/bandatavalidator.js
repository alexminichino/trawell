/**
	 * @author Paone Mario
	 * The javascript provides a admin side validation of the ban data form
	 */

    $(document).ready(function() {
        $("form").validate({
          rules: {
            motivation : {
              required: true,
            },
            bannedUntil: {
              required: true,
              date: true
            }
          }, messages: {
            motivation: "Please insert a motivation",
            bannedUntil:  "Please insert valid date"    
        }
        });
      });