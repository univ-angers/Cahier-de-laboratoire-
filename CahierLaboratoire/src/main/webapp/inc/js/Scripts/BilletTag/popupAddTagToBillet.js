function addTagInBilletToDatabase(idB, nomTag, nomCategorie){





}

function popupAddTagToBillet(idB){
	var popupName= popupCreation("popupAddTagToBillet");

	$(".modal").css("background-color","rgb(0,0,0,0.4)");
	$(".modal-content").css("background-color","white");
	$(".modal-content").css("color","black");
	$(".modal-content").empty();
	$(".modal-content").append(
			'<span class="close">&times;</span>'+
			'<p>Ajouter le tag au billet : </p>'+
			'<div class="text-center w-100" style="margin:auto">'+
			'<div class="form-group ">' +
			'      	<input id="searchCategorie" type="search" placeholder="Catégorie" style="">' +
			'</div>' +
			
			'<div class="form-group ">' +
			'      	<input  id="searchNomTag" type="search" placeholder="Nom tag" style="">' +
				'</div>'+
			'</div>' +
			'<div class="form-group row" >' +
			'<div class="col-sm-10">'+
			'       <button id= "buttonAjouterTagInBillet" class="btn btn-info"> Ajouter le tag </button>' +
			'</div>'+
			'</div>'+
			 '<div id="reponse" style="margin:12px;font-weight: bold;"></div>' +	
	'    </div></div>');

	$(".close").click(function() {
		$("#popupAddTagToBillet").fadeOut();
	});
	
	$("#buttonAjouterTagInBillet").click(function() {
		//searchBilletsByName(  document.getElementById('searchNomTag').value)
		
		//addTagInBilletToDatabase( idB, document.getElementById('searchNomTag').value,document.getElementById('searchCategorie').value);
		var nomTag = document.getElementById('searchNomTag').value;
		var nomCategorie = document.getElementById('searchCategorie').value;
		
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "../billetTag/add",
			data : {
				"idB": idB,
				"nomTag" : nomTag,
				"nomCategorie" : nomCategorie
			},

			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("data.flag=" + typeof(data.flag));
				console.log
				if (data.flag==true){

					generateTagInBillet(idB,document.getElementById('searchNomTag').value);
					
					var popupName2 = "popupAjoutTagInBillet";

					popupSuccess(popupName,"Le tag a bien été inséré dans le billet");
					$("#"+popupName2).fadeTo("slow",1);
					$("#popupAddTagToBillet").fadeTo("slow",1);
					$("#popupAddTagToBillet").fadeOut();
					return true;
				}
				else{
					popupFailure(popupName,"Erreur");
					$("#"+popupName2).fadeTo("slow",1);
					$("#popupAddTagToBillet").fadeTo("slow",1);
					$("#popupAddTagToBillet").fadeOut();
					return false; 
				}
			},
			error : function(e) {
				return false;
			},
			done : function(e) {
				return true;
			}
		});
		
		

		

		
		
	});
	




	
	$("#popupAddTagToBillet").fadeIn();
}






