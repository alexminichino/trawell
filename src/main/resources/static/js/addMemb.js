/**
    * @author Umberto Russomando
    * this javascript remove a group member from the view 
    * 
*/

$(document).ready(function(){
    $(".addMemb").click(function () {
        var username = $("#nametoget").val();
        var idGroup = $(".addMemb").attr("id");
        var url = "/api/group/addMember/"+username+"/"+idGroup;

        alert("are you sure you want to add this member in this group?");
        $.ajax({
            dataType: "text",
            url:url,
            headers:{
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
            },
            type:'POST',
            success:function(data){
                $('#exampleModalCenter').modal('hide');
                location.reload()
                //$(".groupMembList").load( "/group/view?id="+idGroup+" .groupMemb");
            },
           error:function(request,textStatus,errorThrown){
            }
        });
        
     });
});