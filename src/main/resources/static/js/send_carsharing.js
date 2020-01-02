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
         * the function makes the ajax call to server to modify carsharing's data
         *  */
        $("#target").submit(function (e) {
            e.preventDefault();
            var da = $("#target").serializeFormJSON();
            $.ajax({
                dataType: "json",
                url:"/api/carsharing/add",
                headers:{
                 'Accept' : 'application/json',
                 'Content-Type' : 'application/json'
                },
                data:JSON.stringify(da),
                type:'POST',
                success:function(data){
                    alert("successo");
                },
                error:function(jqXHR,textStatus,errorThrown){
                    alert("failed");
                }
            })
        });
    });
    