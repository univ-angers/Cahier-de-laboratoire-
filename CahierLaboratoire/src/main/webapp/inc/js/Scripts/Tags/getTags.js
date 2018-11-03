/**
 * 
 */
function getAllTags(){

	var data = {}
	data["category"] = category;
	data["name"] = name;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "url",
		data : JSON.stringify(data),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//print all tags
			return true;
		},
		error : function(e) {
			return false;
		},
		done : function(e) {
			return true;
		}
	});
	
	
//on rempli la liste des tags

}
