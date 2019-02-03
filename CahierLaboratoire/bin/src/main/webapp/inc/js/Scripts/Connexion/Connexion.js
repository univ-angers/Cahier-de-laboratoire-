$.getScript("inc/js/Scripts/Popup/Popup.js");
//connexion via post
function connexion(popupName,name, mdp){
	 $.ajax({
         type : 'POST',
         url : "connexion",
         data : {email:name,motdepasse:mdp},
         dataType : "text",                        
         success : function(r) {
        	 console.log(r);
        	if (r){
        		popupSuccess(popupName,"Successfully Connected");
        		$("#"+popupName).fadeTo("slow",1);
        		$("#"+popupName).delay(1000).fadeTo("slow", 0);
        		
        		}
        	else {
        		popupFailure(popupName,"Password or Identifant incorrect")
        		$("#"+popupName).fadeTo("slow",1);
        	}
         }
     });
}

