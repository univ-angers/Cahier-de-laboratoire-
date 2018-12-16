$(document).ready(function(){
    //generation de la popup une fois la page de login créee
    var popupName= popupCreation("loginPopup");//return name
   
    $("#forgotPassword").click(function (e) {
        e.preventDefault();
        forgotPopup(popupName);
    });
    //si la variable  datacustomTest n'est pas success on affiche une popup de fail
    if($('body').attr('datacustomTest')!="succes"){
      popupFailure(popupName,"Password or Identifant incorrect")
      $("#"+popupName).fadeTo("slow",1);
    }

   


});
