/**
 * 
 */
//Requete de modifiction, suppression et creation de billet
function removeBillet(id){
	$.ajax({
		url: "./billet/billetRemove",
		type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
		success : function(data) {
			return true;
		},
		error : function(e) {
			console.log(e)
			return false;
		},
		done : function(e) {
			return true;
		},
		data:  JSON.stringify({ idBillet : id}),
	});

}
function updateBillet(id, content){
	
	$.ajax({
		url: "./billet/billetUpdate",
		type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
		success : function(data) {
			return true;
		},
		error : function(e) {
			return false;
		},
		done : function(e) {
			return true;
		},
		data:  JSON.stringify({ idBillet : id, content : content }),
	});

}

function createBillet(){

	var id=0;

	$.post( "./billet/billetCreation")
	.done(function( data ) {
		id=data;
		$( ".loader" ).remove();

		$("#buttonAjoutBillet").prop("disabled",false);

		generateBillet(data,"");

		updateQuill();


	});
}

function affcherBilletsAccueilAndLast(){
	$.ajax({
		url: "./billet/lastBillets",
		method: "POST",
		dataType: "json",
		contentType: 'application/json',
		success : function(data) {
			
			$.each(data, function() {
				generateBillet(this.idB,this.text, this.idB);
			});
			
			updateQuill();
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