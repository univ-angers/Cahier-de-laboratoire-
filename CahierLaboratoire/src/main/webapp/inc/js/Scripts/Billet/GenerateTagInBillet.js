function getTagsInBillet(){
	console.log("get all tag");
	

	var data = {}
	$.ajax({
		type : "GET",
		url : "../tag/getTagBillet",
		//data : JSON.stringify(data),
		data : data,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//print all tags
			
			console.log(data);

			for (var i = 0; i < data.length; i++){
			    var obj = data[i];
			    generateTagInBillet(obj["idB"], obj["nomTag"]);

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


}


function generateTagInBillet(idBillet,tag){
	$("tag"+idBillet).append(
			'	<a href="" class="badge badge-dark">'+tag+'</a> '
	);
			
}
