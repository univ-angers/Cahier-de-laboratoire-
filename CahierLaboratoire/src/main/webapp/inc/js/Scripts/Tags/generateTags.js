/**
 * 
 */


function generateTags(category,tagsList){
	tagsList.forEach(function(element){
		generateOneTag(category,element);
	});
}

function generateOneTag(category,tag, id){
	
    if (!document.getElementById("tagsList"+category)) {
    	generateCategory(category);
    }
    
	$("#tagsList"+category).append(
			'<button id="tag'+id+'" class="badge badge-dark" style="border-color: #3B3B3B;margin:2px; width-max:100%">'+tag+'</button>'
	);
	
}