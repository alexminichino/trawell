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
            location:{required:true, regex:/^[A-Z a-z]$/},
            date:{required:true},
            description:{required:true},       
        },
         messages:{
            location:{
                regex:"Location entered doesn't respect the format: numbers and special characters not allowed. Retry!" 
            },    
        } 
    }); 
    });
    