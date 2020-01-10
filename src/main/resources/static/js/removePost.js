/**
    * @author Umberto Russomando
    * this javascript remove a post from the view 
    * 
*/

   $(document).ready(function(){
    $(".remove").click(function () {
        var wrapper = $(".post").filter($(this).parents());
        var id = $(this).attr("id");
        var url = "/api/post/delete/"+id;
        alert("are you sure you want to eliminate this post?")
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