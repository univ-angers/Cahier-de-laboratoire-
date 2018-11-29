
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
	console.log("On met a jour le contenu du editor quill "+$( "#"+strId+"Content" ).html())
	
	//quill.setText();
	$( "#"+strId+"editor-container" ).find('.ql-editor').html($( "#"+strId+"Content" ).html());
	$( "#"+strId+"Content" ).text('');
	$('.ql-toolbar','#'+strId+'content-container').hide();
	quill.disable();
	
	AllQuillObjects.set(strId,quill);

	return quill;
}
