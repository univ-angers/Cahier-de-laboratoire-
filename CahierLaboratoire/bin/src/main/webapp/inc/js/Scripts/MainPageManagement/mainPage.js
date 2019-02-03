
//script de la page principale

//map contenant tous les objets quills
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

			$("#printAllButton").click(function() {
				exportPDF();
			});

				
				
			affcherBilletsAccueilAndLast();


		});
