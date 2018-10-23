//get functions from scripts
$.getScript("inc/js/Scripts/Popup/Popup.js");
//
$.getScript("inc/js/Scripts/Connexion/Connexion.js");
//
$.getScript("inc/js/Scripts/LoginPageManagement/ForgotPassword.js");
//

$(document).ready(function(){
    //generation de la popup
    var popupName= popupCreation("loginPopup");//return name

    //gestion de la connexion
//    $('#form-connexion').submit(function(e){
//        e.preventDefault();
//
//        //appel des méthodes de connexion
//        if(result=="succes"){
//            popupSuccess(popupName,"Successfully Connected");
//            $("#"+popupName).fadeTo("slow",1);
//            $("#"+popupName).delay(1000).fadeTo("slow", 0);
//        }
//        else {
//            popupFailure(popupName,"Password or Identifant incorrect")
//            $("#"+popupName).fadeTo("slow",1);
//        }
//
//    });
    if($('body').attr('datacustomTest')=="succes"){
      popupSuccess(popupName,"Successfully Connected");
      $("#"+popupName).fadeTo("slow",1);
      $("#"+popupName).delay(1000).fadeTo("slow", 0);
    }
    else {
      popupFailure(popupName,"Password or Identifant incorrect")
      $("#"+popupName).fadeTo("slow",1);
    }

    $("#forgotPassword").click(function (e) {
        e.preventDefault();
        forgotPopup(popupName);
    });


});
