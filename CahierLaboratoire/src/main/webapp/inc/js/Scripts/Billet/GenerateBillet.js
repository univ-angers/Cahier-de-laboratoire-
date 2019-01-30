


function generateBillet(idOrig,data,creation){
	
	var id = "billet"+idOrig;
	$("#billetsList").append(
			'<div id="' +id+  '" class="card mb-4 shadow-sm ">'+ creation +
				'<div class="card-header ">'+
					'<div id="tag'+idOrig+'">'+
					'<button type="button" id="'+id+'addTagToBillet" class="btn btn-dark addTagToBilletButton" style="">'+
					'+'+
					'</button>'+
					'</div>'+
					'<div class="float-left">'+
						'<h6 class="my-0 font-weight-normal d-inline"> </h6>'+
					'</div>'+
					'<div class="float-right btn-group">'+
						'<button type="button" id="'+id+'Remove" class="btn btn-dark removeButton" style="margin:1%">'+
						'<img src="./inc/assets/trashcan.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'tagToPrintMono" class="btn btn-dark printButtonMono" style="margin:1%"onclick=" onclick="exportPDFMono("'+id+')">'+
							'<img src="./inc/assets/print.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'save" class="btn btn-dark saveButton" style="margin:1%">'+
							'<img src="./inc/assets/save.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'Modify" class="btn btn-dark buttonQuill" style="margin:1%">'+
							'<img src="./inc/assets/edit1.svg" width="25" height="25"/>'+
						'</button>'+
					'</div>'+
				'</div>'+
					'<div id="'+id+'Content">'+
					data  + 
					'</div>'+
					'<div id="'+id+'content-container">'+
						'<div id="'+id+'toolbar-container"></div>'+
						'<div id="'+id+'editor-container"></div>'+
					'</div>'+
					'</div>'+
			'</div>')
			
			
			quillEnable(id);
			
			$("#"+id+"save").prop("disabled",true);
			
			getTagsInBillet(idOrig); 
			
}



function createBillets(idOrig,data){
	
	var id = "billet"+idOrig;
	
	$("#billetsList").prepend(
			'<div id="' +id+  '" class="card mb-4 shadow-sm ">'+ data +
				'<div class="card-header ">'+
					'<div id="tag'+idOrig+'">'+
					'<button type="button" id="'+id+'addTagToBillet" class="btn btn-dark addTagToBilletButton" style="">'+
					'+'+
					'</button>'+
					'</div>'+
					'<div class="float-left">'+
						'<h6 class="my-0 font-weight-normal d-inline"> </h6>'+
					'</div>'+
					'<div class="float-right btn-group">'+
						'<button type="button" id="'+id+'Remove" class="btn btn-dark removeButton" style="margin:1%">'+
						'<img src="./inc/assets/trashcan.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'tagToPrintMono" class="btn btn-dark printButtonMono" style="margin:1%">'+
							'<img src="./inc/assets/print.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'save" class="btn btn-dark saveButton" style="margin:1%">'+
							'<img src="./inc/assets/save.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'Modify" class="btn btn-dark buttonQuill" style="margin:1%">'+
							'<img src="./inc/assets/edit1.svg" width="25" height="25"/>'+
						'</button>'+
					'</div>'+
				'</div>'+
					'<div id="'+id+'Content">'+
					'</div>'+
					'<div id="'+id+'content-container">'+
						'<div id="'+id+'toolbar-container"></div>'+
						'<div id="'+id+'editor-container"></div>'+
					'</div>'+
			'</div>')
			
			
			quillEnable(id);
			
			$("#"+id+"Modify").prop("disabled",true);
			
			getTagsInBillet(idOrig); 
			
}

function btnDisable(){

	$.ajax({
		url: "./billet/controlP",
		method: "POST",
		dataType: "json",
		contentType: 'application/json',
		success : function(data) {

			for(var i in data)
			{
				if (data[i].startsWith("false"))
				{

					var n = data[i].split(" ");
					var id = "billet"+n[1];
					$("#"+id+"Remove").prop("disabled",true);
					$("#"+id+"Modify").prop("disabled",true);

				}

			};
			return true;
		},
		error : function(e) {
			return false;
		},
		done : function(e) {
			return true;
		},
		data:  JSON.stringify({ idUtilisateur : $("#utilisateur").text()})
	});

}
