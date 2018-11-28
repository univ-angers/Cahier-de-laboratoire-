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

			'<div class="text-center w-100" style="margin:auto">' +
			'      	<button id= "buttonYDeco" class="btn btn-danger"> Oui </button>' +
			'      <button id= "buttonNDeco" class="btn btn-info"> Non </button>' +
				'</div>'+
			'</div>' +
			 '<div id="reponse" style="margin:12px;font-weight: bold;"></div>' +	
	'    </div>');

	$(".close").click(function() {
		$("#deconnexionPopup").fadeOut();
	});
	$("#buttonNDeco").click(function() {
		$("#deconnexionPopup").fadeOut();
	});

	
	
	$("#buttonYDeco").click(function() {
		window.location.href = "../deconnexion";
		
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