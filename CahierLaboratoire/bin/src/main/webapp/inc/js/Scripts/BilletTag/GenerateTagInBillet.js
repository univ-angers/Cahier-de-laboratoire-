function getTagsInBillet(idB){

	$.ajax({
			type : "GET",
			url : "./billetTag/get", //On appelle le controleur à l'adresse /billetTag/get
			data : {"idB": idB},
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				//On génère les tags récupérés dans les billets 
				for (var i = 0; i < data.length; i++){
				    var obj = data[i];
				   generateTagInBillet(idB,obj["nomTag"],obj["idT"]);
				}
				updatePopoverOnBillet();
				
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


function generateTagInBillet(idBillet,tag,id){
	//Pour ajouter dans le front
	$("#tag"+idBillet).append(
			'	<button  id="billetTag'+id+'" type="button" class="badge badge-dark" style="color:white;border-color: #3B3B3B">'+tag+'</button> '
	);
	
	
}


