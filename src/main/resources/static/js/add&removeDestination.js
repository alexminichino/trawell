/**
    * @author Alfieri Davide
    * the function makes the ajax call to server to modify the clients data in database
*/

        $(document).ready(function(){
            var wrapper = $(".destination");
            var add_button = $(".add");
            var x = 1;
            $(add_button).click(function(e){
                e.preventDefault();
                x++;
                $(wrapper).append('<div> Destinazione:<input type = "text" name = "location" placeholder= "location"><br> Data:<input type = "date" name = "date"><br> Desccrizione:<input type = "text" name = "description" placeholder= "description"><br> <button style = "background-color:red;" class = "remove">Rimuovi la destinazione</button>' );
                             
            })
        
            $(wrapper).on("click",".remove",function(e){
                e.preventDefault();
                $(this).parent('div').remove();
                x--;
            });

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
    $(wrapper).submit(function (e) {
        e.preventDefault();
        var data = $('form').serializeFormJSON();
        var URL = $('form').attr("action");
        
        if (!$(".destination .error").exists()) {
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






        });
            