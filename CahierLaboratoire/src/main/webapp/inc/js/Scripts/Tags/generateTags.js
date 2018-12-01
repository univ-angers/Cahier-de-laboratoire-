/**
 * 
 */


function generateTags(category,tagsList){
	tagsList.forEach(function(element){
		generateOneTag(category,element);
	});
}

function generateOneTag(category,tag){
	
    if (!document.getElementById("tagsList"+category)) {
    	generateCategory(category);
    }
    
	$("#tagsList"+category).append(
			'	<button class="badge badge-dark" style="border-color: #3B3B3B">'+tag+'</button> '
	);

}