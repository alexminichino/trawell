/**
    * @author Gaetano Ruggiero
    * this javascript remove a group from the view where the user is owner
    * 
*/

function remove(x) {
    var wrapper = $("tr").filter($(x).parents());
    var id = x.id;
    var url = "/api/group/eliminate/" + id;
    custom_alert("Message","are you sure you want to eliminate this Group?")
    $.ajax({
        dataType: "text",
        url: url,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        success: function (data) {
            custom_alert("Message","success");
            wrapper.remove();
        },
        error: function (request, textStatus, errorThrown) {
            custom_alert("Message","failed:");
        }
    });

};