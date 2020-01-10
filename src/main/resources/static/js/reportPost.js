/**
    * @author Umberto Russomando
    * this javascript report a post to an admin
    * 
*/

$(document).ready(function(){
    $(".report").click(function () {
        var id = $(this).attr("id");
        var url = "/api/post/report/"+id;
        alert("are you sure you want to report this post?")
        $.ajax({
            dataType: "text",
            url:url,
            headers:{
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
            },
            type:'POST',
            success:function(data){
                alert("Thank you for taking the time to report a post. An email has been sent to our customer service's email. Please wait for an admin to answer you.");
            },
           error:function(request,textStatus,errorThrown){
                alert("failed:");
            }
        });
        
     });
});