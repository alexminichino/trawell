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
    
    $('#target').click(function (e) {
        e.preventDefault();
        var data = $('form').serializeFormJSON();
        var URL = $('form').attr("action");
        console.log(URL);
        $.ajax({
            type:"POST",
            url:URL,
            data: data,
            success:function(data){
                alert("successo");
            }
        });
    });
});
