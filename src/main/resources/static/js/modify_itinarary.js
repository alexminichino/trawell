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
        $("#clickme").click(function (e) {
            var da = $("#target").serializeFormJSON();
            $.ajax({
                dataType: "json",
                url:"/api/itinerary/change",
                headers:{
                 'Accept' : 'application/json',
                 'Content-Type' : 'application/json'
                },
                data:da,
                type:'POST',
                success:function(data){
                    self.displayResults(data);
                },
                error:function(jqXHR,textStatus,errorThrown){
                    showPopupError('Error','error:' + textStatus,'ok');
                }
            })
        });
    });
    