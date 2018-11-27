
function quillEnable(strId){

	var quill = new Quill('#'+strId+'editor-container', {
		modules: {
			toolbar: [
				[{ header: [1, 2, false] }],
				['bold', 'italic', 'underline'],
				['image', 'code-block']
				]
		},
		theme: 'snow'  // or 'bubble'
	});

	quill.setText($( "#"+strId+"Content" ).text());
	$( "#"+strId+"Content" ).text('');
	$('.ql-toolbar','#'+strId+'content-container').hide();
	quill.disable();
	
	AllQuillObjects.set(strId,quill);

	return quill;
}
