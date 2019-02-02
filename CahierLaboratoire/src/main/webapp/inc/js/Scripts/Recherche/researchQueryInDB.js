//requete ajax effectuant une recherche au près du serveur
function searchBilletsByName( name){
	if (name.length > 0){
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./search/get",
			data : { "nomTag" : name },
		
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				
				if (data.length==0){
					$("#reponse").text("Aucun résultat trouvé. Veuillez réessayer");
					return false; 
				}
				else{
					$("#reponse").text("Resultats trouvés.");
					$("#billetsList").empty();
					for (var i = 0; i < data.length; i++){
					    var obj = data[i];
					    generateBillet( obj['idB'], obj["text"],obj["creation"] );
					}
					$("#buttonRechercheTag").text("Close");
					$("#buttonRechercheTag").click(function() {
						$("#rechercheTag").fadeOut();
					});
					
					updateQuill();
		        }
				return true;
			},
			error : function(e) {
				return false;
			},
			done : function(e) {
				return true;
			}
		});
		return true;
	}
	else{
		return false; 
	}
}

// Chercher les billets par plusieurs tags
function searchBilletsByNames(nom){

		if (nom.length==0){
			return false;
		}
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./search/getBillets",
			data : { "nomTag" : nom},
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				
				if (data.length==0){
					alert("Aucun résultat trouvé");
					return false; 
				}
				else{
				//	$("#reponse").text("Resultats trouvés.");
					$("#billetsList").empty();
					for (var i = 0; i < data.length; i++){
					    var obj = data[i];
					    generateBillet( obj['idB'], obj["text"],obj["creation"] );
					}
				/*	$("#buttonRechercheTag").text("Close");
					$("#buttonRechercheTag").click(function() {
						$("#rechercheTag").fadeOut();
					});*/
					
					updateQuill();
		        }
				return true;
			},
			error : function(e) {
				return false;
			},
			done : function(e) {
				return true;
			}
		});
		return true;
	}


