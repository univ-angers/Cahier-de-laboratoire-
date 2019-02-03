/**
 * Permet de fournir les fonctionnalités aux boutons des popovers des tags
 */

function updatePopover(){
	$('.badge').on('click', function (e) {

		if($(".popover").length <1){

			$(this).popover({
				trigger: 'focus',
				placement: 'bottom',
				html: true,
				title : '<span class="text-info"><strong>Tag menu :</strong> <div class="close"> &times;</div></span>',
				content : '<div id="inputPopover"></div><button class="btn btn-dark" id="buttonPopoverRemove" style="margin:2px">Supprimer</button><button class="btn btn-dark" id="buttonPopoverModify" style="margin:2px">Modifier</button>'
			});


			$(this).popover('show');

			badgeToModify = this;

			objectPopover = $('#inputPopover').parent().parent();


			$(".close").on("click",function(){
				$(objectPopover).remove();

			});

			$(this).on('hide.bs.popover', function (e) {
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
							
							var content=$("#textToSetInTag").val();
							var idTagParsed= badgeToModify.id.replace('tag','');
							
							var idTagParsed=idTagParsed.replace('billetTag','');
							
							$("#tag"+idTagParsed).html(content);
							
							$("#billetTag"+idTagParsed).html(content);
							
							updateTag(idTagParsed,$("#textToSetInTag").val())
							
							$(objectPopover).remove();
						}
					}

				});

				$("#buttonPopoverRemove").on( "click",function(e2){
					console.log("remove");
					if($(this).text()!="Annuler"){
						console.log("Bouton annulé pressé");

						removeTag(badgeToModify.id.replace('tag',''));

						$(badgeToModify).remove();

						$(objectPopover).remove();
						
						e2.stopImmediatePropagation();
						
					}else{
						$("#inputPopover").html("");
						$("#buttonPopoverModify").text("Modifier");
						$("#buttonPopoverRemove").text("Supprimer");
					}
				});
			});
		}

	});
}


function updatePopoverOnBillet(){
	$('.badge').on('click', function (e) {

		if($(".popover").length <1){

			$(this).popover({
				trigger: 'focus',
				placement: 'bottom',
				html: true,
				title : '<span class="text-info"><strong>Tag menu :</strong> <div class="close"> &times;</div></span>',
				content : '<div id="inputPopover"></div><button class="btn btn-dark" id="buttonPopoverRemove" style="margin:2px">Supprimer</button><button class="btn btn-dark" id="buttonPopoverModify" style="margin:2px">Modifier</button>'
			});


			$(this).popover('show');

			badgeToModify = this;

			objectPopover = $('#inputPopover').parent().parent();


			$(".close").on("click",function(){
				$(objectPopover).remove();

			});

			$(this).on('hide.bs.popover', function (e) {
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
							
							var content=$("#textToSetInTag").val();
							var idTagParsed= badgeToModify.id.replace('tag','');
							
							var idTagParsed=idTagParsed.replace('billetTag','');
							
							$("#tag"+idTagParsed).html(content);
							
							$("#billetTag"+idTagParsed).html(content);
							
							updateTag(idTagParsed,$("#textToSetInTag").val())
							
							$(objectPopover).remove();
						}
					}

				});

				$("#buttonPopoverRemove").on( "click",function(e2){
					
					var idTag=badgeToModify.id.replace('billetTag','');
					console.log("remove : zelirhozeirjzleirj : "+idTag);
					if($(this).text()!="Annuler"){
						console.log(badgeToModify.parentNode.parentNode.parentNode.id.replace('billet',''))
						
						removeTagBillet(badgeToModify.parentNode.parentNode.parentNode.id.replace('billet',''),idTag);

						$(badgeToModify).remove();

						$(objectPopover).remove();
						
						e2.stopImmediatePropagation();
						
					}else{
						$("#inputPopover").html("");
						$("#buttonPopoverModify").text("Modifier");
						$("#buttonPopoverRemove").text("Supprimer");
					}
				});
			});
		}

	});
}
