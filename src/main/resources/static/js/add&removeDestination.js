/**
    * @author Alfieri Davide
    * this javascript add and remove a destination in a itinerary */

   $(document).ready(function(){
    var wrapper = $(".destination");
    var add_button = $(".add");
    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        x++;
        $(wrapper).append('<div> Destinazione:<input type = "text" name = "location" placeholder= "location"><br> Data:<input type = "date" name = "date"><br> Ora:<input type = "time" name = "hour"><br> Desccrizione:<input type = "text" name = "description" placeholder= "description"><br> <button style = "background-color:red;" class = "remove">Rimuovi la destinazione</button>' );
                     
    })

    $(wrapper).on("click",".remove",function(e){
        e.preventDefault();
        $(this).parent('div').remove();
        x--;
    });



    $.fn.serializeFormJSON = function () {

        var o = {};
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

    $("#target").submit(function (e) {
        e.preventDefault();
        var name = $("input[name='name']").val();
        var da = {name: name, destinations : []}

        $(".destination").each(function () {
            var location = $("input[name='location']").val();
            var date = $("input[name='date']").val();
            var description = $("textarea").val();

            var destination = {location: location, date: date, description: description}
            da['destinations'].push(destination);
        });
        
        console.log(da);

       $.ajax({
           dataType: "json",
           url:"/api/itinerary/add",
           headers:{
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
           },
           data:JSON.stringify(da),
           type:'POST',
           success:function(data){
               self.displayResults(data);
           },
          /* error:function(jqXHR,textStatus,errorThrown){
               showPopupError('Error','error:' + textStatus,'ok');
           }*/
       })
       
    });

});

         