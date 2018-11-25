/**
 * 
 */

function addTagToDatabase(category, name){
	if(category.length > 0 && name.length > 0){
		var data = {}
		data["category"] = category;
		data["name"] = name;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "url",
			data : JSON.stringify(data),
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
		$("#rechercheTag").fadeOut();
	});

	$("#buttonPopupAddTag").click(function() {

		if(addTagToDatabase($("#tagCategory").val(), $("#tagValue").val())){

			$("#reponse").css("color","green");
			$("#reponse").text("Tag ajouté en base de données : catégorie "+$("#tagCategory").val()+" nom "+ $("#tagValue").val()+".");
			$("#buttonPopupAddTag").text("Close");
			$("#buttonPopupAddTag").click(function() {
				$("#addtagpopup").fadeOut();
			});
			addTagInList($("#tagValue").val());
			addTagInProjectDiv($("#tagValue").val())
		}else{
			$("#reponse").css("color","red");
			$("#reponse").text("Tag non ajouté en base de données, contacter administrateur.");
		}
	});


	$("#addtagpopup").fadeIn();

}

function addTagInList(name){

	$("#taglist").append('<a href="#" class="badge badge-primary">'+name+'</a>');
}
