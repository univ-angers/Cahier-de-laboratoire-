/**
 * 
 */


function popupDeconnexion(){

	var popupName= popupCreation("deconnexionPopup");

	$(".modal").css("background-color","rgb(0,0,0,0.4)");
	$(".modal-content").css("background-color","white");
	$(".modal-content").css("color","black");
	$(".modal-content").css("width","300px");
	$(".modal-content").empty();
	$(".modal-content").append(
			'<span class="close">&times;</span>'+
			'<p>Souhaitez vous vraiment vous déconnecter ? </p>'+
			'<form class="text-center w-100" style="margin:auto">'+
			'<div class="form-group ">' +
			'      	<button id= "buttonYDeco" class="btn btn-danger"> Oui </button>' +
			'      <button id= "buttonNDeco" class="btn btn-info"> Non </button>' +
				'</div>'+
			'</div>' +
			 '<div id="reponse" style="margin:12px;font-weight: bold;"></div>' +	
	'    </div></form>');

	$(".close").click(function() {
		$("#deconnexionPopup").fadeOut();
	});



//	$("#buttonRechercheTag").click(function() {
//
//		
//			$("#reponse").text("Resultat");
//			//deconne
//			$("#buttonRechercheTag").text("Close");
//			$("#buttonRechercheTag").click(function() {
//				$("#deconnexionPopup").fadeOut();
//			});
//			
//	
//	});


	$("#deconnexionPopup").fadeIn();

}