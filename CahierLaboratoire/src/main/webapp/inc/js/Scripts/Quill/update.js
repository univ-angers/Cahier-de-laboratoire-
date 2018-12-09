			
function updateQuill(){
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
				//console.log(strId.replace('billet',''));
				
				//console.log($("#billet"+id+"editor-container").find( ".ql-editor" ).html())
				
				updateBillet(strId.replace('billet',''), $("#billet"+strId.replace('billet','')+"editor-container").find( ".ql-editor" ).html());
			});
	

	$(".removeButton").click(
			function() {
				
				strId = this.id.substring(0,this.id.length - 6);// on récupère
				console.log("remove pressed "+strId);
				//removing in data base 
				removeBillet(strId.replace('billet',''));
				//removing from ui
				$("#"+strId).remove();
			});
	
	
	$(".addTagToBilletButton").click(

			function() {
				strId = this.id.substring(0,this.id.length - 14);// on récupère
				console.log("+ pressed "+strId);
				popupAddTagToBillet(strId.replace('billet',''));



			});

	

}