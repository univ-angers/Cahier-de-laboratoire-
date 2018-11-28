/**
 * 
 */
function updateBillet(id, content){
	$.ajax({
		url: "../billet/billetUpdate",
		  method: "GET",
		  data: { "idBillet" : id, "content" : content },
		  dataType: "json",
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
		}
	});
	
}

function createBillet(){
	
	var id=0;
	
	$.post( "../billet/billetCreation")
	.done(function( data ) {
		id=data;
		$( ".loader" ).remove();

		$("#buttonAjoutBillet").prop("disabled",false);

		generateBillet("billet"+data,"");

		$(".buttonQuill").click(
				function() {
					strId = this.id.substring(0,
							this.id.length - 6);// on récupère
				
					quill = AllQuillObjects.get(strId);
					$('.ql-toolbar',
							'#' + strId + 'content-container')
							.fadeIn();
		
					$(this).prop("disabled", true);
					$("#" + strId + "save").prop("disabled",
							false);
					quill.enable();
				});

		$(".saveButton").click(
				function() {
					strId = this.id.substring(0,
							this.id.length - 4);
					quill = AllQuillObjects.get(strId);
					quill.disable();

					$('.ql-toolbar',
							'#' + strId + 'content-container')
							.hide();
					// must save in database
					$(this).prop("disabled", true);
					$("#" + strId + "Modify").prop("disabled",false);
					
					console.log($("#billet"+id+"editor-container").find( ".ql-editor" ).html())
					
					updateBillet(id, $("#billet"+id+"editor-container").find( ".ql-editor" ).html());
				});

	});
}

function affcherBilletsAccueil(){
	
}