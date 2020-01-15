/**
	 * @author Alfieri Davide
	 * the javascript allows for the modify data functionality to be dealt asynchronously via 
     * JSON.
     *  */

$(document).ready(function(){
    /**
	 * @author Alfieri Davide
	 * the function automatically serializes form data to json object
     *  */
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

    /**
	 * @author Alfieri Davide
     * the function makes the ajax call to server to modify the clients data in database
     *  */
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
                    custom_alert("Message","Your data has been updated!");
                },
                error:function(request,textStatus,errorThrown){
                    custom_alert("Message","failed to update your data!");
                }
            });
        }
    });
});
