$(document).ready(function() {

	$( ".buttonQuill" ).click(function() {
		var quill = new Quill('#editor-container', {
			modules: {
				toolbar: [
					[{ header: [1, 2, false] }],
					['bold', 'italic', 'underline'],
					['image', 'code-block']
					]
			},
			theme: 'snow'  // or 'bubble'
		});
		text=$( "#data1" ).text();
		quill.setText($.trim(text.replace(/[\t\n]+/g,' ')));
		$( "#data1" ).text('');
		$(this).prop("disabled",true);
		
		console.log($(this))
	});

});
