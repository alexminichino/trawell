/**
    * @author Ruggiero Gaetano
    * this javascript  change visibility a document in a wallet 
    * 
*/

function changeVis(x) {
    var wrapper = $("tr").filter($(x).parents());
    var id = x.id;
    var url = "/api/document/changeVisibility/"+id;

    custom_alert("Message","are you sure you want to change this visibility of document?")
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
            if ($("table").filter($(wrapper).parents()).attr("id") == "User") {

                $("#Public tbody").append("<tr>" + wrapper.html() + "</tr>");

            } else {

                $("#User tbody").append("<tr>" + wrapper.html() + "</tr>");

            }
            wrapper.remove();
        },
        error: function (request, textStatus, errorThrown) {
            custom_alert("Message","You're not authorized");
        }
    });

};
