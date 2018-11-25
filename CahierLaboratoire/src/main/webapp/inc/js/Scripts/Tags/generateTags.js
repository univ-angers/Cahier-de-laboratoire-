/**
 * 
 */


function generateTags(category,tagsList){
	tagsList.forEach(function(element){
		generateOneTag(category,element);
	});
}

function generateOneTag(category,tag){
	
	$("#tagsList"+category).append(
			'	<a href="" class="badge badge-dark">'+tag+'</a> '
	);

	
}