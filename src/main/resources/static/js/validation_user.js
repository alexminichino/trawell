$.validator.addMethod(
    "regex",
    function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
);
/**
	 * @author Alfieri Davide
	 * the javascript is used to provide a client side validation of any data
     * regarding users of our platform. It uses JQuery validation plugin.
     *  */

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
            required:true
        },
        mail:{
            required:true, regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        },
        password:{
            required:true,regex: /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{8,45}$/
        },
        verify:{
            required:true,
            equalTo :'#password'
        },
        phone:{
            required:true
        },
        username:{
            required: true, regex: /^[A-Z a-z 0-9]{1,45}$/  
        },
        nameAgency:{
            required:true, regex:/^[A-Z a-z]{1,45}$/
        },
        url:{
            required:true, regex:/^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/ 
        },
        vat:{
            required:true, regex:/^((AT)?U[0-9]{8}|(BE)?0[0-9]{9}|(BG)?[0-9]{9,10}|(CY)?[0-9]{8}L|↵(CZ)?[0-9]{8,10}|(DE)?[0-9]{9}|(DK)?[0-9]{8}|(EE)?[0-9]{9}|↵(EL|GR)?[0-9]{9}|(ES)?[0-9A-Z][0-9]{7}[0-9A-Z]|(FI)?[0-9]{8}|↵(FR)?[0-9A-Z]{2}[0-9]{9}|(GB)?([0-9]{9}([0-9]{3})?|[A-Z]{2}[0-9]{3})|↵(HU)?[0-9]{8}|(IE)?[0-9]S[0-9]{5}L|(IT)?[0-9]{11}|↵(LT)?([0-9]{9}|[0-9]{12})|(LU)?[0-9]{8}|(LV)?[0-9]{11}|(MT)?[0-9]{8}|↵(NL)?[0-9]{9}B[0-9]{2}|(PL)?[0-9]{10}|(PT)?[0-9]{9}|(RO)?[0-9]{2,10}|↵(SE)?[0-9]{12}|(SI)?[0-9]{8}|(SK)?[0-9]{10})$/
        }
    },
     messages:{
        username:{
            regex: "special characters not allowed and must not exceed 45 characters. Retry!" 
        },    
        name:{
            regex:"Name entered is not correct: special characters not allowed and must not exceed 45 characters. Retry!"
        },
        surname:{
            regex:"Surname entered is not correct: special characters not allowed and must not exceed 45 characters. Retry!"
        },
        mail:{
            regex: "Mail entered is not correct: special characters not allowed. Retry!"
        },
        password:{
            regex: "Password entered is not correct: minimum number of characters 8 and accept only these special characters: . ! # $ % & ' * + / = ? ^ _ ` { | } ~ - . Retry!"
        }, 
        nameAgency:{
            regex:"Agency's name entered is not correct: special characters not allowed and must not exceed 45 characters. Retry!"
        },
        url:{
            regex:"Agency's url entered is malformed. Retry!"
        },
        vat:{
            regex:"VATnumber entered is not correct: format not allowed. Retry!"
        }
} 
}); 
});