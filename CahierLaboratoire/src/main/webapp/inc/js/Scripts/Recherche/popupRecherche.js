/**
 * Création de la popup de recherche
 */


function popupRechercheTag(){

	var popupName= popupCreation("rechercheTag");
	var data = {}
	var lt = new Array();
	$.ajax({
		type : "GET",
		url : "./tag/get", 
		data : data,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//print all tags
			for (var i = 0; i < data.length; i++){
				lt.push(data[i]['0']);
			}
			var liste = '<select id="searchNomTag" type="search" placeholder="Nom tag" style="" MULTIPLE>';
			lt.forEach(function(element) {
				liste += '<option value="'+element+'">'+element+'</option>';
			});
			liste += '</select>';
			$(".modal").css("background-color","rgb(0,0,0,0.4)");
			$(".modal-content").css("background-color","white");
			$(".modal-content").css("color","black");
			$(".modal-content").empty();
			$(".modal-content").append(
					'<span class="close">&times;</span>'+
					'<p>Rerchercher Tag : </p>'+
					'<div class="text-center w-100" style="margin:auto">'+
					'<div class="form-group ">' +
					liste  +
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


			return true;
		},
		error : function(e) {
			return false;
		},
		done : function(e) {
			return true;
		}
	});
	
}

