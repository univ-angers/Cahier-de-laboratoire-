

$(document).ready(function(){
    $('#form-connexion').submit(function(e){
        e.preventDefault();
         $.ajax({
              type: $(this).attr('method'),
            url: $(this).attr('action'),
          data: $(this).serialize(),
        }).done(function(r){

         if(r.ok){

         }else{

         }


         }).fail("error connecting");

        console.log("test");

    });
});
