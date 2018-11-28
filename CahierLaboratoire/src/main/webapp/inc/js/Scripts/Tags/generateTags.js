/**
 * 
 */


function generateTags(category,tagsList){
	tagsList.forEach(function(element){
		generateOneTag(category,element);
	});
}

function generateOneTag(category,tag){
	console.log("je genere un tag");
	
	
    if (!document.getElementById("tagsList"+category)) {
    	generateCategory(category);
    }
    
	$("#tagsList"+category).append(
			'	<a href="" class="badge badge-dark">'+tag+'</a> '
	);

	
}