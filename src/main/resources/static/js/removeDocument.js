/**
* @author Ruggiero Gaetano
* this javascript  remove a document in a wallet 
* 
*/

function remove(x) {
    var wrapper = $("tr").filter($(x).parents());
    var id = x.id;
    var url = "/api/document/eliminate/"+id;

    alert("are you sure you want to eliminate this document?")
    $.ajax({
        dataType: "text",
        url:url,
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
            alert("failed");
        }
    });

};