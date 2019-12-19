$(document).ready(function(){
    $("#target").validate({
        rules:{
            name:{
                required:true, minlenght:1, maxlenght:45
            }, 
            surname:{
                required:true, minlenght:1, maxlenght:45
            },
            birthday:{
                required:true, minlenght:1, maxlenght:45
            },
            email:{
                required:true, 
            },
            password:{
                required:true, minlenght:1, maxlenght:45
            },
            verify_pss:{
                required:true, function () {
                    return password == verificapss ? true : false;
                }
            }, 
            username:{
                required: true, minlenght:1, maxlenght:45
            },
            numbPhone:{
                required:true
            },
            bio:{
                requirde:true
            },
            nameAgency:{
                required:true, minlenght:1, maxlenght:45
            },
            agencyPhone:{
                required:true   
            },
            urlAgencyPhone:{
                required:true
            },
            VATnumber:{
                required:true
            }
        }
   }); 
});
