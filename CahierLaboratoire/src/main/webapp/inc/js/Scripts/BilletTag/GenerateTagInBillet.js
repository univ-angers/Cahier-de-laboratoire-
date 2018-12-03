function getTagsInBillet(idB){


		$.ajax({
			type : "GET",
			url : "../billetTag/get",
			//data : JSON.stringify(data),
			data : {"idB": idB},
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				//print all tags
				
				//console.log(data);
	
				for (var i = 0; i < data.length; i++){
				    var obj = data[i];
				    generateTagInBillet(idB,obj["nomTag"]);
				    //console.log("JE GENERE LE TAG "+obj["nomTag"]+"POUR LE BILLET: "+idB );
	
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

function addTagInBilletToDatabase(idB, idT){

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "../billetTag/add",
			data : {
					"idB": idB,
					"idT" : idT
	        },
		
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				return true;
			},
			error : function(e) {
				return false;
			},
			done : function(e) {
				return true;
			}
		});
		return true;


}


function generateTagInBillet(idBillet,tag){
	//console.log(idBillet + tag );
	$("#tag"+idBillet).append(
			'	<a href="" class="badge badge-dark">'+tag+'</a> '
	);
	
}
