//Fonction qui fait appel au controleur pour récupérer les tags puis les générer
function getAllTags(){
	var data = {}
	$.ajax({
		type : "GET",
		url : "./tag/get", 
		data : data,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//print all tags
			for (var i = 0; i < data.length; i++){
				var obj = data[i];
				generateOneTag(obj[1], obj[0], obj[2]);
			}
			updatePopover();

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


