/**
 * 
 */

function generateTags(category,tagsList){
	
	//Methode permettant de générer tous les tags sur le front (exemple à l'ouverture du site)
	tagsList.forEach(function(element){
		generateOneTag(category,element);
	});
}

function generateOneTag(category,tag, id){
	//Methode permettant de générer un tag et sa catégorie sur le front
    if (!document.getElementById("tagsList"+category)) {
    	generateCategory(category);
    }
    //On ajoute le tag à la catégorie
	$("#tagsList"+category).append(
			'<button id="tag'+id+'" class="badge badge-dark" style="border-color: #3B3B3B;margin:2px; width-max:100%">'+tag+'</button>'
	);
	
}