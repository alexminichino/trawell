/**
    * @author Umberto Russomando
    * this javascript remove a group member from the view 
    * 
*/

$(document).ready(function(){
    $(".remove").click(function () {
        var wrapper = $(".groupMemb").filter($(this).parents());
        var idGroup = $(this).attr("id");
        var url = "/api/group/removeMember";
        var data = { idGroup : idGroup};
        alert("are you sure you want to eliminate thi member from this group?")
        $.ajax({
            dataType: "text",
            url:url,
            headers:{
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
            },
            data: JSON.stringify(data),
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