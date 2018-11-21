//$(document).ready(function() {
//
//	$( ".buttonQuill" ).click(function() {
//		var quill = new Quill('#editor-container', {
//			modules: {
//				toolbar: [
//					[{ header: [1, 2, false] }],
//					['bold', 'italic', 'underline'],
//					['image', 'code-block']
//					]
//			},
//			theme: 'snow'  // or 'bubble'
//		});
//		text=$( "#data1" ).text();
//		quill.setText($.trim(text.replace(/[\t\n]+/g,' ')));
//		$( "#data1" ).text('');
//		$(this).prop("disabled",true);
//		
//		console.log($(this))
//	});
//
//});
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
}

function quillDisable(strId){
	$('#'+strId+'Content').append($('.ql-editor','#'+strId+'content-container').html());
	$('.ql-toolbar','#'+strId+'content-container').remove();
	$('#'+strId+'editor-container').empty();
	$('#'+strId+'editor-container').removeClass();
}