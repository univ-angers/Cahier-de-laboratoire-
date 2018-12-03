


function generateBillet(idOrig,data){
	
	var id = "billet"+idOrig;
	// @THIERRY : ID n'est pas un id mais une chaine de caractere
	//console.log("on genere le billet id : "+id+" avec le content : "+data );
	$("#billetsList").append(
			'<div id="' +id+  '" class="card mb-4 shadow-sm">'+
				'<div class="card-header ">'+
					'<div id="tag'+idOrig+'">'+
					'<button type="button" id="'+id+'Remove" class="btn btn-dark addTagToBilletButton" style="">'+
					'+'+
					'</button>'+
					'</div>'+
					'<div class="float-left">'+
						'<h6 class="my-0 font-weight-normal d-inline"> </h6>'+
					'</div>'+
					'<div class="float-right btn-group">'+
						'<button type="button" id="'+id+'Remove" class="btn btn-dark removeButton" style="margin:1%">'+
						'<img src="../inc/assets/trashcan.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'print" class="btn btn-dark printButton" style="margin:1%" onclick="exportPDF()">'+
							'<img src="../inc/assets/print.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'save" class="btn btn-dark saveButton" style="margin:1%">'+
							'<img src="../inc/assets/save.png" width="25" height="25"/>'+
						'</button>'+
						'<button type="button" id="'+id+'Modify" class="btn btn-dark buttonQuill" style="margin:1%">'+
							'<img src="../inc/assets/edit1.svg" width="25" height="25"/>'+
						'</button>'+
					'</div>'+
				'</div>'+
					'<div id="'+id+'Content">'+
					data +
					'</div>'+
					'<div id="'+id+'content-container">'+
						'<div id="'+id+'toolbar-container"></div>'+
						'<div id="'+id+'editor-container"></div>'+
					'</div>'+
			'</div>')
			
			quillEnable(id);
			
			$("#"+id+"save").prop("disabled",true);
			
			getTagsInBillet(idOrig); 
			
}
