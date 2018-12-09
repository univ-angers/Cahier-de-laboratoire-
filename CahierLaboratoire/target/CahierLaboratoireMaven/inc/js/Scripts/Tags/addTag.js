/**
 * 
 */

function addTagToDatabase(category, name){

	if(category.length > 0 && name.length > 0){
			
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./tag/add",
			data : {
					"categorie": category,
					"nomTag" : name
	        },
		
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				return true;
			},
			error : function(e) {
				return false;
			},
			done : function(e) {
				return true;
			}
		});
		return true;
	}else{
		return false
	}
}
function addTagInProjectDiv(tagname){
	//on recherche les projets lié a un tag passé en paramètre
	//on affiche ce tag dans la div concerné par ce tag
}
function popupAddTag(){

	var popupName= popupCreation("addtagpopup");

	$(".modal").css("background-color","rgb(0,0,0,0.4)");
	$(".modal-content").css("background-color","white");
	$(".modal-content").css("color","black");
	$(".modal-content").empty();
	$(".modal-content").append(
			'<span class="close">&times;</span>'+
			'<p>Ajouter un nouveau tag </p>'+
			'    <div class="row " >' +
			'      <div class="col-sm-9">' +
			'       <input id="tagCategory" class="form-control" list="browsers" name="browser" placeholder="Choisir ou saisir catégorie" > <datalist class="w-100" id="browsers"> <option value="exemple"> </datalist>' +
			'		<input id="tagValue" class="form-control" style="margin-bottom:2%;margin-top:2%" placeholder="Nom du tag">'+
			'       <div id="reponse" style="margin:12px;font-weight: bold;"></div>' +
			'      </div>' +
			'       <div class="col-sm-2" >' +
			'            <button id= "buttonPopupAddTag" class="btn btn-info" style=""> Créer tag</button>' +
			'       </div>'+
	'    </div>');

	$(".close").click(function() {
		$("#addtagpopup").fadeOut();
	});

	// AJOUTER POPUP CLOSE
	
	
	$("#buttonPopupAddTag").click(function() {
		if(addTagToDatabase($("#tagCategory").val(), $("#tagValue").val())){
			
			var popupName = "popupAjoutTag";
			
			//console.log("J'AI BIEN INSERE DANS LA BDD");
			$("#reponse").css("color","green");
			$("#reponse").text("Tag ajouté en base de données : catégorie "+$("#tagCategory").val()+" nom "+ $("#tagValue").val()+".");
			
			addTagInList($("#tagValue").val());
			addTagInProjectDiv($("#tagValue").val())
			
			generateOneTag($("#tagCategory").val(),$("#tagValue").val());
			
			popupSuccess(popupName,"Le tag a bien été inséré");
			$("#"+popupName).fadeTo("slow",1);
			//$("#"+popupName).delay(1000).fadeTo("slow", 0);
			$("#addtagpopup").fadeTo("slow",1);
			$("#addtagpopup").fadeOut();
			
		}else{
			$("#reponse").css("color","red");
			$("#reponse").text("Tag non ajouté en base de données, contacter administrateur.");
			popupFailure(popupName," ERREUR : le tag n'a pas été inséré");
			$("#"+popupName).fadeTo("slow",1);
			$("#addtagpopup").fadeOut();
		}
	});


	$("#addtagpopup").fadeIn();

}






function addTagInList(name){

	$("#taglist").append('<a href="#" class="badge badge-primary">'+name+'</a>');
}
