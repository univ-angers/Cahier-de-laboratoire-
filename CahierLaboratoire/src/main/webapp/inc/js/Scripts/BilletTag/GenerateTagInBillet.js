function getTagsInBillet(idB){

		$.ajax({
			type : "GET",
			url : "./billetTag/get",
			data : {"idB": idB},
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				//print all tags
				 
				for (var i = 0; i < data.length; i++){
				    var obj = data[i];
				   generateTagInBillet(idB,obj["nomTag"],obj["idT"]);
	
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




function generateTagInBillet(idBillet,tag,id){
	$("#tag"+idBillet).append(
			'	<button  id="billetTag'+id+'" type="button" class="badge badge-dark" style="color:white;border-color: #3B3B3B">'+tag+'</button> '
	);
	
	
}


