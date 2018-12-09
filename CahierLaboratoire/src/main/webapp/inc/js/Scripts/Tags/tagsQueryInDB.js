/**
 * 
 */

function removeTag(id){
	console.log("RemoveTag called");
	$.ajax({
		url: "./tag/remove",
		type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
		success : function(data) {
			
			console.log("success");
			return true;
		},
		error : function(e) {
			console.log(e)
			return false;
		},
		done : function(e) {
			return true;
		},
		data:  JSON.stringify({ idTag : id}),
	});
}


function updateTag(id,content){
	$.ajax({
		url: "./tag/update",
		type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
		success : function(data) {
			console.log("success")
			return true;
		},
		error : function(e) {
			console.log(e)
			return false;
		},
		done : function(e) {
			return true;
		},
		data:  JSON.stringify({ idTag : id, content : content}),
	});
}