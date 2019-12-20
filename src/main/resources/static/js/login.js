$(document).ready(function() {
    $("form").validate({
      rules: {
        username : {
          required: true,
        },
        password: {
          required: true
        }
      }, messages: {
        username: "Inserisci la login",
        password:  "Inserisci una password password"    
    }
    });
  });