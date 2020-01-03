/**
    * @author Alfieri Davide
    * this javascript add and remove a destination in a itinerary */

   $(document).ready(function(){
    $(".remove").click(function () {
        var wrapper = $("tr").filter($(this).parents());
        var id = $(this).attr("id");
        var url = "/api/carsharing/eliminate/"+id;

        alert("are you sure you want to eliminate this carsharing ad?")
        $.ajax({
            dataType: "json",
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
