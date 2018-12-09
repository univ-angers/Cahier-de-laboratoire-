



function getAllTags(){


	var data = {}
	//data["category"] = category;
	//data["name"] = name;
	$.ajax({
		type : "GET",
		url : "./tag/get", 
		//data : JSON.stringify(data),
		data : data,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//print all tags


			for (var i = 0; i < data.length; i++){
				var obj = data[i];
				generateOneTag(obj[1], obj[0]);


			}

			$('.badge').on('click', function (e) {

				if($(".popover").length <1){

					$(this).popover({
						trigger: 'focus',
						placement: 'bottom',
						html: true,
						title : ' <span class="text-info"><strong>Tag menu :</strong> <div class="close"> &times;</div> </span>',
						content : '<div id="inputPopover"></div><button class="btn btn-dark" id="buttonPopoverRemove" style="margin:2px">Supprimer</button><button class="btn btn-dark" id="buttonPopoverModify" style="margin:2px">Modifier</button>'
					});


					$(this).popover('show');

					badgeToModify = this;
					
					objectPopover = $('#inputPopover').parent().parent();

					
					$(".close").on("click",function(){
						$(objectPopover).remove();
					});

					$(this).on('hide.bs.popover', function (e) {
						//
						// on hiding popover stop action
						
						e.preventDefault();
						
						$("#buttonPopoverModify").on( "click",function(e1){

							console.log("value avant le click : "+$(this).text())

							if($(this).text()=="Modifier"){

								$("#inputPopover").html("<input id='textToSetInTag' type='text'>");

								$(this).text("Valider");

								$("#buttonPopoverRemove").text("Annuler");

								e1.stopImmediatePropagation();

							}else{
								if($("#textToSetInTag").val()!=""){
									$(badgeToModify).html($("#textToSetInTag").val())

									$(objectPopover).remove();
								}
							}

						});

						$("#buttonPopoverRemove").on( "click",function(){

							if($(this).text()!="Annuler"){

								$(badgeToModify).remove();

								$(objectPopover).remove();
							}else{
								$("#inputPopover").html("");
								$("#buttonPopoverModify").text("Modifier");
								$("#buttonPopoverRemove").text("Supprimer");
							}
						});
					});
				}

			});




			return true;
		},
		error : function(e) {
			return false;
		},
		done : function(e) {
			return true;
		}
	});


//	on rempli la liste des tags

}

function removeTag(id){
	console.log('Removing tag with id :'+id)
	$.ajax({
//		type : "GET",
//		url : "./tag/get",
//		//data : JSON.stringify(data),
//		data : data,
//		dataType : 'json',
//		timeout : 100000,
//		success : function(data) {
//		//print all tags


//		for (var i = 0; i < data.length; i++){
//		var obj = data[i];
//		generateOneTag(obj[1], obj[0]);

//		}

//		return true;
//		},
//		error : function(e) {
//		return false;
//		},
//		done : function(e) {
//		return true;
//		}
	});
}

