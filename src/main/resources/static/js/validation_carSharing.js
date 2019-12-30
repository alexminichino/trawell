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
            departure_date:{required:true, regex:/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/},
            description:{required:true},
            arrival:{required:true},
            carsharingspot:{required:true}
         
        },
         messages:{
            departure_date:{
                regex: "Date entered doesn't respect the following format:dd/mm/yyyy. Please Retry!" 
            },    
        } 
    }); 
    });
    