function getTagsInBillet(idB){



	console.log("JE SUIS DANS TAGINBILLET" +idB);

	$.ajax({
		type : "GET",
		url : "../billetTag/get",
		//data : JSON.stringify(data),
		data : {"idB": idB},
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//print all tags

			console.log(data);

			for (var i = 0; i < data.length; i++){
				var obj = data[i];
				generateTagInBillet(idB,obj["nomTag"]);

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
	console.log(idBillet + tag );
	$("#tag"+idBillet).append(
			'	<button type="button" class="badge badge-dark" style="color:white;border-color: #3B3B3B">'+tag+'</button> '
	);

	$('.badge').popover({
		trigger: 'focus',
		placement: 'bottom',
		html: true,
		title : '<span class="text-info"><strong>Tag menu :</strong></span>',
		content : '<button class="btn btn-dark" style="margin:2px">Remove</button><button class="btn btn-dark" style="margin:2px">Modify</button>'
	});
	
}

