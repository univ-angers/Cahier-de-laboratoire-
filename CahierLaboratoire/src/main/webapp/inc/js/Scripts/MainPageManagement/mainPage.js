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

			updateQuill();

			$("#buttonRechercherTag").click(function() {
				popupRechercheTag();
			});

			$("#buttonIdTag").click(function() {
				popupAddTag();
			});

			// ask in database for last billet avec var recherche

			$("#buttonDeco").click(function() {
				popupDeconnexion();
			});

			affcherBilletsAccueilAndLast();
			//addTagInBilletToDatabase(0,0);

		});
