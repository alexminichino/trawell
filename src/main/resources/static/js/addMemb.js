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
                custom_alert("Message","User inserted correctly!");
                    
                location.reload()
                //$(".groupMembList").load( "/group/view?id="+idGroup+" .groupMembL");
            },
           error:function(request,textStatus,errorThrown){
            custom_alert("Message","Error, doesn't exist! ");
            
            }
        });
        
     });
});