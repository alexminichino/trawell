$.validator.addMethod('regex', function(value, element, param){
    return this.optional(element) || value.match(typeof param == 'string'? new RegExp(param) : param);
    }, message);

$( document ).ready(function() {

   $("#sign-up").validate({
        rules: {
            name:{
                required:true, regex: /^[A-Z a-z]{1,20}$/
            }, 
            surname:{
                required:true, regex: /^[A-Z a-z]{1,20}$/
            },
            email:{
                required:true, regex: /^[a-zA-Z0-9]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
            },
            password:{
                required:true, regex: /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{8,45}$/
            },
            verifica_pss:{
                required:true, function () {
                    return password == verificapss ? true : false;
                }
            }, 
            username:{
                required: true, regex: /^[a-zA-Z0-9]$/
            }
        }
   }
});