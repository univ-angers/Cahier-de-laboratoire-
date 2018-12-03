/**
 * 
 */
function updateBillet(id, content){
	
	//console.log("Sending ")
	$.ajax({
		url: "../billet/billetUpdate",
		type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
		success : function(data) {
			//console.log("success")
			return true;
		},
		error : function(e) {
			//console.log(e)
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

	$.post( "../billet/billetCreation")
	.done(function( data ) {
		id=data;
		$( ".loader" ).remove();

		$("#buttonAjoutBillet").prop("disabled",false);

		generateBillet(data,"");

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

					//console.log($("#billet"+id+"editor-container").find( ".ql-editor" ).html())

					updateBillet(id, $("#billet"+id+"editor-container").find( ".ql-editor" ).html());
				});

	});
}

function affcherBilletsAccueilAndLast(){
	$.ajax({
		url: "../billet/lastBillets",
		method: "POST",
		dataType: "json",
		contentType: 'application/json',
		success : function(data) {
			//console.log(data)
			
			$.each(data, function() {
				generateBillet(this.idB,this.text, this.idB);
				//getTagsInBillet(this.idB);
			});

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
						

						updateBillet(strId.replace("billet",""), $("#billet"+strId.replace("billet","")+"editor-container").find( ".ql-editor" ).html());
					});
			return true;
		},
		error : function(e) {
			//onsole.log(e)
			return false;
		},
		done : function(e) {
			return true;
		}
	});
	

}