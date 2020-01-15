/**
    * @author Alfieri Davide
    * this javascript add and remove a destination in a itinerary */

   $(document).ready(function(){
    $(".remove").click(function () {
        var wrapper = $("tr").filter($(this).parents());
        var id = $(this).attr("id");
        var url = "/api/carsharing/eliminate/"+id;

        custom_alert("Message","are you sure you want to eliminate this carsharing ad?")
        $.ajax({
            dataType: "text",
            url:url,
            headers:{
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
            },
            type:'POST',
            success:function(data){
                custom_alert("Message","success");
                wrapper.remove();
            },
           error:function(request,textStatus,errorThrown){
                custom_alert("Message","failed:");
            }
        });
        
     });
});
