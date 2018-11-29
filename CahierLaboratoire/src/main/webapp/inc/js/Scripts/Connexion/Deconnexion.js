function Deconnexion(){
	/*
	 $.ajax({
         type : 'POST',
         url : "../deconnexion",
         contentType: "application/json; charset=utf-8",
         success : function(r) {
        	 console.log(r);
        	if (r){
        		popupSuccess(popupName,"Successfully Deconnected");
        		$("#"+popupName).fadeTo("slow",1);
        		$("#"+popupName).delay(1000).fadeTo("slow", 0);
        		
        		}
        	else {
        		popupFailure(popupName,"Error while deconnecting")
        		$("#"+popupName).fadeTo("slow",1);
        	}
         }
     });
     
*/
	
	window.location.href = "../deconnexion";
	
	

} 




