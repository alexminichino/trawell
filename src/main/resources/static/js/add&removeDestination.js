/**
    * @author Alfieri Davide
    * this javascript add and remove a destination in a itinerary */

   $(document).ready(function(){
    var wrapper = $(".destinations");
    var add_button = $("#add");
    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        x++;
        $(wrapper).append('<div  class = "destination"> <div class="input-group mb-2"> <div class="input-group-append"> <span class="input-group-text"><i class="fas fa-city"></i></span> </div> <input type = "text" name = "location" placeholder= "Destinazione" class="form-control input_user"> </div> <div class="input-group mb-2"> <div class="input-group-append"> <span class="input-group-text"><i class="fas fa-clock"></i></span> </div> <input type = "datetime-local" name = "date" placeholder= "Data e ora" class="form-control input_user">  </div> <div class="input-group mb-2"> <div class="input-group-append"> <span class="input-group-text"><i class="fas fa-pen"></i></span> </div> <textarea name="description" placeholder="Descrizione" class = "form-control input_user"></textarea> </div><br> <input type = "button" value = "Rimuovi la destinazione" id="remove" class="btn my_btn"><br>');
                     
    });

    $(wrapper).on("click","#remove",function(e){
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
               custom_alert("Message","success");
               window.location.href='/';
           },
          error:function(request,textStatus,errorThrown){
               custom_alert("Message","failed:");
           }
       })
       
    });

});

         