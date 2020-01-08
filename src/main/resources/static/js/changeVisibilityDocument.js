/**
    * @author Ruggiero Gaetano
    * this javascript  remove a document in a wallet 
    * 
*/

$(document).ready(function(){
    $(".changeVis").click(function () {
        var id = $(this).attr("id");
        var url = "/api/document/changevisibility/"+id;

        alert("are you sure you want to change this visibility of document?")
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
            },
           error:function(request,textStatus,errorThrown){
                alert("failed:");
            }
        });
        
     });
});