var AllQuillObjects = new Map();

$(document).ready(
		function() {

			
		getAllTags();
		
			$("#buttonAjoutBillet").click(
					function() {
						//creer un billet bd
						$("#buttonAjoutBillet").prop("disabled",true);
						$("#billetsList").append('<div class="loader"></div>');

						createBillet();
					});

			$(".removeButton").click(
					function() {
						
						strId = this.id.substring(0,this.id.length - 6);
						// on récupère
						console.log("remove pressed ");
						
						//removing in data base 
						removeBillet(strId.charAt(strId.length - 1));
						
						//removing from ui
						$("#"+strId).remove();
						
					});
			
			$(".buttonQuill").click(
					function() {
						strId = this.id.substring(0,
								this.id.length - 6);// on récupère
						// l'id commun
						// quill=quillEnable(strId);
						quill = AllQuillObjects.get(strId);
						$('.ql-toolbar',
								'#' + strId + 'content-container')
								.fadeIn();
						// quill retourne l'objet et on l'ajout à la
						// liste pour manipuler l'objet quand on
						// veut
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
						$("#" + strId + "Modify").prop("disabled",
								false);
					});

			$("#buttonRechercherTag").click(function() {
				popupRechercheTag();
				console.log("test");
			});

			$("#buttonIdTag").click(function() {
				popupAddTag();
			});

			// ask in database for last billet avec var recherche

			$("#buttonDeco").click(function() {
				popupDeconnexion();
			});

			affcherBilletsAccueilAndLast();


		});
