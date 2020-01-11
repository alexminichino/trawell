/**
    * @author Gaetano Ruggiero
    * this javascript remove a group from the view where the user is owner
    * 
*/

function remove(x) {
    var wrapper = $("tr").filter($(x).parents());
    var id = x.id;
    var url = "/api/group/eliminate/" + id;
    alert("are you sure you want to eliminate this Group?")
    $.ajax({
        dataType: "text",
        url: url,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        success: function (data) {
            alert("success");
            wrapper.remove();
        },
        error: function (request, textStatus, errorThrown) {
            alert("failed:");
        }
    });

};