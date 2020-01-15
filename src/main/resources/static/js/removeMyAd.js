/**
    * @author Mario Paone
    * this javascript remove an Ad
    * 
*/

   $(document).ready(function () {
    $(".remove").click(function () {
        var idad = $(this).attr("id");
        custom_alert("Message","Are you sure you want to eliminate this ad?")
        $.ajax({
            type: 'POST',
            url: "/agency/deleteAd",
            data: { id: idad },
            async: false,
            success: function (data) {
                custom_alert("Message","Ad successfully removed");
                location.reload();
            },
            error: function (request, textStatus, errorThrown) {
                custom_alert("Message","Failed");
                
            }
        });
    })
});