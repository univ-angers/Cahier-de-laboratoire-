function exportPDF() {
	var doc = new jsPDF('p', 'pt', 'a4');
	var width = doc.internal.pageSize.getWidth();
	var height = doc.internal.pageSize.getHeight();
	$('.btn-group').hide();
	$('.addTagToBilletButton').hide();
	var toExport = $(".tagToPrint");
		doc.addHTML( toExport,  {dim:{'w':width, 'h':height}},  function() {
		doc.save('billet.pdf');
	});
	$('.btn-group').show();
	$('.addTagToBilletButton').show(); 
}

function exportPDFMono(id) {
	console.log(id)
	var doc = new jsPDF('p', 'pt', 'a4');
	var width = doc.internal.pageSize.getWidth();
	var height = doc.internal.pageSize.getHeight();
	$('.btn-group').hide();
	$('.addTagToBilletButton').hide();
	var toExport = $("#"+id);
		doc.addHTML( toExport,  {dim:{'w':width, 'h':height}},  function() {
		doc.save('billet.pdf');
	});
		console.log(toExport)
	$('.btn-group').show();
	$('.addTagToBilletButton').show(); 
}