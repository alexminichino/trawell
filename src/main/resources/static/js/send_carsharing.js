/**
	 * @author Alfieri Davide
	 * the javascript allows for the creation of car sharing to be dealt asynchronously via 
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
            if(! $("#target").valid()) return false;
            var da = $("#target").serializeFormJSON();
            $.ajax({
                dataType: "text",
                url:"/api/carsharing/add",
                headers:{
                 'Accept' : 'application/json',
                 'Content-Type' : 'application/json'
                },
                data:JSON.stringify(da),
                type:'POST',
                success:function(data){
                    custom_alert("Message","Car sharing post inserted correctly!");
                    setTimeout(function(){ window.location.href='/carsharing/list-view'; }, 3000);
                },
                error:function(jqXHR,textStatus,errorThrown){
                    custom_alert("Message","Error, conctact administrators!");
                }
            })
        });
    });
    