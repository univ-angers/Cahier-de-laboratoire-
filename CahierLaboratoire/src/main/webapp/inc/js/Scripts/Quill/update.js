0//fonctionnalité permettant d'affilier les différentes fonctions sur les boutons d'un billet
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
				$(this).prop("disabled", true);
				$("#" + strId + "Modify").prop("disabled",false);
				
				updateBillet(strId.replace('billet',''), $("#billet"+strId.replace('billet','')+"editor-container").find( ".ql-editor" ).html());
			});
	
	$(".printButtonMono").click(
			function() {
				strId = this.id.substring(0,
						this.id.length - 14);
				exportPDFMono(strId);
			});
	

	$(".removeButton").click(
			function() {
				
				strId = this.id.substring(0,this.id.length - 6);// on récupère
				//removing in data base 
				removeBillet(strId.replace('billet',''));
				//removing from ui
				$("#"+strId).remove();
			});
	
	
	$(".addTagToBilletButton").click(

			function() {
				strId = this.id.substring(0,this.id.length - 14);// on récupère
				popupAddTagToBillet(strId.replace('billet',''));

			});

	

}