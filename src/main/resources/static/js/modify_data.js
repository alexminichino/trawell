$(document).ready(function(){
    $('#btn').on('click',function(){
        var URL = $('form').val("action");
        function dataCreator () {
            var unindexed_array = $('form').serializeArray();
            var indexed_array = {};
    
            $.map(unindexed_array, function(n, i){
                indexed_array[n['name']] = n['value'];
            });
    
            return indexed_array;
        }

        $.ajax({
            type:"POST",
            url:URL,
            dataType:"json",
            data: dataCreator(),
            success:function(data){
                alert("successo");
            }
        })

        
     });
        
});