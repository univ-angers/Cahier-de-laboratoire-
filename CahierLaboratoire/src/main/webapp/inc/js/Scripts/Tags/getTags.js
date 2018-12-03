
function getAllTags(){

	var data = {}
	//data["category"] = category;
	//data["name"] = name;
	$.ajax({
		type : "GET",
		url : "../tag/get",
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

			$('.badge').popover({
				trigger: 'focus',
				placement: 'bottom',
				html: true,
				title : '<span class="text-info"><strong>Tag menu : </strong></span>',
				content : '<div id="inputPopover"></div><button class="btn btn-dark" id="buttonPopoverRemove" style="margin:2px">Supprimer</button><button class="btn btn-dark" id="buttonPopoverModify" style="margin:2px">Modifier</button>'
			});

			$('.badge').on('hide.bs.popover', function (e) {
				//
				// on hiding popover stop action
				console.log("@getTags : on stoppe la popover")
				badgeToModify = this;

//				$("#buttonPopoverRemove").on( "click",function(){
//					if($(this).attr("id")!="buttonPopoverValidate"){
////						console.log("@getTags : on remove le tag")
////						//call remove 
////						//if true
////						//else
////						$(badgeToModify).remove();
//					}
//				});

				$("#buttonPopoverModify").on( "click",function(){
					if($(this).attr("id")!="buttonPopoverValidate"){
						console.log("@getTags : on modifie le tag");

						$("#inputPopover").html("<input id='test' type='text'>");

						$(this).text("Valider");

						$("#buttonPopoverRemove").text("Annuler");

						$(this).attr("id","buttonPopoverValidate");

						$("#buttonPopoverRemove").on( "click",function(){
							$("#inputPopover").html("");
							$("#buttonPopoverValidate").text("Modifier");
							$("#buttonPopoverRemove").text("Supprimer");
						});

					}
				});

				$("#buttonPopoverValidate").on("click",function(){
//					if($(this).attr("id")=="buttonPopoverValidate"){
//
//						//call modify
//						//if true
//						//else
//						//close popover
//						//$(badgeToModify).html($("#test").val());
//						
//						$(this).attr("id","buttonPopoverModify");
//					}
					console.log("hello");
				});
				e.preventDefault();
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
//		url : "../tag/get",
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

