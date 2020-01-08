/**
    * @author Alfieri Davide
    * this javascript add and remove a destination in a itinerary */

   $(document).ready(function(){
    var wrapper = $(".destinations");
    var add_button = $(".add");
    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        x++;
        $(wrapper).append('<div class = "destination"> Destinazione:<input type = "text" name = "location" placeholder= "location"><br> Data e Ora:<input type = "datetime-local" name = "date"><br> Desccrizione:<textarea name = "description" placeholder= "description"></textarea><br> <button style = "background-color:red;" class = "remove">Rimuovi la destinazione</button>' );
                     
    })

    $(wrapper).on("click",".remove",function(e){
        e.preventDefault();
        $(this).parent('div').remove();
        x--;
    });
    
    $.fn.exists = function () {
        return this.length !== 0;
    }

    $("#target").submit(function (e) {
        e.preventDefault();
        var name = $("input[name='name']").val();
        var da = {name: name, destinations : []}

        $(".destination").each(function () {
            var div = $(this);
            var location = div.find("input[name='location']").val();
            var date = div.find("input[name='date']").val();
            var description = div.find("textarea").val();

            var destination = {location: location, date: date, description: description}
            da['destinations'].push(destination);
        });

       $.ajax({
           dataType: "text",
           url:"/api/itinerary/add",
           headers:{
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
           },
           data:JSON.stringify(da),
           type:'POST',
           success:function(data){
               alert("success");
           },
          error:function(request,textStatus,errorThrown){
               alert("failed:");
           }
       })
       
    });

});

         