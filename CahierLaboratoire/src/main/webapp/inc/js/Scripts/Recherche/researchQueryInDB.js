function searchBilletsByName( name){
	if (name.length > 0){
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./search/get",
			data : {
					"nomTag" : name
	        },
		
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				
				if (data.length==0){
					$("#reponse").text("Aucun résultat trouvé. Veuillez réessayer");
					return false; 
				}
				else{
					//console.log(data);
					$("#reponse").text("Resultats trouvés.");
					$("#billetsList").empty();
					for (var i = 0; i < data.length; i++){
					    var obj = data[i];
					    console.log(obj);
					    generateBillet( obj['idB'], obj["text"] );
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

