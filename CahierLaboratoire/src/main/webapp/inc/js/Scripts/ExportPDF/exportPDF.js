function exportPDF() {
	var doc = new jsPDF('p', 'pt', 'a4');
	var width = doc.internal.pageSize.getWidth();
	var height = doc.internal.pageSize.getHeight();
	var toExport = $(".tagToPrint");
		doc.addHTML( toExport,  {dim:{'w':width, 'h':height}},  function() {
		//doc.internal.scaleFactor = 0.25; 
		doc.save('web.pdf');
	
	});

	 $('.btn-group').show();
	 $('.addTagToBilletButton').show(); //before the addHTML()


}