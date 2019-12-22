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
        name:{
            required:true, regex: /^[A-Z a-z]{1,45}$/
        }, 
        surname:{
            required:true, regex: /^[A-Z a-z]{1,45}$/
        },
        birth:{
            required:true, regex:/^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)(?:0?2|(?:Feb))\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/
        },
        mail:{
            required:true, regex: /^[a-zA-Z0-9]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
        },
        password:{
            required:true,regex: /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{8,45}$/
        },
        verify:{
            required:true, function () {
                return $("input[name = 'password']").val() == $("input[name = 'verify']").val() ? true : false;
            }
        }, 
        username:{
            required: true, regex: /^[a-zA-Z0-9]$/   
        },
        phone:{
            required:true, regex:/^[+][(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]$/
        },
        bio:{
            requirde:true, regex:/^[a-zA-Z0-9 .!#$%&'*+/=?^_`{|}~-]{1,5000}$/
        },
        nameAgency:{
            required:true, regex:/^[A-Z a-z]{1,45}$/
        },
        url:{
            required:true, regex:/^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/ 
        },
        vat:{
            required:true, regex:/^((AT)?U[0-9]{8}|(BE)?0[0-9]{9}|(BG)?[0-9]{9,10}|(CY)?[0-9]{8}L|↵(CZ)?[0-9]{8,10}|(DE)?[0-9]{9}|(DK)?[0-9]{8}|(EE)?[0-9]{9}|↵(EL|GR)?[0-9]{9}|(ES)?[0-9A-Z][0-9]{7}[0-9A-Z]|(FI)?[0-9]{8}|↵(FR)?[0-9A-Z]{2}[0-9]{9}|(GB)?([0-9]{9}([0-9]{3})?|[A-Z]{2}[0-9]{3})|↵(HU)?[0-9]{8}|(IE)?[0-9]S[0-9]{5}L|(IT)?[0-9]{11}|↵(LT)?([0-9]{9}|[0-9]{12})|(LU)?[0-9]{8}|(LV)?[0-9]{11}|(MT)?[0-9]{8}|↵(NL)?[0-9]{9}B[0-9]{2}|(PL)?[0-9]{10}|(PT)?[0-9]{9}|(RO)?[0-9]{2,10}|↵(SE)?[0-9]{12}|(SI)?[0-9]{8}|(SK)?[0-9]{10})$/
        },
    },
     messages:{
        username:{
            regex: "Username entered is not correct. Retry!" 
        },    
        name:{
            regex:"Name entered is not correct. Retry!"
        },
        surname:{
            regex:"Surname entered is not correct. Retry!"
        },
        birth:{
            regex:"Birth entered is not correct. Retry!"
        },
        mail:{
            regex: "Mail entered is not correct. Retry!"
        },
        password:{
            regex: "Password entered is not correct. Retry!"
        },
        verify:{
            regex:"Passwords are not equals. Retry!"
        }, 
        phone:{
            regex:"Numberphone entered is not correct. Retry!"
        },
        bio:{
            regex:"Bio entered is not correct. Retry!"
        },
        nameAgency:{
            regex:"Agency's name entered is not correct. Retry!"
        },
        url:{
            regex:"Agency's url entered non exists. Retry!"
        },
        vat:{
            regex:"VATnumber entered is not correct. Retry!"
        },


} 
}); 
});
