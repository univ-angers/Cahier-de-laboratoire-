function exportPDF() {
	//Exporter tous les billets
	var doc = new jsPDF('p', 'pt', 'a4');
	var width = doc.internal.pageSize.getWidth();
	var height = doc.internal.pageSize.getHeight();
	//On enlève les boutons de l'export pour une meilleure mise en page
	$('.btn-group').hide();
	$('.addTagToBilletButton').hide();
	//On récupère tous les billets 
	var toExport = $(".tagToPrint");
		doc.addHTML( toExport,  {dim:{'w':width, 'h':height}},  function() {
		doc.save('billet.pdf');
	});
	//On réaffiche les boutons
	$('.btn-group').show();
	$('.addTagToBilletButton').show(); 
}

function exportPDFMono(id) {
	//Exporter un seul billet
	console.log(id)
	var doc = new jsPDF('p', 'pt', 'a4');
	var width = doc.internal.pageSize.getWidth();
	var height = doc.internal.pageSize.getHeight();
	$('.btn-group').hide();
	$('.addTagToBilletButton').hide();
	//On ne récupère que le billet demandé
	var toExport = $("#"+id);
		doc.addHTML( toExport,  {dim:{'w':width, 'h':height}},  function() {
		doc.save('billet.pdf');
	});
		console.log(toExport)
	$('.btn-group').show();
	$('.addTagToBilletButton').show(); 
}