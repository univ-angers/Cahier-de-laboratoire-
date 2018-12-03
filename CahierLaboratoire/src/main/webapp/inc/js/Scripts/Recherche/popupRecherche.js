/**
 * 
 */

function popupRechercheTag(){

	var popupName= popupCreation("rechercheTag");

	$(".modal").css("background-color","rgb(0,0,0,0.4)");
	$(".modal-content").css("background-color","white");
	$(".modal-content").css("color","black");
	$(".modal-content").empty();
	$(".modal-content").append(
			'<span class="close">&times;</span>'+
			'<p>Rerchercher Tag : </p>'+
			'<div class="text-center w-100" style="margin:auto">'+
			
			/*
			'<div class="form-group ">' +
			'      	<input id="searchCategorie" type="search" placeholder="Catégorie" style="">' +
			'</div>' +
			*/
			'<div class="form-group ">' +
			'      	<input  id="searchNomTag" type="search" placeholder="Nom tag" style="">' +
				'</div>'+
			'</div>' +
			'<div class="form-group row" >' +
			'<div class="col-sm-10">'+
			'       <button id= "buttonRechercheTag" class="btn btn-info"> Rechercher </button>' +
			'</div>'+
			'</div>'+
			 '<div id="reponse" style="margin:12px;font-weight: bold;"></div>' +	
	'    </div></div>');

	$(".close").click(function() {
		$("#rechercheTag").fadeOut();
	});



	$("#buttonRechercheTag").click(function() {
		
		
		searchBilletsByName(  document.getElementById('searchNomTag').value);


		
	
	});
	


	



	
	$("#rechercheTag").fadeIn();

}

