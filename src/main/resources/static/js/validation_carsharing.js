/**
 * @author Alfieri Davide
 */

$.validator.addMethod(
    "regex",
    function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
);

$(document).ready(function(){
    $("#target").validate({
        rules:{
            departureDate:{required:true},
            departure:{required:true, regex:/^[A-Z a-z]$/},
            description:{required:true},
            arrival:{required:true, regex:/^[A-Z a-z]$/},
            carsharingspot:{required:true}
         
        },
         messages:{
            departure:{
                regex:"Departure entered doesn't respect the format: numbers and special characters not allowed. Retry!" 
            },    
            arrival:{
                regex:"Departure entered doesn't respect the format: numbers and special characters not allowed. Retry!"
            }
        } 
    }); 
    });
    