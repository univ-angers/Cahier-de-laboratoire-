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
		data:  JSON.stringify({ idBillet : id, idUtilisateur : $("#utilisateur").text() }),
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
		data:  JSON.stringify({ idBillet : id, content : content, idUtilisateur : $("#utilisateur").text() }),
	});

}
function createBillet(){
	 
	var id=0;
	var d = new Date();
	d =  d.getDate() + '/' + (d.getMonth()+1) + '/' + d.getFullYear();	
		$.ajax({
			url: "./billet/billetCreation",
			type: 'POST',
	        dataType: 'json',
	        contentType: 'application/json',
			success : function(data) {
				return true;
			},
			error : function(e) {
				return false;
			},
			done : function(data) {
				
				return true;
			},
			data:  JSON.stringify({ idUtilisateur : $("#utilisateur").text()}),
		});
		
		
		$.post( "./billet/billetCreation")
		.done(function( data ) {
			id=data;
			$( ".loader" ).remove();

			$("#buttonAjoutBillet").prop("disabled",false);
			createBillets(data,d);

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
	
				generateBillet(this.idB,this.text,this.creation);
				
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
