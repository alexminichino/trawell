/**
    * @author Alfieri Davide
    * this javascript delete an itinerary permanently */

$(document).ready(function(){
    $(".remove").click(function () {
        var wrapper = $("tr").filter($(this).parents());
        var id = $(this).attr("id");
        var url = "/api/itinerary/eliminate/"+id;

        alert("are you sure you want to eliminate this itinerary?")
        $.ajax({
            dataType: "text",
            url:url,
            headers:{
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
            },
            type:'POST',
            success:function(data){
                alert("success");
                wrapper.remove();
            },
           error:function(request,textStatus,errorThrown){
                alert("failed:");
            }
        });
        
     });
});

