$(document).ready(function(){
    $.fn.serializeFormJSON = function () {

        var o = {headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        }};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    
    $.fn.exists = function () {
        return this.length !== 0;
    }

    $('#target').submit(function (e) {
        e.preventDefault();
        var data = $('form').serializeFormJSON();
        var URL = $('form').attr("action");
        
        if (!$("#target .error").exists()) {
            $.ajax({
                type:"POST",
                url:URL,
                data: data,
                success:function(data){
                    alert("successo");
                }
            });
        }
    });
});
