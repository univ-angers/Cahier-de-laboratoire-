
function getAllTags(){

	var data = {}
	//data["category"] = category;
	//data["name"] = name;
	$.ajax({
		type : "GET",
		url : "../tag/get",
		//data : JSON.stringify(data),
		data : data,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//print all tags
		

			for (var i = 0; i < data.length; i++){
			    var obj = data[i];
			    generateOneTag(obj[1], obj[0]);

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
	

//on rempli la liste des tags
	
}

function removeTag(id){
	
}

