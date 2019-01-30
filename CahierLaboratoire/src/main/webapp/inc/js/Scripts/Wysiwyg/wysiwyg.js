//permet de creer un objet quill en lui fournissant un ID
//Cet id permet de communiquer avec le bon objet quill de chaque billet
function quillEnable(strId){
	
	var quill = new Quill('#'+strId+'editor-container', {
		modules: {
			toolbar: [
				[{ header: [1, 2, false] }],
				['bold', 'italic', 'underline'],
				['image'],
				['link', 'code-block']
				]
		},
		theme: 'snow'
	});

	$( "#"+strId+"editor-container" ).find('.ql-editor').html($( "#"+strId+"Content" ).html());
	$( "#"+strId+"Content" ).text('');
	//$('.ql-toolbar','#'+strId+'content-container').hide();
	//quill.disable();
	//on ajoute à la map contenant les objets quill dans le mainPage.js
	AllQuillObjects.set(strId,quill);

	return quill;
}
