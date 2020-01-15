/**
    * @author Umberto Russomando
    * this javascript remove a group member from the view 
    * 
*/

$(document).ready(function(){
    $(".removeMemb").click(function () {
        var wrapper = $(".groupMemb").filter($(this).parents());
        var idGroup = $("tbody").filter($(this).parents()).attr("id");
        var idUser = $(this).attr("id");

        var url = "/api/group/removeMember/"+idGroup+"/"+idUser;

        alert("are you sure you want to eliminate this member from this group?");
        $.ajax({
            dataType: "text",
            url:url,
            headers:{
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
            },
            type:'POST',
            success:function(data){
                wrapper.remove();
            },
           error:function(request,textStatus,errorThrown){
            }
        });
        
     });
});