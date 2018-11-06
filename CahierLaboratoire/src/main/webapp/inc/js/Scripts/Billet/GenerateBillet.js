//			<div id="billet-1" class="card mb-4 shadow-sm">
//				<div class="card-header">
//
//					<h6 class="my-0 font-weight-normal d-inline">Projet_5 Mycose
//						de cyrillepinette</h6>
//					<button type="button" class="btn btn-dark d-inline float-right buttonQuill">
//						<img id="testquill" src="<c:url value="/inc/assets/edit1.svg"/>"
//							alt="" width="25" height="25">
//					</button>
//
//
//				</div>
//				<div class="card-body">
//					<%-- Nom : ${sessionScope.sessionUtilisateur.nom} Prénom : <br>
//					${sessionScope.sessionUtilisateur.prenom} Email :<br>
//					${sessionScope.sessionUtilisateur.email} MDP :<br>
//					${sessionScope.sessionUtilisateur.motdepasse} ADMIN :<br>
//					${sessionScope.sessionUtilisateur.isadmin}<br> --%>
//					<div id=" data1">
//					 Le Lorem Ipsum est
//					simplement du faux texte employé dans la composition et la mise en
//					page avant impression. Le Lorem Ipsum est le faux texte standard de
//					l'imprimerie depuis les années 1500, quand un imprimeur anonyme
//					assembla ensemble des morceaux de texte pour réaliser un livre
//					spécimen de polices de texte. Il n'a pas fait que survivre cinq
//					siècles, mais s'est aussi adapté à la bureautique informatique,
//					sans que son contenu n'en soit modifié. Il a été popularisé dans
//					les années 1960 grâce à la vente de feuilles Letraset contenant des
//					passages du Lorem Ipsum, et, plus récemment, par son inclusion dans
//					des applications de mise en page de texte, comme Aldus PageMaker.
//					</div>
//					<div id="content-container">
//						<div id="toolbar-container">
//						</div>
//						<div id="editor-container"></div>
//					</div>
//				</div>
//			</div>



function generateBillet(id,data){
	$(".col-10").append(
			'<div id="'+id+'" class="card mb-4 shadow-sm">'+
				'<div class="card-header">'+
					'<h6 class="my-0 font-weight-normal d-inline">Projet_5 Mycose de cyrillepinette </h6>'+
					'<button type="button" id="'+id+'print" class="btn btn-dark d-inline float-right printButton" style="margin-left:1%">'+
					'<img src="../inc/assets/print.png" width="25" height="25"/>'+
					'</button>'+
					'<button type="button" id="'+id+'save" class="btn btn-dark d-inline float-right saveButton" style="margin-left:1%">'+
					'<img src="../inc/assets/save.png" width="25" height="25"/>'+
					'</button>'+
					'<button type="button" id="'+id+'Modify" class="btn btn-dark d-inline float-right buttonQuill">'+
						'<img src="../inc/assets/edit1.svg" width="25" height="25"/>'+
					'</button>'+


				'</div>'+
				'<div class="card-body">'+
					'<div id="'+id+'Content">'+
					data+
					'</div>'+
					'<div id="'+id+'content-container">'+
						'<div id="'+id+'toolbar-container"></div>'+
						'<div id="'+id+'editor-container"></div>'+
					'</div>'+
				'</div>'+
			'</div>')
			
			$("#"+id+"save").prop("disabled",true);
			
}
