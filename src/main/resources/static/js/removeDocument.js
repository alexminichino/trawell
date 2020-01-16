/**
* @author Ruggiero Gaetano
* this javascript  remove a document in a wallet 
* 
*/

function remove(x) {
    var wrapper = $("tr").filter($(x).parents());
    var id = x.id;
    var url = "/api/document/eliminate/"+id;

    custom_alert("Message","are you sure you want to eliminate this document?")
    $.ajax({
        dataType: "text",
        url:url,
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
            custom_alert("Message","You're not authorized");
        }
    });

};