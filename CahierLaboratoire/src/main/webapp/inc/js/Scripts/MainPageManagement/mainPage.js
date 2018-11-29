var AllQuillObjects = new Map();

$(document).ready(function() {
	
	(function(){
		getAllTags();
	})()
	
	


	$("#buttonRechercherTag").click(function() {
	popupRechercheTag();
	});
	
	$("#buttonIdTag").click(function() {
		popupAddTag();

	});
	
	//ask in database for last billet avec var recherche
	generateBillet('billet1','Sample');
	generateBillet('billet2','Sample');
	
	
	$( ".buttonQuill" ).click(function() {
		strId=this.id.substring(0, this.id.length - 6);//on récupère l'id commun
		//quill=quillEnable(strId);
		quill=AllQuillObjects.get(strId);
		$('.ql-toolbar','#'+strId+'content-container').fadeIn();
		//quill retourne l'objet et on l'ajout à la liste pour manipuler l'objet quand on veut
		$(this).prop("disabled",true);
		$("#"+strId+"save").prop("disabled",false);
		quill.enable();
		});
	
	$( ".saveButton" ).click(function() {
		strId=this.id.substring(0, this.id.length - 4);
		quill=AllQuillObjects.get(strId);
		quill.disable();

		$('.ql-toolbar','#'+strId+'content-container').hide();
		//must save in database
		$(this).prop("disabled",true);
		$("#"+strId+"Modify").prop("disabled",false);
	});
	
	$( "#buttonDeco" ).click(function() {
		popupDeconnexion();
	});
	
	
	/*
	var array1 = ['val1', 'val2', 'val3', 'val2', 'val3', 'val2', 'val3', 'val2', 'val3', 'val2', 'val3', 'val2', 'val3'];
	
	generateCategory("Category_1");
	generateTags("Category_1",array1);
	
	generateCategory("Category_2");
	generateTags("Category_2",array1);
	
	generateCategory("Category_3");
	generateTags("Category_3",array1);
	
	generateCategory("Category_4");
	generateTags("Category_4",array1);
	
	generateCategory("Category_5");
	generateTags("Category_5",array1);
	
	*/

	
	
});
